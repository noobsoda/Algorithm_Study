package 코딩테스트공부.백준;

import java.io.*;

public class G4내리막길1520 {
    static int N, M;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int array[][];
    static int dp[][];
    static int count = 0;

    public static int dfs(int x, int y) {

        if (x == N - 1 && y == M - 1)
            return 1;

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;

            if (array[x][y] > array[nx][ny])
                dp[x][y] += dfs(nx, ny);

        }
        return dp[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        array = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(nv[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));

    }
}

// https://www.acmicpc.net/problem/1520