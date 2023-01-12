package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class 역량2_2 {
    static int Ms, Ma, L, N, max;
    static int data[][];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            max = 0;
            st = new StringTokenizer(br.readLine());
            Ms = Integer.parseInt(st.nextToken());
            Ma = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            data = new int[L][N];

            for (int i = 0; i < L; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            dfs(0, Ms);
        }
    }


    private static void dfs(int depth, int sum) {
        if (depth == N) {
            max = Math.max(sum, max);
            return;
        }
        

    }
}
