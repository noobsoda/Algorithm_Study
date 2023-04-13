package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G4택배1719다익스트라 {
    static int N, M;
    static PriorityQueue<Node> pq;
    static ArrayList<Node> arr[];

    static boolean visited[];
    static int dist[];
    static int res[];
    static final int INF = 987654321;

    public static void dijkstra(int start) {
        Arrays.fill(dist, INF);
        Arrays.fill(res, INF);
        Arrays.fill(visited, false);

        dist[start] = 0;
        res[start] = 0;
        pq.add(new Node(start, 0));
        visited[start] = true;

        while (!pq.isEmpty()) {
            Node nq = pq.poll();
            visited[nq.end] = true;
            for (Node narr : arr[nq.end]) {
                if (visited[narr.end])
                    continue;

                if (dist[narr.end] > narr.w + nq.w) {
                    if (res[nq.end] != 0) {
                        res[narr.end] = res[nq.end];
                    } else {
                        res[narr.end] = narr.end;
                    }
                    dist[narr.end] = narr.w + nq.w;
                    pq.add(new Node(narr.end, narr.w + nq.w));
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        res = new int[N + 1];
        visited = new boolean[N + 1];
        arr = new ArrayList[N + 1];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, w));
            arr[b].add(new Node(a, w));

        }

        for (int i = 1; i <= N; i++) {
            dijkstra(i);
            StringBuilder sb = new StringBuilder();

            for (int j = 1; j <= N; j++) {
                if (res[j] == 0) {
                    sb.append('-');
                    sb.append(' ');
                } else {
                    sb.append(res[j]);
                    sb.append(' ');
                }

            }
            System.out.println(sb.toString());

        }
    }

    static class Node implements Comparable<Node> {
        int end, w;

        public Node(int end, int w) {
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}
// https://www.acmicpc.net/problem/1719
// 모든 정점의 최단거리 구하는거면 다익스트라 보다 플로이드가 낫다