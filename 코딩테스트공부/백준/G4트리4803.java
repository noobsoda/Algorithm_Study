package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4트리4803 {
    static int N, M, T;
    static boolean visited[];
    static List<Integer> list[];

    @SuppressWarnings("unchecked")

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        T = 1;

        while (true) {
            int res = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }
            list = new ArrayList[N + 1];
            visited = new boolean[N + 1];

            for (int i = 0; i <= N; i++) {
                list[i] = new ArrayList<>();

            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);

            }
            for (int i = 1; i <= N; i++) {
                if (!visited[i])
                    if (dfs(i, 0)) {
                        res += 1;
                    }
            }

            if (res > 1) {
                bw.write(String.format("Case %d: A forest of %d trees.\n", T++, res));
            } else if (res == 1) {
                bw.write(String.format("Case %d: There is one tree.\n", T++));
            } else {
                bw.write(String.format("Case %d: No trees.\n", T++));

            }
        }
        bw.flush();

    }

    private static boolean dfs(int cur, int pre) {
        visited[cur] = true;
        for (int child : list[cur]) {
            if (child == pre)
                continue;
            if (visited[child])
                return false;
            if (!dfs(child, cur))
                return false;
        }

        return true;
    }

}
// https://www.acmicpc.net/problem/4803
