package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1포도주시식2156 {
    static int N, dp[], arr[], max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                dp[i] = arr[i];
            } else if (i == 1) {
                dp[i] += Math.max(dp[i - 1], arr[i - 1] + arr[i]);
            } else if (i == 2) {
                dp[i] += Math.max(dp[i - 1], Math.max(arr[i - 2] + arr[i], arr[i - 1] + arr[i]));
            } else {
                dp[i] += Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
            }

        }
        System.out.println(dp[N - 1]);
    }

}
// https://www.acmicpc.net/problem/2156