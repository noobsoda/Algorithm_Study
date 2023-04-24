package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S2피보나치함수1003 {
    static int N;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new int[41];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            System.out.println(fibonacci(n - 1) + " " + fibonacci(n));

        }
    }

    private static int fibonacci(int n) {
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
