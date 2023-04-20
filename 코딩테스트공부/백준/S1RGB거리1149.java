package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1RGB거리1149 {
    static int N;
    static int map[][];
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i != 0) {
                    int min = j - 1;
                    int max = j + 1;
                    if (j - 1 < 0) {
                        min = 2;
                    }
                    if (j + 1 > 2) {
                        max = 0;
                    }
                    dp[i][j] = Math.min(dp[i - 1][min], dp[i - 1][max]) + map[i][j];
                } else {
                    dp[i][j] = map[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(dp[N - 1][i], min);
        }

        System.out.println(min);

    }
}
