package 코딩테스트공부.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1극장좌석2303 {
    static int N, M, dp[];
    static boolean arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 2];
        arr = new boolean[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            arr[now - 1] = true;
        }
        int cnt = 1;
        int res = 1;
        for (int i = 0; i < N; i++) {
            if (!arr[i]) {
                cnt++;
            } else {
                res *= fibonacci(cnt);
                cnt = 1;
            }

            if (i == N - 1 && !arr[i]) {
                res *= fibonacci(cnt);
            }
        }
        System.out.println(res);

    }

    private static int fibonacci(int n) {
        if (n == 0) {
            dp[0] = 0;
            return 0;
        } else if (n == 1) {
            dp[1] = 1;
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        } else {
            return dp[n] = fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
// https://www.acmicpc.net/problem/2302
