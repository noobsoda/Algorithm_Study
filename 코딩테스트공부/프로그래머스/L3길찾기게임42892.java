package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L3길찾기게임42892 {
    static int N, idx;
    static int[][] answer;

    public static int[][] solution(int[][] nodeinfo) {
        N = nodeinfo.length;
        answer = new int[2][nodeinfo.length];

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.y - o1.y);
        int cnt = 1;
        for (int edge[] : nodeinfo) {
            pq.add(new Node(cnt++, edge[0], edge[1], null, null));
        }

        Node root = pq.poll();

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            insertNode(root, now);
        }
        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);

        return answer;
    }

    public static void insertNode(Node parents, Node child) {
        if (parents.x < child.x) {
            if (parents.right == null)
                parents.right = child;
            else
                insertNode(parents.right, child);

        } else {
            if (parents.left == null)
                parents.left = child;
            else
                insertNode(parents.left, child);
        }
    }

    public static void preorder(Node now) {
        if (now == null)
            return;
        answer[0][idx++] = now.n;
        preorder(now.left);
        preorder(now.right);
    }

    public static void postorder(Node now) {
        if (now == null)
            return;
        postorder(now.left);
        postorder(now.right);
        answer[1][idx++] = now.n;
    }

    static class Node {
        int n, x, y;
        Node left, right;

        public Node(int n, int x, int y, Node left, Node right) {
            this.n = n;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }

    }
}