package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3계단오르기 {
    static int N;
    static int dp[];
    static int map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        map = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = Integer.parseInt(st.nextToken());
            dp[i] = map[i];
        }
        for (int i = 1; i < N; i++) {
            dp[i] += find(i);
        }
        System.out.println(dp[N - 1]);

    }

    private static Integer find(int n) {
        if (n == 1) {
            return map[0];
        } else if (n == 2) {
            return Math.max(map[0], map[1]);
        } else {
            return Math.max(dp[n - 3] + map[n - 1], dp[n - 2]);
        }

    }
}
