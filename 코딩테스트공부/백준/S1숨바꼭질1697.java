package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1숨바꼭질1697 {
    static Queue<Node> q;
    static boolean visited[];
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        q = new ArrayDeque<>();

        System.out.println(bfs());

    }

    public static int bfs() {
        q.add(new Node(N, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.n < 0 || now.n > 100000 || visited[now.n])
                continue;

            visited[now.n] = true;

            if (now.n == K) {
                return now.cnt;
            }

            q.add(new Node(now.n * 2, now.cnt + 1));
            q.add(new Node(now.n - 1, now.cnt + 1));
            q.add(new Node(now.n + 1, now.cnt + 1));
        }
        return -1;

    }

    static class Node {
        int n, cnt;

        public Node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
}
