package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3_2XN타일링2_11727 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= N; i++)
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;

        System.out.println(dp[N]);

    }

}
