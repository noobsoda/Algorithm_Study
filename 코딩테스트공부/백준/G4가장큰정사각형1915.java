package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4가장큰정사각형1915 {
    static int N, M, maxSquareSize;
    static int dp[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < M; j++) {
                dp[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 맨 윗줄이나 맨 왼쪽줄이거나 현재 값이 0이면 넘어가기
                if (i == 0 || j == 0 || dp[i][j] == 0) {
                    maxSquareSize = Math.max(dp[i][j], maxSquareSize);
                    continue;
                }
                // 왼, 위, 왼위에서 가장 작은 값 +1
                dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                maxSquareSize = Math.max(dp[i][j], maxSquareSize);
            }
        }
        System.out.println(maxSquareSize * maxSquareSize);

    }

}
// https://www.acmicpc.net/problem/1915