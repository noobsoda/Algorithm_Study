package 코딩테스트공부.백준;

import java.util.*;
import java.io.*;

public class B2백설공주와일곱난쟁이3040 {
    static int map[];
    static boolean visited[];

    public static boolean dfs(int start, int depth, int sum) {

        if (depth == 7) {
            if (sum == 100)
                return true;
            return false;
        }
        for (int i = start; i < 9; i++) {
            visited[i] = true;
            if (dfs(i + 1, depth + 1, sum + map[i])) {
                return true;
            }
            visited[i] = false;
        }

        return false;

    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        map = new int[9];
        visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = Integer.parseInt(st.nextToken());

        }
        dfs(0, 0, 0);

        for (int i = 0; i < 9; i++) {
            if (visited[i]) {
                bw.write(map[i] + "\n");
            }
        }
        bw.flush();

    }
}