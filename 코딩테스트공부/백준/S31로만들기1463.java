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
        Arrays.fill(dp, Integer.MAX_VALUE);
        dfs(N, 0);

        System.out.println(dp[1]);
    }

    private static void dfs(int n, int depth) {
        if (dp[n] <= depth || n <= 0)
            return;

        dp[n] = depth;

        if (n % 3 == 0) {
            dfs(n / 3, depth + 1);
        }
        if (n % 2 == 0) {
            dfs(n / 2, depth + 1);
        }
        dfs(n - 1, depth + 1);
    }

}
