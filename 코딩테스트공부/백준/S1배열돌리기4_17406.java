package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1배열돌리기4_17406 {
    static int N, M, R;
    static int min = Integer.MAX_VALUE;
    static int map[][];
    static int result[];
    static boolean visited[];
    static Node[] arr;

    public static void rotate(int sn, int sm, int en, int em, int tempmap[][]) {
        if (sn == en && sm == em) {
            return;
        }

        int[] temp = new int[3];
        temp[0] = tempmap[sn][em];
        temp[1] = tempmap[en][em];
        temp[2] = tempmap[en][sm];

        for (int i = em; i > sm; i--) {
            tempmap[sn][i] = tempmap[sn][i - 1];
        }
        for (int i = en; i > sn; i--) {
            if (i == sn + 1)
                tempmap[i][em] = temp[0];
            else
                tempmap[i][em] = tempmap[i - 1][em];
        }
        for (int i = sm; i < em; i++) {
            if (i == em - 1)
                tempmap[en][i] = temp[1];
            else
                tempmap[en][i] = tempmap[en][i + 1];
        }
        for (int i = sn; i < en; i++) {
            if (i == en - 1)
                tempmap[i][sm] = temp[2];
            else
                tempmap[i][sm] = tempmap[i + 1][sm];
        }
        rotate(sn + 1, sm + 1, en - 1, em - 1, tempmap);
    }

    public static void dfs(int index) {
        if (index == R) {
            int tempmap[][] = new int[N][M];
            for (int i = 0; i < N; i++)
                System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);

            check(tempmap);
            return;
        }

        for (int i = 0; i < R; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            result[index] = i;
            dfs(index + 1);
            visited[i] = false;

        }

    }

    public static void check(int[][] tempmap) {
        for (int i = 0; i < R; i++)
            rotate(arr[result[i]].sn, arr[result[i]].sm, arr[result[i]].en, arr[result[i]].em, tempmap);

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += tempmap[i][j];
            }
            min = Math.min(sum, min);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        arr = new Node[R];
        visited = new boolean[R];
        result = new int[R];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < R; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            arr[k] = new Node(r - s, c - s, r + s, c + s);
        }

        dfs(0);

        System.out.println(min);

    }

    static class Node {
        int sn, sm, en, em;

        public Node(int sn, int sm, int en, int em) {
            this.sn = sn;
            this.sm = sm;
            this.en = en;
            this.em = em;
        }
    }

}
