package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3파티이1238 {
    static int N, M, X;
    static ArrayList<ArrayList<Node>> map;
    static ArrayList<ArrayList<Node>> reserve_map;
    static int[] dist, res_dist;
    static boolean[] visit;
    static final int INF = 987654321;

    static void dijkstra(ArrayList<ArrayList<Node>> list, int[] d) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));

        Arrays.fill(d, INF);
        visit = new boolean[N + 1];
        d[X] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int nend = node.end;
            int nweight = node.weight;

            if (visit[nend])
                continue;

            visit[nend] = true;
            for (Node v : list.get(nend)) {
                if (d[v.end] > nweight + v.weight) {
                    d[v.end] = nweight + v.weight;
                    pq.add(new Node(v.end, d[v.end]));
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        X = Integer.parseInt(nv[2]);

        map = new ArrayList<>();
        reserve_map = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
            reserve_map.add(new ArrayList<>());
        }
        dist = new int[N + 1];
        res_dist = new int[N + 1];

        for (int i = 0; i < M; i++) {
            nv = br.readLine().split(" ");
            int start = Integer.parseInt(nv[0]);
            int end = Integer.parseInt(nv[1]);
            int weight = Integer.parseInt(nv[2]);

            map.get(start).add(new Node(end, weight));
            reserve_map.get(end).add(new Node(start, weight));
        }
        dijkstra(map, dist);
        dijkstra(reserve_map, res_dist);

        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dist[i] + res_dist[i]);
        }
        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

}

// https://www.acmicpc.net/problem/2580