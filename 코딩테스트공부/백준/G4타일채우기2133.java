package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * DP 점화식 공부
 */
public class G4타일채우기2133 {
    static int N;
    static long dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new long[N + 1];

        System.out.println(tileFiller(N));
    }

    private static long tileFiller(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                dp[i] = 0;
            } else if (i == 2) {
                dp[i] = 3;
            } else if (i == 4) {
                dp[i] = dp[i - 2] * 4 - 1;
            } else {
                dp[i] = dp[i - 2] * 4 - dp[i - 4];
            }

        }
        return dp[n];
    }
}
// https://www.acmicpc.net/problem/2133