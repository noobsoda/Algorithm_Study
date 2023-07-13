package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G5트리와쿼리15681 {
    static int N, R, Q;
    static int dp[];
    static boolean visited[];
    static List<Integer> list[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        dp = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        dfs(R);
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            bw.write(dp[q] + "\n");
        }
        bw.flush();

    }

    public static int dfs(int n) {
        int sum = 0;
        visited[n] = true;
        for (int next : list[n]) {
            if (visited[next])
                continue;
            sum += dfs(next);
        }
        sum += 1;
        dp[n] += sum;

        return sum;
    }
}
// https://www.acmicpc.net/problem/15681