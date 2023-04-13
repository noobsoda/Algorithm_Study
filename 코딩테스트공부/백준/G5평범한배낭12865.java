package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5평범한배낭12865 {
    static int N, K;
    static int dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            if (i == 0) {
                for (int j = W; j <= K; j++) {
                    dp[i][j] = V;
                }
            } else {
                for (int j = 0; j <= K; j++) {
                    if (j - W < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W] + V);
                    }
                }
            }

        }
        System.out.println(dp[N - 1][K]);

    }

}
// https://www.acmicpc.net/problem/12865
// 236ms