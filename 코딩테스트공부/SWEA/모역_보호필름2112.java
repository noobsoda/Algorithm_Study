package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

public class 모역_보호필름2112 {
    static int D, W, K, answer;
    static int map[][];
    static boolean visited[];
    static int bvisited[];

    public static boolean check(int tempmap[][]) {
        for (int i = 0; i < W; i++) {
            int v = -1;
            int stack = 1;
            boolean flag = false;
            for (int j = 0; j < D; j++) {
                if (v == -1) {
                    v = tempmap[j][i];
                    continue;
                }

                if (tempmap[j][i] == v) {
                    stack++;
                    if (stack >= K) {
                        flag = true;
                        break;
                    }
                } else {
                    stack = 1;
                    v = tempmap[j][i];
                    if (stack >= K) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag)
                return false;
        }
        return true;

    }

    public static boolean dfs2(int num, int depth, int max, int tempmap[][]) {
        if (depth == max) {
            int cnt = 0;
            for (int i = 0; i < D; i++) {
                if (visited[i]) {
                    for (int j = 0; j < W; j++) {
                        tempmap[i][j] = bvisited[cnt];

                    }
                    cnt++;
                }
            }
            if (check(tempmap))
                return true;

            return false;

        }
        for (int i = 0; i < 2; i++) {
            for (int j = num; j < D; j++) {
                if (visited[j]) {
                    if (i == 0)
                        bvisited[depth] = 0;
                    if (i == 1)
                        bvisited[depth] = 1;
                    if (dfs2(j + 1, depth + 1, max, tempmap))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int num, int depth, int max) {
        if (depth == max) {
            int tempmap[][] = new int[D][W];
            for (int i = 0; i < D; i++)
                System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);

            if (dfs2(0, 0, max, tempmap))
                return true;

            return false;

        }

        for (int i = num; i < D; i++) {
            visited[i] = true;
            if (dfs(i + 1, depth + 1, max))
                return true;
            visited[i] = false;

        }
        return false;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            visited = new boolean[D];
            bvisited = new int[D];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = 0;
            if (check(map))
                ;
            else {
                for (int i = 1; i <= D; i++) {
                    if (dfs(0, 0, i)) {
                        answer = i;
                        break;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);

        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu