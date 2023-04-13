package 코딩테스트공부.프로그래머스;

import java.util.*;

@SuppressWarnings("unchecked")
public class L3등산코스정하기118669 {
    static int[] answer = new int[2];
    static PriorityQueue<Node> pq;
    static ArrayList<Node> arr[];
    static boolean visited[];
    static boolean gate[];
    static boolean summit[];

    public static void main(String[] args) {
        // solution(6, new
        // int[][]{{1,2,3},{2,3,5},{2,4,2},{2,5,4},{3,4,4},{4,5,3},{4,6,1},{5,6,1}}, new
        // int[]{1,3}, new int[]{5});
        solution(7, new int[][] { { 1, 4, 4 }, { 1, 6, 1 }, { 1, 7, 3 }, { 2, 5, 2 }, { 3, 7, 4 }, { 5, 6, 6 } },
                new int[] { 1 }, new int[] { 2, 3, 4 });
        // solution(7, new
        // int[][]{{1,2,5},{1,4,1},{2,3,1},{2,6,7},{4,5,1},{5,6,1},{6,7,1}}, new
        // int[]{3,7}, new int[]{1,5});
    }

    public static void bfs(int n, int start, int w) {
        visited = new boolean[n + 1];

        pq.add(new Node(start, w));
        visited[start] = true;

        while (!pq.isEmpty()) {
            boolean flag = false;
            Node now = pq.poll();
            visited[now.end] = true;
            if (now.w > answer[1])
                continue;
            if (summit[now.end]) {
                if (answer[1] > now.w) {
                    answer[0] = now.end;
                    answer[1] = now.w;
                }
                if (answer[1] == now.w) {
                    if (answer[0] > now.end) {
                        answer[0] = now.end;
                    }
                }
                flag = true;
            }

            if (flag)
                continue;

            for (Node narr : arr[now.end]) {
                if (visited[narr.end])
                    continue;
                if (gate[narr.end])
                    continue;
                if (narr.w > answer[1])
                    continue;

                int nw = Math.max(now.w, narr.w);
                pq.add(new Node(narr.end, nw));
            }
        }

    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        arr = new ArrayList[n + 1];
        gate = new boolean[n + 1];
        summit = new boolean[n + 1];
        visited = new boolean[n + 1];
        pq = new PriorityQueue<>();
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;

        for (int i : summits) {
            summit[i] = true;
        }

        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i[] : paths) {
            arr[i[0]].add(new Node(i[1], i[2]));
            arr[i[1]].add(new Node(i[0], i[2]));
        }
        for (int i : gates) {
            gate[i] = true;
        }

        for (int i = 0; i < gates.length; i++) {
            bfs(n, gates[i], 0);
        }
        return answer;
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