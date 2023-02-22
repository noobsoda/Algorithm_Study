package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S3합구하기11441 {
    static int N, M;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i == 0)
                dp[i] = Integer.parseInt(st.nextToken());
            else
                dp[i] = Integer.parseInt(st.nextToken()) + dp[i - 1];
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == 1) {
                bw.write(dp[end - 1] + "\n");
            } else {
                bw.write(dp[end - 1] - dp[start - 2] + "\n");
            }
        }
        bw.flush();

    }
}
// https://www.acmicpc.net/problem/11441