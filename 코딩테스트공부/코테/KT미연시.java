package 코딩테스트공부.코테;

import java.util.*;

class KT미연시 {
    static int answer[];

    public int[] solution(int n, int m, int[] p, int[][] sels) {
        answer = new int[n];
        bfs(n, m, p, sels);

        return answer;
    }

    public void bfs(int n, int m, int[] p, int[][] sels) {
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(0, p));

        while (!q.isEmpty()) {
            Node now = q.poll();

            int simul = 0;
            int max = Integer.MIN_VALUE;
            // 상황 고르기
            for (int i = 0; i < m; i++) {
                if (max < now.p[i]) {
                    max = now.p[i];
                    simul = i + 1;
                } else if (max == now.p[i]) {
                    simul = 0;
                }
            }
            // 엔딩에 도달했다면
            if (now.level == n) {
                answer[simul]++;
                continue;
            }

            for (int i = 0; i < m; i++) {
                int nowP[] = now.p.clone();
                nowP[i] += sels[simul][i];
                if (nowP[i] < 0)
                    continue;

                q.add(new Node(now.level + 1, nowP));

            }

        }
    }

    static class Node {
        int level;
        int p[];

        public Node(int level, int p[]) {
            this.level = level;
            this.p = p;
        }

    }
}