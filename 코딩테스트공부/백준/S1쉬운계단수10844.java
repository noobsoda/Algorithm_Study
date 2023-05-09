package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1쉬운계단수10844 {
    static int N;
    static long dp[][], res;
    static final int mod = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new long[N][10];

        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1] % mod;
                } else if (j == 9) {
                    dp[i][9] = dp[i - 1][8] % mod;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            res += dp[N - 1][i];
        }

        System.out.println(res % mod);

    }
}
// https://www.acmicpc.net/problem/10844