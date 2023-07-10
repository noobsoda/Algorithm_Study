package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class S2트리의부모찾기11725 {
    static int N;
    static int depth[];
    static List<Integer> graphList[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        depth = new int[N + 1];

        graphList = new ArrayList[N + 1];
        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graphList[i] = new ArrayList<>();
        }
        // Input
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graphList[a].add(b);
            graphList[b].add(a);

        }
        bfs(1);
        for (int i = 2; i <= N; i++) {
            for (int next : graphList[i]) {
                if (depth[next] < depth[i]) {
                    bw.write(next + "\n");
                    break;
                }
            }
        }
        bw.flush();

    }

    private static void bfs(int node) {
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(node, 1));
        depth[node] = 1;
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int next : graphList[now.n]) {
                if (depth[next] == 0) {
                    depth[next] = now.cnt + 1;
                    q.add(new Node(next, now.cnt + 1));
                }

            }
        }
    }

    static class Node {
        int n, cnt;

        public Node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }

}
// https://www.acmicpc.net/problem/1351