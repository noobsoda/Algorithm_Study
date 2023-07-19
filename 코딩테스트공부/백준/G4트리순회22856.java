package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4트리순회22856 {
    static boolean[] visited;
    static int[] l, r;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        l = new int[N + 1];
        r = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            l[cur] = left;
            r[cur] = right;
        }
        System.out.println(2 * (N - 1) - inOrder(1, 0));
    }

    static int inOrder(int cur, int depth) {
        if (r[cur] != -1)
            return inOrder(r[cur], depth + 1);
        return depth;
    }
}
// https://www.acmicpc.net/problem/22856