package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S2연속합1912 {
    static int N, dp[], max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (i == 0) {
                dp[0] = value;
            } else {
                dp[i] = Math.max(dp[i - 1] + value, value);
            }
            max = Math.max(dp[i], max);

        }
        System.out.println(max);

    }
}
// https://www.acmicpc.net/problem/1912