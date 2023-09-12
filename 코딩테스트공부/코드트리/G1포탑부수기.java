package 코딩테스트공부.코드트리;

import java.io.*;
import java.util.*;

public class G1포탑부수기 {
    static int N, M, K, max;
    static Node map[][];
    static Node tempMap[][];
    static boolean visited[][];
    // 우 하 좌 상 순서
    static int dx[] = { 0, 1, 0, -1 };
    static int dy[] = { 1, 0, -1, 0 };
    // 포탑 8방위
    static int tdx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int tdy[] = { 1, 1, 0, -1, -1, -1, 0, 1 };

    static int dp[][];
    static boolean isSuccess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Node[N][M];
        tempMap = new Node[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                boolean isBroken = false;
                if (n == 0)
                    isBroken = true;
                map[i][j] = new Node(i, j, 0, n, isBroken);
            }
        }

        for (int t = 1; t <= K; t++) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                Arrays.fill(visited[i], false);
            }
            isSuccess = false;
            tempMap = new Node[N][M];
            // 공격자 선정
            Node selectAttacker = selectedAttacker(t);
            dp[selectAttacker.x][selectAttacker.y] = 0;
            // 공격자의 공격
            Node target = targetEnemy(selectAttacker, t);
            // 부서지지 않은 포탑이 1개가 된다면 즉시 중지
            if (target.x == -1 && target.y == -1) {
                selectAttacker.power -= N + M;
                break;
            }
            // 레이저 어택
            exploreRoute(selectAttacker.x, selectAttacker.y, 1, selectAttacker, target);

            // 레이저 어택 실패 시 포탑 어택
            if (isSuccess) {
                ragerAttack(selectAttacker, target);
            } else {
                turetAttack(selectAttacker, target);
            }
            // 포탑 정비
            recoveryTurret();
            // print();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, map[i][j].power);
            }
        }
        System.out.println(max);

    }

    // private static void print() {
    // System.out.println();
    // int temp[][] = new int[N][M];
    // for (int i = 0; i < N; i++) {
    // for (int j = 0; j < M; j++) {
    // temp[i][j] = map[i][j].power;
    // }
    // System.out.println(Arrays.toString(temp[i]));
    // }
    // }

    private static void recoveryTurret() {
        // 포탑 공격과 무관했던 포탑은 공격력이 1씩 올라갑니데이
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && !map[i][j].isBroken) {
                    map[i][j].power++;
                }
            }
        }
    }

    private static void ragerAttack(Node selectAttacker, Node target) {
        // 레이저 경로에 있는 적을 지지고 0이하는 부서진다.
        visited[selectAttacker.x][selectAttacker.y] = true;

        Node now = target;
        now.power -= selectAttacker.power;
        visited[now.x][now.y] = true;
        if (now.power <= 0) {
            now.power = 0;
            now.isBroken = true;
        }
        while (tempMap[now.x][now.y] != selectAttacker) {
            now = tempMap[now.x][now.y];
            now.power -= selectAttacker.power / 2;
            visited[now.x][now.y] = true;
            if (now.power <= 0) {
                now.power = 0;
                now.isBroken = true;
            }
        }
    }

    private static void exploreRoute(int x, int y, int t, Node selectAttacker, Node target) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= N) {
                nx = 0;
            } else if (nx < 0) {
                nx = N - 1;
            }
            if (ny >= M) {
                ny = 0;
            } else if (ny < 0) {
                ny = M - 1;
            }

            Node now = map[nx][ny];
            // 이미 포탑이 부러졌거나 왔던 곳인데 시간이 많은곳이라면 돌아가기
            if (now.isBroken || dp[nx][ny] <= t)
                continue;
            if (target.x == nx && target.y == ny)
                isSuccess = true;
            dp[nx][ny] = t;
            tempMap[nx][ny] = map[x][y];
            exploreRoute(nx, ny, t + 1, selectAttacker, target);

        }
    }

    // 포탑 어택
    private static void turetAttack(Node selectAttacker, Node target) {
        visited[selectAttacker.x][selectAttacker.y] = true;
        visited[target.x][target.y] = true;

        target.power -= selectAttacker.power;
        visited[target.x][target.y] = true;
        if (target.power <= 0) {
            target.power = 0;
            target.isBroken = true;
        }
        for (int i = 0; i < 8; i++) {
            int nx = target.x + tdx[i];
            int ny = target.y + tdy[i];

            if (nx >= N) {
                nx = 0;
            } else if (nx < 0) {
                nx = N - 1;
            }
            if (ny >= M) {
                ny = 0;
            } else if (ny < 0) {
                ny = M - 1;
            }

            if (map[nx][ny].isBroken || map[nx][ny].equals(selectAttacker))
                continue;
            map[nx][ny].power -= selectAttacker.power / 2;
            visited[nx][ny] = true;
            if (map[nx][ny].power <= 0) {
                map[nx][ny].power = 0;
                map[nx][ny].isBroken = true;
            }

        }
    }

    // 1. 공격자 선정
    private static Node selectedAttacker(int time) {
        Node selectNode = new Node(-1, -1, 0, Integer.MAX_VALUE, false);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node now = map[i][j];
                if (now.isBroken)
                    continue;
                if (now.power < selectNode.power) {
                    selectNode = now;
                } else if (now.power == selectNode.power) {
                    if (now.time > selectNode.time) {
                        selectNode = now;
                    } else if (now.time == selectNode.time) {
                        if (now.x + now.y > selectNode.x + selectNode.y) {
                            selectNode = now;
                        } else if (now.x + now.y == selectNode.x + selectNode.y) {
                            if (now.y > selectNode.y) {
                                selectNode = now;
                            }
                        }
                    }
                }
            }
        }
        // 공격자의 시간 선정
        selectNode.time = time;
        selectNode.power += N + M;
        return selectNode;
    }

    // 2. 공격자의 공격 선정
    private static Node targetEnemy(Node attacker, int time) {
        Node selectNode = new Node(-1, -1, 0, 0, false);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node now = map[i][j];
                if (now.isBroken || now.equals(attacker))
                    continue;
                if (now.power > selectNode.power) {
                    selectNode = now;
                } else if (now.power == selectNode.power) {
                    if (now.time < selectNode.time) {
                        selectNode = now;
                    } else if (now.time == selectNode.time) {
                        if (now.x + now.y < selectNode.x + selectNode.y) {
                            selectNode = now;
                        } else if (now.x + now.y == selectNode.x + selectNode.y) {
                            if (now.y < selectNode.y) {
                                selectNode = now;
                            }
                        }
                    }
                }
            }
        }
        return selectNode;
    }

    static class Node {
        int x, y, time, power;
        boolean isBroken;

        public Node(int x, int y, int time, int power, boolean isBroken) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.power = power;
            this.isBroken = isBroken;
        }

        @Override
        public String toString() {
            return "Node [x=" + x + ", y=" + y + ", time=" + time + ", power=" + power + ", isBroken=" + isBroken + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            result = prime * result + time;
            result = prime * result + power;
            result = prime * result + (isBroken ? 1231 : 1237);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            if (time != other.time)
                return false;
            if (power != other.power)
                return false;
            if (isBroken != other.isBroken)
                return false;
            return true;
        }

    }
}
// 10 6 1000
// 3362 3908 4653 3746 4119 3669
// 4174 0 0 868 1062 854
// 633 51 759 0 4724 1474
// 2735 365 1750 3382 498 1672
// 141 3700 0 436 2752 974
// 3494 0 4719 2016 3870 0
// 3357 0 4652 3468 0 3758
// 4610 3125 0 2364 3303 1904
// 0 0 0 0 3959 3324
// 3187 0 105 2821 3642 160
// 정답 727 내 정답 729
// https://www.codetree.ai/problems/destroy-the-turret/submissions
// 3번 실수한 이유
// 1. 포탑이 하나 남았을 때 상황 고려 못함
// 2. 열과 행을 헷갈림
// 3. 포탑 광역 공격이 자신도 때릴줄 생각 못함