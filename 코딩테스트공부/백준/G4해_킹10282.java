package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4해_킹10282 {
    static ArrayList<ArrayList<Node>> map;
    static final int INF = 987654321;
    static int d[];
    static boolean visit[];
    static int T, N, M, C;

    public static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(C, 0));

        visit = new boolean[N + 1];
        d = new int[N + 1];
        Arrays.fill(d, INF);
        Arrays.fill(visit, false);

        d[C] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int nend = node.end;
            int nweight = node.weight;

            if (visit[nend])
                continue;
            visit[nend] = true;

            for (Node a : map.get(nend)) {
                if (d[a.end] > a.weight + nweight) {
                    d[a.end] = a.weight + nweight;
                    pq.add(new Node(a.end, d[a.end]));
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String nv[] = br.readLine().split(" ");

            N = Integer.parseInt(nv[0]);
            M = Integer.parseInt(nv[1]);
            C = Integer.parseInt(nv[2]);

            map = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                nv = br.readLine().split(" ");

                int start = Integer.parseInt(nv[0]);
                int end = Integer.parseInt(nv[1]);
                int weight = Integer.parseInt(nv[2]);

                map.get(end).add(new Node(start, weight));
            }
            dijkstra();

            int count = 0, max = 0;
            for (int i = 1; i <= N; i++) {
                if (d[i] != INF) {
                    count++;
                    max = Math.max(max, d[i]);
                }
            }
            System.out.println(count + " " + max);

            map.clear();
        }

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

// https://www.acmicpc.net/problem/10282