package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

public class D4장훈이의높은선반1486 {
    static int N, B, min = Integer.MAX_VALUE;
    static int list[];
    static boolean visited[];

    public static void dfs(int start, int depth, int max, int res) {
        if (res > min)
            return;
        if (depth == max) {
            if (res >= B) {
                min = Math.min(min, res);
            }
            return;
        }
        for (int i = start; i < N; i++) {
            res += list[i];
            dfs(i + 1, depth + 1, max, res);
            res -= list[i];
        }

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            list = new int[N];
            visited = new boolean[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++)
                dfs(0, 0, i, 0);
            min -= B;
            System.out.println("#" + test_case + " " + min);

        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV2b7Yf6ABcBBASw&categoryId=AV2b7Yf6ABcBBASw&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=4&pageSize=10&pageIndex=3