package 코딩테스트공부.SWEA;

import java.io.*;
import java.util.*;

public class D3규영이와인영이의카드게임6808 {
    static boolean visited[];
    static int arr[];
    static int resl, resr;

    public static void dfs(int depth, int left, int right) {
        if (depth == 9) {
            if (left > right)
                resl++;
            else if (left < right)
                resr++;

            return;
        }

        for (int i = 1; i <= 18; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            if (arr[depth] > i)
                dfs(depth + 1, left + arr[depth] + i, right);
            else
                dfs(depth + 1, left, right + arr[depth] + i);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            resl = 0;
            resr = 0;
            visited = new boolean[19];
            arr = new int[9];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 9; i++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i] = n;
                visited[n] = true;
            }

            dfs(0, 0, 0);

            bw.write("#" + test_case + " " + resl + " " + resr + "\n");
        }

        bw.flush();

    }
}
