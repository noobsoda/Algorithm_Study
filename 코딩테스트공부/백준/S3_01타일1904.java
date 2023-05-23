package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3_01타일1904 {
    static long dp[];
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new long[N + 1];

        System.out.println(fibonacci(N));

    }

    private static long fibonacci(int n) {
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[1] = 1;
            } else if (i == 2) {
                dp[2] = 2;
            } else {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
            }
        }
        return dp[n];
    }
}
// https://www.acmicpc.net/problem/1904