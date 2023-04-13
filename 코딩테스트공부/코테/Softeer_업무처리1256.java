package 코딩테스트공부.코테;

import java.io.*;
import java.util.*;

public class Softeer_업무처리1256 {
    static int H, K, R;
    static final int LEFT = 1;
    static final int RIGHT = 0;
    static Queue<Integer> leftQ, rightQ;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        leftQ = new ArrayDeque<>();
        rightQ = new ArrayDeque<>();

        for (int i = 0; i < Math.pow(2, H) / 2; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                leftQ.offer(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                rightQ.offer(Integer.parseInt(st.nextToken()));
            }
        }
        Node head = new Node(0, null);

        init(head, 0);
        while (!leftQ.isEmpty() || !rightQ.isEmpty()) {
            postInit(head, 0, -1);
        }
        for (int i = 1; i <= R; i++) {
            postOrder(head, i % 2, 0, 0);
            if (!head.q.isEmpty())
                res += head.q.poll();
        }
        System.out.println(res);

    }

    public static void postOrder(Node node, int state, int state2, int h) {
        if (h > H) {
            return;
        }
        // 왼쪽 오른쪽 선택해서 q에 넣고 위로 올리기
        if (state == LEFT) {
            if (!node.leftQ.isEmpty()) {
                node.q.offer(node.leftQ.poll());
            }
        } else {
            if (!node.rightQ.isEmpty()) {
                node.q.offer(node.rightQ.poll());
            }
        }
        if (h == 0) {
            if (!node.q.isEmpty())
                res += node.q.poll();
        } else {
            if (state2 == LEFT) {
                if (!node.q.isEmpty()) {
                    node.up.leftQ.add(node.q.poll());
                }
            } else {
                if (!node.q.isEmpty()) {
                    node.up.rightQ.add(node.q.poll());
                }
            }
        }

        postOrder(node.left, state, LEFT, h + 1);
        postOrder(node.right, state, RIGHT, h + 1);

    }

    public static void postInit(Node node, int h, int state) {
        if (node.left == null || node.right == null) {
            if (state == LEFT) {
                for (int i = 0; i < K; i++) {
                    node.q.add(leftQ.poll());
                }
            } else if (state == RIGHT) {
                for (int i = 0; i < K; i++) {
                    node.q.add(rightQ.poll());
                }
            }

        }
        if (h >= H) {
            return;
        }
        postInit(node.left, h + 1, LEFT);
        postInit(node.right, h + 1, RIGHT);
    }

    public static void init(Node node, int h) {
        if (h >= H) {
            return;
        }
        if (node.left == null) {
            node.left = new Node(h + 1, node);
        }
        init(node.left, h + 1);

        if (node.right == null) {
            node.right = new Node(h + 1, node);
        }
        init(node.right, h + 1);

    }

    static class Node {
        int v;
        Node left, right, up;
        Queue<Integer> leftQ;
        Queue<Integer> rightQ;
        Queue<Integer> q;

        public Node(int v, Node up) {
            this.v = v;
            this.leftQ = new ArrayDeque<>();
            this.rightQ = new ArrayDeque<>();
            this.q = new ArrayDeque<>();
            this.left = null;
            this.right = null;
            this.up = up;
        }

    }
}
