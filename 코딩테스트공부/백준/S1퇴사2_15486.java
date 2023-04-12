package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1퇴사2_15486 {
    static int N, max = 0;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            if (max < dp[i])
                max = dp[i];

            int nxt = i + T;
            if (nxt > N)
                continue;

            dp[nxt] = Math.max(max + P, dp[nxt]);

        }
        dp[N] = Math.max(max, dp[N]);

        System.out.println(dp[N]);

    }
}
// https://www.acmicpc.net/problem/15486