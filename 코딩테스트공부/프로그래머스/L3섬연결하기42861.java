package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L3섬연결하기42861 {
    static int parents[];
    static PriorityQueue<Node> pq;

    public static void main(String[] args) {
        System.out
                .println(solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } }));
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        pq = new PriorityQueue<>();
        for (int i = 0; i < costs.length; i++) {
            pq.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }
        parents = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (Union(now.x, now.y)) {
                answer += now.w;
            }
        }

        return answer;
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
            return w - o.w;
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
// https://programmers.co.kr/learn/courses/30/lessons/42861