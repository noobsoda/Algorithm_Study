package 코딩테스트공부.프로그래머스;

import java.io.*;
import java.util.*;

public class L3정수삼각형43105 {
    static int N;
    static int dp[][];
    static int triangle[][];

    // 위, 왼쪽 위
    static int dx[] = { -1, -1 };
    static int dy[] = { 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        triangle = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }

        }
        System.out.println(solution(triangle));

    }

    public static int solution(int[][] triangle) {

        N = triangle.length;
        dp = new int[N][N];

        System.arraycopy(triangle[N - 1], 0, dp[N - 1], 0, N);

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {

                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= i)
                        continue;

                    int now = dp[i][j] + triangle[nx][ny];
                    if (dp[nx][ny] < now) {
                        dp[nx][ny] = now;
                    }
                }

            }
        }

        return dp[0][0];
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/43105
// https://www.acmicpc.net/problem/1932