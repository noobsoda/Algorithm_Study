package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * 상향식 DP
 */
public class S1오르막수11057 {
    static int N;
    static long dp[][];
    static final int maxNum = 9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][maxNum + 1];

        Arrays.fill(dp[1], 1);

        for (int i = 1; i <= N; i++) {
            // 1일 때 값 고정
            if (i == 1) {
                for (int j = 0; j <= 9; j++) {
                    dp[i][j] = 1;
                }
            }
            // 2일 때 값 중복 가능
            else {
                for (int j = 0; j <= 9; j++) {
                    for (int k = j; k <= 9; k++) {
                        dp[i][j] += dp[i - 1][k] % 10007;
                    }
                }
            }

        }

        long sum = 0;
        for (int i = 0; i <= maxNum; i++) {
            sum += dp[N][i];
        }
        System.out.println(sum % 10007);

    }

}
// https://www.acmicpc.net/problem/11057