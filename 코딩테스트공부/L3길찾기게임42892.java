package 코딩테스트공부;

import java.util.*;

public class L3길찾기게임42892 {
    static int result[][];
    static int idx;

    public static void main(String[] args) {
        solution(new int[][] { { 5, 3 }, { 11, 5 }, { 13, 3 }, { 3, 5 }, { 6, 1 }, { 1, 3 }, { 8, 6 }, { 7, 2 },
                { 2, 2 } });
    }

    public static int[][] solution(int[][] nodeinfo) {
        Node[] node = new Node[nodeinfo.length];
        for (int i = 0; i < node.length; i++) {
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        Arrays.sort(node, new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                if (o1.y == o2.y)
                    return o1.x - o2.x;

                return o2.y - o1.y;
            }

        });

        Node root = node[0];
        for (int i = 1; i < node.length; i++) {
            insertNode(root, node[i]);
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);
        System.err.println(Arrays.toString(result[0]));
        return result;
    }

    public static void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null)
                parent.left = child;
            else
                insertNode(parent.left, child);
        } else {
            if (parent.right == null)
                parent.right = child;
            else
                insertNode(parent.right, child);
        }
    }

    public static void preorder(Node root) {
        if (root != null) {
            result[0][idx++] = root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.value;
        }
    }

    static class Node {
        Node left, right;
        int x, y, value;

        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }
}
