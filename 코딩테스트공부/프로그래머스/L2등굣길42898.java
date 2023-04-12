package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2등굣길42898 {
    static int dp[][];

    static int dx[] = { 1, 0 };
    static int dy[] = { 0, 1 };
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println(solution(3, 3, new int[][] { { 2, 2 } }));
        System.out.println(solution(3, 3, new int[][] { { 2, 3 } }));
    }

    public static int dfs(int x, int y, int m, int n) {
        if (x == m - 1 && y == n - 1)
            return 1;

        if (dp[x][y] != -1)
            return dp[x][y];

        dp[x][y] = 0;
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                continue;
            if (dp[nx][ny] == -2)
                continue;

            dp[x][y] += dfs(nx, ny, m, n);
            dp[x][y] %= MOD;
        }

        return dp[x][y];
    }

    public static int solution(int m, int n, int[][] puddles) {
        dp = new int[m][n];
        int answer = 0;

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);

        }
        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][0] - 1][puddles[i][1] - 1] = -2;
        }
        answer = dfs(0, 0, m, n);

        return answer;
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/42898#