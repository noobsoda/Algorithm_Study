package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

class 모역_홈방범서비스2117 {
    static int N, M, max;
    static int map[][];

    // 다이아몬드 만들기
    public static Integer diamond(int x, int y, int K) {
        int cnt = 0;
        for (int i = 0; i < K - 1; i++) {
            int ny = y;
            for (int j = i; j < K - 1; j++) {
                ny++;
            }
            for (int j = 0; j <= i * 2; j++) {
                if (x < 0 || ny < 0 || x >= N || ny >= N) {
                    ny++;
                    continue;
                }
                if (map[x][ny] == 1) {
                    cnt++;
                }
                ny++;
            }
            x++;

        }
        for (int i = K - 1; i >= 0; i--) {
            int ny = y;
            for (int j = i; j < K - 1; j++) {
                ny++;
            }
            for (int j = 0; j <= i * 2; j++) {
                if (x < 0 || ny < 0 || x >= N || ny >= N) {
                    ny++;
                    continue;
                }
                if (map[x][ny] == 1) {
                    cnt++;
                }
                ny++;
            }
            x++;
        }
        return cnt;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            max = 0;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // K의 최대값은 N+1까지

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= N + 1; k++) {
                        int n = diamond(i - (k - 1), j - (k - 1), k);
                        int cost = k * k + (k - 1) * (k - 1);
                        if (cost <= n * M) {
                            max = Math.max(max, n);
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + max);
        }
    }
}
// https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
// 328ms