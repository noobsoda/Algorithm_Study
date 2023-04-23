package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1_1로만들기2_12852 {
    static int N;
    static int dp[];
    static int before[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        before = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
                before[i] = i / 2;
            }
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
                before[i] = i / 3;
            }
        }
        String s = "";
        int temp = N;
        while (temp > 0) {
            s += temp + " ";
            temp = before[temp];
        }

        System.out.println(dp[N]);
        System.out.println(s);
    }

}
// https://www.acmicpc.net/problem/12852