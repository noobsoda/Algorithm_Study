package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class B1피보나치수2_2748 {
    static int N;
    static long dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[91];

        System.out.println(fibonacci(N));

    }

    private static long fibonacci(int n) {
        if (n == 0) {
            dp[0] = 0;
            return 0;
        } else if (n == 1) {
            dp[1] = 1;
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        } else {
            return dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
// https://www.acmicpc.net/problem/2748
