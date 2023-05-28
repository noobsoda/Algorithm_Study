package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * 초기에 모든 팰린드롬 값 구해서 질문시 O(1) 호출하게 하기
 */
public class G4팰린드롬10942 {
    static int N, M;
    static int arr[];
    static boolean dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        dp = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        palindrome();

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            bw.write(dp[s - 1][e - 1] ? "1" : "0");
            bw.newLine();

        }
        bw.flush();

    }

    public static void palindrome() {
        // 본인일 때는 true
        for (int i = 0; i < N; i++)
            dp[i][i] = true;

        // 길이 2일 때 비교하면 true
        for (int i = 0; i < N - 1; i++)
            if (arr[i] == arr[i + 1])
                dp[i][i + 1] = true;

        // 길이 3이상일 때 부터는 s + 1, e -1 팰린드롬 값 가져오고 s,e만 비교해서 최적화
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N - i; j++) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }
}
// https://www.acmicpc.net/problem/10942