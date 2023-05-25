package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1삼각그래프4883 {
    static int dx[] = { -1, -1, -1, 0 };
    static int dy[] = { -1, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int arr[][] = new int[n][3];
            int dp[][] = new int[n][3];

            if (n == 0)
                break;
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    // 위 세방향 전부, 왼쪽
                    if (i == 0) {
                        if (j == 1) {
                            dp[i][j] = arr[i][j];
                        } else if (j == 2) {
                            dp[i][j] = Math.min(dp[i][j - 1] + arr[i][j], dp[i][j - 1]);
                        }

                    } else {

                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx < 0 || ny < 0 || ny >= 3 || dp[nx][ny] == Integer.MAX_VALUE)
                                continue;

                            dp[i][j] = Math.min(dp[i][j], dp[nx][ny] + arr[i][j]);
                        }
                    }
                }
            }

            System.out.println(tc++ + ". " + dp[n - 1][1]);

        }

    }
}
// https://www.acmicpc.net/problem/4883