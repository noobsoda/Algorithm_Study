package 코딩테스트공부;

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
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            leftQ.offer(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            rightQ.offer(Integer.parseInt(st.nextToken()));
        }
        Node head = new Node();

        init(head, 0);
        while (!leftQ.isEmpty() || !rightQ.isEmpty()) {
            postInit(head, 0, -1);
        }
        for (int i = 1; i <= R; i++) {
            postOrder(head, 0, i % 2);
        }
        System.out.println(res);

    }

    public static void postOrder(Node node, int h, int state) {
        if (h >= H) {
            return;
        }
        // 부장!!
        if (h == 0) {
            if (state == LEFT) {
                if (!node.leftQ.isEmpty()) {
                    res += node.leftQ.poll();
                }
            } else {
                if (!node.rightQ.isEmpty()) {
                    res += node.rightQ.poll();
                }
            }
        }
        // 중간 AND 말단
        if (state == LEFT) {
            // 왼쪽의 왼쪽
            if (!node.left.leftQ.isEmpty()) {
                node.leftQ.offer(node.left.leftQ.poll());
            }
            // 오른쪽의 왼쪽
            if (!node.right.leftQ.isEmpty()) {
                node.rightQ.offer(node.right.leftQ.poll());
            }
        } else {
            // 왼쪽의 오른쪽
            if (!node.left.rightQ.isEmpty()) {
                node.leftQ.offer(node.left.rightQ.poll());
            }
            // 오른쪽의 오른쪽
            if (!node.right.rightQ.isEmpty()) {
                node.rightQ.offer(node.right.rightQ.poll());
            }
        }

        postOrder(node.left, h + 1, state);
        postOrder(node.right, h + 1, state);

    }

    public static void postInit(Node node, int h, int state) {
        if (node.left == null || node.right == null) {
            if (state == LEFT) {
                if (!leftQ.isEmpty()) {
                    node.leftQ.add(leftQ.poll());
                }
            } else if (state == RIGHT) {
                if (!rightQ.isEmpty()) {
                    node.leftQ.add(rightQ.poll());
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
            node.left = new Node();
        }
        init(node.left, h + 1);

        if (node.right == null) {
            node.right = new Node();
        }
        init(node.right, h + 1);

    }

    static class Node {
        int v;
        Node left, right;
        Queue<Integer> leftQ;
        Queue<Integer> rightQ;

        public Node() {
            this.v = 0;
            this.leftQ = new ArrayDeque<>();
            this.rightQ = new ArrayDeque<>();
            this.left = null;
            this.right = null;
        }

    }
}
