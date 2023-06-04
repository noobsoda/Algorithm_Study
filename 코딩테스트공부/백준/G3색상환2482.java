package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3색상환2482 {
    static int N, K;
    static int dp[][];
    private static final int MOD = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.valueOf(st.nextToken());

        dp = new int[N + 1][N + 1];

        // dp 초기화
        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 2; j <= (i + 1) / 2; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }
        System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MOD);

    }
}
// https://www.acmicpc.net/problem/2482