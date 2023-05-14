package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S2가장긴증가하는부분수열11055 {
    static int N;
    static int dp[], arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (i == j)
                    continue;
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(dp[i], max);
        }
        System.out.println(max);

    }
}
// https://www.acmicpc.net/problem/11053