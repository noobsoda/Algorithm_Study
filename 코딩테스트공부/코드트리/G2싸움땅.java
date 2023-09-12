package 코딩테스트공부.코드트리;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G2싸움땅 {
    static int N, M, K;
    static List<Gun> gunMap[][];
    static People people[];
    static int res[];
    // 위 오 아 왼
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gunMap = new ArrayList[N][N];
        people = new People[M];
        res = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                gunMap[i][j] = new ArrayList<>();
                int p = Integer.parseInt(st.nextToken());
                if (p == 0)
                    continue;
                gunMap[i][j].add(new Gun(p));
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            people[i] = new People(i, x - 1, y - 1, d, s);
        }
        for (int i = 0; i < K; i++) {
            simul();
        }
        for (int i = 0; i < M - 1; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println(res[M - 1]);

    }

    private static void simul() {
        for (int i = 0; i < M; i++) {
            People nowP = people[i];

            // 1-1
            int nx = nowP.x + dx[nowP.d];
            int ny = nowP.y + dy[nowP.d];

            // 플레이어가 격자를 벗어나는 경우 반대 방향으로 1만큼 이동
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                nowP.d = (nowP.d + 2) % 4;
                nx = nowP.x + dx[nowP.d];
                ny = nowP.y + dy[nowP.d];
            }

            // 플레이어가 있는지 탐색
            People anotherP = isPlayerCheck(nx, ny);

            // 현재 플레이어 이동
            nowP.x = nx;
            nowP.y = ny;

            // 2-2 플레이어가 있는 경우
            if (anotherP != null) {
                fightPeople(nowP, anotherP);

            } else {
                // 2-1 플레이어가 없는 경우
                // 총이 있는지 확인
                Gun powerGun = getPowerGun(nx, ny);

                // 총을 가지고 있는 경우
                // 격자에 있는 총이 더 쌔다면 총 바꾸기
                if (nowP.gun.p < powerGun.p) {
                    Gun preGun = nowP.gun;
                    nowP.gun = powerGun;
                    gunMap[nx][ny].remove(powerGun);
                    if (preGun.p != 0)
                        gunMap[nx][ny].add(preGun);
                }

            }

        }
    }

    private static Gun getPowerGun(int nx, int ny) {
        Gun powerGun = new Gun(0);
        for (Gun gun : gunMap[nx][ny]) {
            // 가장 쌘 총을 찾아라
            if (gun.p > powerGun.p) {
                powerGun = gun;
            }
        }
        return powerGun;
    }

    private static void fightPeople(People nowP, People anotherP) {
        int nowPower = nowP.s + nowP.gun.p;
        int anotherPower = anotherP.s + anotherP.gun.p;
        int diff = Math.abs(nowPower - anotherPower);
        int winIdx = -1, loseIdx = -1;

        if (nowPower > anotherPower) {
            winIdx = nowP.idx;
            loseIdx = anotherP.idx;
        } else if (nowPower == anotherPower) {
            if (nowP.s > anotherP.s) {
                winIdx = nowP.idx;
                loseIdx = anotherP.idx;
            } else {
                winIdx = anotherP.idx;
                loseIdx = nowP.idx;
            }
        } else {
            winIdx = anotherP.idx;
            loseIdx = nowP.idx;
        }
        People winPeople = people[winIdx];
        People losePeople = people[loseIdx];

        // 이긴 플레이어 초기 능력치와 총의 공격력의 합의 차이만큼 포인트
        res[winIdx] += diff;
        // 진 플레이어는 총이 있다면 격자에 내려놓고 원래 가지고 있던 방향대로 한 칸 이동
        if (losePeople.gun.p != 0) {
            gunMap[losePeople.x][losePeople.y].add(losePeople.gun);
            losePeople.gun = new Gun(0);

        }
        int nx = losePeople.x + dx[losePeople.d];
        int ny = losePeople.y + dy[losePeople.d];

        // 빈칸을 만날때까지 돌려
        while (isPlayerCheck(nx, ny) != null || nx < 0 || nx >= N || ny < 0 || ny >= N) {
            losePeople.d++;
            losePeople.d %= 4;
            nx = losePeople.x + dx[losePeople.d];
            ny = losePeople.y + dy[losePeople.d];
        }
        // 진 플레이어 이동
        losePeople.x = nx;
        losePeople.y = ny;

        Gun loserPowerGun = getPowerGun(nx, ny);
        if (loserPowerGun.p != 0) {
            losePeople.gun = loserPowerGun;
            gunMap[nx][ny].remove(loserPowerGun);
        }

        // 이긴 플레이어는 승리한 칸에 떨어져있는 총들과 원래 있던 총 중 공격력이 높은 총 획득
        Gun powerGun = getPowerGun(winPeople.x, winPeople.y);

        // 격자에 있는 총이 더 쌔다면 총 바꾸기
        if (winPeople.gun.p < powerGun.p) {
            Gun preGun = winPeople.gun;
            winPeople.gun = powerGun;
            gunMap[winPeople.x][winPeople.y].remove(powerGun);
            if (preGun.p != 0)
                gunMap[winPeople.x][winPeople.y].add(preGun);
        }

    }

    private static People isPlayerCheck(int nx, int ny) {
        for (int i = 0; i < M; i++) {
            People nowP = people[i];
            if (nowP.x == nx && nowP.y == ny)
                return nowP;
        }
        return null;
    }

    static class Gun {
        int p;

        public Gun(int p) {
            this.p = p;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + p;
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
            Gun other = (Gun) obj;
            if (p != other.p)
                return false;
            return true;
        }
    }

    static class People {
        int idx;
        int x, y, d, s;
        Gun gun;

        public People(int idx, int x, int y, int d, int s) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.gun = new Gun(0);
        }
    }
}
// https://www.codetree.ai/training-field/frequent-problems/problems/battle-ground/description?page=3&pageSize=20