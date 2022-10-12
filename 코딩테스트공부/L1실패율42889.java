package 코딩테스트공부;

import java.util.*;

class L1실패율42889 {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            double fail = 0;
            double clear = 0;
            for (int stage : stages) {
                if (stage == i) {
                    fail += 1;
                }
                if (stage >= i) {
                    clear += 1;
                }
            }
            if (fail == 0) {
                pq.add(new Node(i, 0));
            } else {
                pq.add(new Node(i, fail / clear));
            }
        }

        int cnt = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            answer[cnt++] = now.n;
        }

        return answer;
    }

    static class Node implements Comparable<Node> {
        int n;
        double failrate;

        public Node(int n, double failrate) {
            this.n = n;
            this.failrate = failrate;
        }

        public int compareTo(Node o) {
            if (this.failrate == o.failrate) {
                return this.n - o.n;
            }
            return Double.compare(o.failrate, this.failrate);
        }
    }
}