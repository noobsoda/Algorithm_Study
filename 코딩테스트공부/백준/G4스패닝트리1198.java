package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4스패닝트리1198 {
    static int N, V;
    static int parents[];
    static PriorityQueue<Node> pq;

    public static int spanningtree() {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (Union(now.x, now.y)) {
                sum += now.w;
                count++;
                if (count == V - 1)
                    return sum;
            }

        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        parents = new int[N];

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new Node(x - 1, y - 1, w));

        }
        System.out.println(spanningtree());

    }

    static class Node implements Comparable<Node> {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }

    }

    public static int Find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = Find(parents[x]);
    }

    public static boolean Union(int x, int y) {
        int xroot = Find(x);
        int yroot = Find(y);

        if (xroot == yroot)
            return false;

        parents[yroot] = xroot;
        return true;

    }
}
// https://www.acmicpc.net/problem/1197
// Find 최적화 Union으로 검색 최적화