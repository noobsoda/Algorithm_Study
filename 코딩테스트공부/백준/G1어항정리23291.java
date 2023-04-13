package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G1어항정리23291 {
    static int N, K, nU, nL, max, min;
    static int map[][];
    static int tempmap[][];
    static int dx[] = { 0, 0, -1, 1 };
    static int dy[] = { 1, -1, 0, 0 };

    public static void print() {
        for (int i = N - 1; i >= 0; i--) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    // 물고기 반 접기
    public static void half() {

        // 한번 접고
        for (int i = 0; i < N / 2; i++) {
            map[1][N - 1 - i] = map[0][i];
            map[0][i] = 0;
        }

        // 두번 접고
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N / 4; j++) {
                map[3 - i][N - 1 - j] = map[i][N / 2 + j];
                map[i][N / 2 + j] = 0;
            }
        }

    }

    // 물고기 내리기
    public static void takeoff(int a) {
        for (int i = 0; i < N; i++) {
            if (map[0][i] == 0) {
                for (int j = 0; j < nU; j++) {
                    map[0][i + j] = map[j][a];
                    map[j][a] = 0;
                }
                break;
            }
        }
    }

    // 어항 확산
    public static void spread(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 벗어나거나 주변 값들이 더 크거나 같은 경우 컨티뉴
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0)
                continue;

            int diff = (map[x][y] - map[nx][ny]) / 5;
            if (diff > 0) {
                tempmap[nx][ny] += diff;
                tempmap[x][y] -= diff;
            }

        }

    }

    public static void move(int x, int y) {

        // 위치 조정 해주기
        for (int i = 0; i < nU; i++) {
            for (int j = 0; j < nL; j++) {
                map[x + nL - j][y + nL + i] = map[x + i][y + j];
                map[x + i][y + j] = 0;
            }
        }

        // 2 1, 2 2, 3 2, 3 3, 4 3, 4 4
        int temp = nL;
        nL = nU;
        nU = temp + 1;

    }

    public static void BinomialTheorem(int minfish) {
        max = 0;
        min = Integer.MAX_VALUE;
        // 물고기 한마리 채우기
        for (int i = 0; i < N; i++) {
            if (map[0][i] == minfish) {
                map[0][i]++;
            }
        }
        // 가장 왼쪽에 있는 어항 올리기
        map[1][1] = map[0][0];
        map[0][0] = 0;
        nU = 2;
        nL = 1;

        // 어항 오른쪽 없을때까지 말기
        for (int i = 0; i < N; i++) {
            if (map[0][i] != 0) {
                if (i + nU + nL <= N) {
                    move(0, i);
                    i = 0;
                }
            }
        }

        // 현재 물고기 복사
        for (int i = 0; i < N; i++) {
            tempmap[i] = map[i].clone();
        }

        // 물고기 확산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0)
                    continue;
                spread(i, j);
            }
        }

        // 확산된 물고기 복사
        for (int i = 0; i < N; i++) {
            map[i] = tempmap[i].clone();
        }

        // 물고기 내리기
        for (int i = 0; i < N; i++) {
            if (map[1][i] != 0) {
                takeoff(i);
            }
        }

        // 물고기 반의 반 접기
        half();

        // 현재 물고기 복사
        for (int i = 0; i < N; i++) {
            tempmap[i] = map[i].clone();
        }

        // 물고기 확산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0)
                    continue;
                spread(i, j);
            }
        }

        // 확산된 물고기 복사
        for (int i = 0; i < N; i++) {
            map[i] = tempmap[i].clone();
        }

        // 물고기 내리기
        for (int i = 0; i < N; i++) {
            if (map[1][i] != 0) {
                // 세로 최대치 4로 설정
                nU = 4;
                takeoff(i);
            }
        }
        // 어항에서 물고기 최대, 최소 구하기
        for (int i = 0; i < N; i++) {
            if (map[0][i] > max)
                max = map[0][i];
            if (map[0][i] < min)
                min = map[0][i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        K = Integer.parseInt(nv[1]);
        map = new int[N][N];
        tempmap = new int[N][N];

        nv = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            map[0][i] = Integer.parseInt(nv[i]);
        }

        int cnt = 0;
        do {
            // 물고기가 제일 적은 어항 고르기
            int minfish = 10000;
            for (int i = 0; i < N; i++) {
                if (map[0][i] < minfish)
                    minfish = map[0][i];
            }

            BinomialTheorem(minfish);
            cnt++;
        } while (K < max - min);
        System.out.println(cnt);
    }

}

// https://www.acmicpc.net/problem/23291