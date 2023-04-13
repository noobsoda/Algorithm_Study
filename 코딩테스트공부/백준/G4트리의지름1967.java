package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class G4트리의지름1967 {
    static int N, maxdist = 0, maxnum;
    static boolean visited[];
    static ArrayList<Node> arr[];

    public static void dfs(int n, int w) {

        visited[n] = true;

        for (Node now : arr[n]) {
            if (visited[now.end])
                continue;

            dfs(now.end, w + now.w);
        }

        // 끝 부분에 도달 했을 시
        if (maxdist < w) {
            maxdist = w;
            maxnum = n;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[a].add(new Node(b, w));
            arr[b].add(new Node(a, w));
        }
        // for(int i = 1; i <= N; i++){
        // visited = new boolean[N+1];
        // dfs(i, 0);
        // }

        visited = new boolean[N + 1];
        dfs(1, 0);

        visited = new boolean[N + 1];
        dfs(maxnum, 0);

        System.out.println(maxdist);

    }

    static class Node {
        int end, w;

        public Node(int end, int w) {
            this.end = end;
            this.w = w;
        }
    }
}
// https://www.acmicpc.net/problem/1967