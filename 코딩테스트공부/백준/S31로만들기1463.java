package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S31로만들기1463 {
    static int N;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        System.out.println(dp[N]);

    }

}
// https://www.acmicpc.net/problem/1463
