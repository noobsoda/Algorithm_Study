package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G5노드사이의거리1240 {
    static int N, M;

    static List<Node> list[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();

        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Node(e, w));
            list[e].add(new Node(s, w));

        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            bw.write(String.format(bfs(s, e) + "\n"));
        }
        bw.flush();
    }

    private static int bfs(int s, int e) {
        Queue<Node> q = new ArrayDeque<>();
        boolean visited[] = new boolean[N + 1];
        q.add(new Node(s, 0));
        visited[s] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (Node next : list[now.e]) {
                if (visited[next.e])
                    continue;
                if (next.e == e) {
                    return now.w + next.w;
                }
                visited[next.e] = true;
                q.add(new Node(next.e, now.w + next.w));
            }
        }

        return 0;

    }

    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
// https://www.acmicpc.net/problem/1240