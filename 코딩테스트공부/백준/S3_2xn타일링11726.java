package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3_2xn타일링11726 {
    static int N;
    static int dp[];

    public static int fibo(int n) {
        if (n == 1 || n == 0)
            return 1;

        if (dp[n] != 0)
            return dp[n] % 10007;

        return dp[n] = (fibo(n - 1) + fibo(n - 2)) % 10007;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];

        System.out.println(fibo(N) % 10007);

    }
}
// https://www.acmicpc.net/problem/11726