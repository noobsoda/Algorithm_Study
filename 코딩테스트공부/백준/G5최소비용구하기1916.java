package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G5최소비용구하기1916 {
    static ArrayList<Node> arr[];
    static final int INF = 987654321;
    static int N, M;
    static int S, E;
    static int dist[];
    static boolean visited[];
    static PriorityQueue<Node> pq;

    public static void Dijkstra() {
        dist[S] = 0;
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node nowpoll = pq.poll();
            if (visited[nowpoll.e])
                continue;
            visited[nowpoll.e] = true;

            for (Node now : arr[nowpoll.e]) {

                if (dist[now.e] > dist[nowpoll.e] + now.w) {
                    dist[now.e] = dist[nowpoll.e] + now.w;
                    pq.add(new Node(now.e, dist[now.e]));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[s].add(new Node(e, w));
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Dijkstra();
        System.out.println(dist[E]);

    }

    static class Node {
        int e, w;

        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
// https://www.acmicpc.net/problem/1916