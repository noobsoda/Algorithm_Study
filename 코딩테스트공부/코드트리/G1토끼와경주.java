package 코딩테스트공부.코드트리;

import java.io.*;
import java.util.*;

public class G1토끼와경주 {
    static int Q;
    static int N, M, P;
    static Queue<Rabbit> rabbitQ;
    static List<Rabbit> rabbits;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            switch (q) {
                case 100:
                    init(st);
                    break;
                case 200:
                    int k = Integer.parseInt(st.nextToken());
                    int s = Integer.parseInt(st.nextToken());
                    proceedRace(k, s);
                    break;
                case 300:
                    int pid = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    updateDistance(pid, l);
                    break;
                case 400:
                    long res = selectBestRabbit();
                    System.out.println(res);
                    break;
            }

        }
    }

    private static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        rabbitQ = new PriorityQueue<Rabbit>((o1, o2) -> {
            if (o1.count == o2.count) {
                if (o1.x + o1.y == o2.x + o2.y) {
                    if (o1.x == o2.x) {
                        if (o1.y == o2.y) {
                            return o1.pid - o2.pid;
                        }
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return (o1.x + o1.y) - (o2.x + o2.y);
            }
            return o1.count - o2.count;
        });
        rabbits = new ArrayList<>();

        for (int j = 0; j < P; j++) {
            int pid = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            Rabbit rabbit = new Rabbit(pid, dist);
            rabbitQ.add(rabbit);
            rabbits.add(rabbit);
        }
    }

    // k라운드 진행, s 점수 획득
    private static void proceedRace(int k, int s) {
        for (int i = 0; i < k; i++) {
            // 우선순위 가장 높은 토끼 커몬
            Rabbit nowRabbit = rabbitQ.poll();
            nowRabbit.isUsed = true;
            nowRabbit.count++;

            int maxX = 0, maxY = 0;

            // 상하좌우 4개 위치 구하기 (최적화)
            for (int j = 0; j < 4; j++) {
                int nx = nowRabbit.x + (dx[j] * nowRabbit.dist % ((N - 1) * 2));
                int ny = nowRabbit.y + (dy[j] * nowRabbit.dist % ((M - 1) * 2));

                // 전체적으로 다시 수정
                if (nx < 0) {
                    nx = Math.abs(nx);
                    if (nx >= N) {
                        int diff = nx - (N - 1);
                        nx = (N - 1) - diff;
                    }
                } else if (nx >= N) {
                    int diff = nx - (N - 1);
                    nx = (N - 1) - diff;
                    if (nx < 0) {
                        nx = Math.abs(nx);
                    }
                } else if (ny < 0) {
                    ny = Math.abs(ny);
                    if (ny >= M) {
                        int diff = ny - (M - 1);
                        ny = (M - 1) - diff;
                    }

                } else if (ny >= M) {
                    int diff = ny - (M - 1);
                    ny = (M - 1) - diff;
                    if (ny < 0) {
                        ny = Math.abs(ny);
                    }
                }

                // 우선순위에 따라 nx, ny 뽑기
                if (nx + ny > maxX + maxY) {
                    maxX = nx;
                    maxY = ny;
                } else if (nx + ny == maxX + maxY) {
                    if (nx > maxX) {
                        maxX = nx;
                        maxY = ny;
                    } else if (nx == maxX) {
                        if (ny > maxY) {
                            maxX = nx;
                            maxY = ny;
                        }
                    }
                }
            }
            nowRabbit.x = maxX;
            nowRabbit.y = maxY;

            int maxPosition = maxX + 1 + maxY + 1;
            // i번 토끼를 제외한 나머지 토꺵이들 점수 더해주기
            for (Iterator<Rabbit> it = rabbitQ.iterator(); it.hasNext();) {
                Rabbit rabbit = it.next();
                rabbit.score += maxPosition;
            }
            rabbitQ.add(nowRabbit);

        }

        // 토끼 정렬
        Collections.sort(rabbits, (o1, o2) -> {
            if (o1.isUsed && o2.isUsed) {
                if (o1.x + o1.y == o2.x + o2.y) {
                    if (o1.x == o2.x) {
                        if (o1.y == o2.y) {
                            return o2.pid - o1.pid;
                        }
                        return o2.y - o1.y;
                    }
                    return o2.x - o1.x;
                }
                return (o2.x + o2.y) - (o1.x + o1.y);
            }
            return Boolean.compare(o2.isUsed, o1.isUsed);
        });
        // 사용한 토끼중에 가장 우선순위가 높은 토끼 점수 더해주기
        Rabbit maxRabbit = rabbits.get(0);
        if (maxRabbit.isUsed)
            maxRabbit.score += s;

        // 사용한 토끼들 비사용으로 바꾸기
        for (Rabbit rabbit : rabbits) {
            if (rabbit.isUsed) {
                rabbit.isUsed = false;
            }
        }

    }

    // 토끼 거리 곱해주기 O(N)
    private static void updateDistance(int pid, int l) {
        for (Rabbit rabbit : rabbits) {
            if (rabbit.pid == pid) {
                rabbit.dist *= l;
            }
        }
    }

    // 베스트 토끼 고르기 O(N)
    private static long selectBestRabbit() {
        long max = 0;
        for (Rabbit rabbit : rabbits) {
            max = Math.max(rabbit.score, max);
        }
        return max;
    }

    static class Rabbit {
        int pid;
        int dist;
        int count;
        long score;
        int x, y;
        boolean isUsed;

        public Rabbit() {

        }

        public Rabbit(int pid, int dist) {
            this.pid = pid;
            this.dist = dist;
            this.x = 0;
            this.y = 0;
            this.isUsed = false;
        }

        @Override
        public String toString() {
            return "Rabbit [pid=" + pid + ", dist=" + dist + ", count=" + count + ", score=" + score + ", x=" + x
                    + ", y=" + y + "]";
        }
    }

}
// https://www.codetree.ai/training-field/frequent-problems/problems/rabit-and-race/submissions?page=1&pageSize=20
// 2805ms 1번 실패 : 정렬을 반대로 했음 boolean 정렬 방식을 잘 알고 있을 것
