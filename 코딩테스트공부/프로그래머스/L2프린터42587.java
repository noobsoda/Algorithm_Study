package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2프린터42587 {
    static Queue<Node> q;

    public static void main(String[] args) {
        solution(new int[] { 2, 1, 3, 2 }, 2);
        solution(new int[] { 1, 1, 9, 1, 1, 1 }, 2);
    }

    public static int solution(int[] priorities, int location) {
        q = new LinkedList<>();
        int answer = 0;
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Node(priorities[i], i));
        }

        while (!q.isEmpty()) {
            Node n = q.poll();

            boolean flag = false;
            for (Node i : q) {
                if (n.v < i.v) {
                    flag = true;
                }
            }
            if (flag) {
                q.add(new Node(n.v, n.n));
            } else {
                answer++;
                if (n.n == location) {
                    q.clear();
                }
            }

        }

        return answer;
    }

    static class Node {
        int v, n;

        public Node(int v, int n) {
            this.v = v;
            this.n = n;
        }
    }
}