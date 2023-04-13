package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S5다리놓기1010 {
    static int T, N, M;
    static long dp[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new long[M + 1];
            visited = new boolean[M + 1];

            dp[0] = 1;
            for (int i = 1; i <= M; i++) {
                dp[i] = i;
                dp[i] *= dp[i - 1];
                if (i <= N)
                    dp[i] /= dp[i];
                else {
                    for (int j = M - N; j >= 1; j--) {
                        if (visited[j] || dp[i] % j != 0)
                            continue;

                        visited[j] = true;
                        dp[i] /= j;
                    }
                }

            }
            System.out.println(dp[M]);

        }

    }
}
