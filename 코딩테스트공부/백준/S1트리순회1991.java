package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1트리순회1991 {
    static int N;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();
        Tree root = new Tree('A');

        for (int i = 0; i < N; i++) {
            // 탐색을 하고 없으면 list에 넣어서 무한 뺑뺑이
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);
            Tree now = exploreTree(c, root);
            if (now == null) {
                q.add(new Node(c, l, r));
            } else {
                addTree(l, r, now);
            }
        }
        while (!q.isEmpty()) {
            Node temp = q.poll();
            Tree now = exploreTree(temp.root, root);
            if (now == null) {
                q.add(new Node(temp.root, temp.left, temp.right));
            } else {
                addTree(temp.left, temp.right, now);
            }
        }
        StringBuilder sb[] = new StringBuilder[3];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        preOrder(sb[0], root);
        inOrder(sb[1], root);
        postOrder(sb[2], root);

        System.out.println(sb[0].toString());
        System.out.println(sb[1].toString());
        System.out.println(sb[2].toString());
    }

    private static void preOrder(StringBuilder sb, Tree tree) {
        if (tree == null || tree.c == '.') {
            return;
        }
        sb.append(tree.c + "");
        preOrder(sb, tree.left);
        preOrder(sb, tree.right);

    }

    private static void inOrder(StringBuilder sb, Tree tree) {
        if (tree == null || tree.c == '.') {
            return;
        }
        inOrder(sb, tree.left);
        sb.append(tree.c + "");
        inOrder(sb, tree.right);

    }

    private static void postOrder(StringBuilder sb, Tree tree) {
        if (tree == null || tree.c == '.') {
            return;
        }
        postOrder(sb, tree.left);
        postOrder(sb, tree.right);
        sb.append(tree.c + "");

    }

    private static void addTree(char left, char right, Tree tree) {
        tree.left = new Tree(left);
        tree.right = new Tree(right);

    }

    private static Tree exploreTree(char c, Tree tree) {
        if (tree == null) {
            return null;
        }
        if (tree.c == c) {
            return tree;
        } else {
            Tree left = exploreTree(c, tree.left);
            Tree right = exploreTree(c, tree.right);

            if (left != null) {
                return left;
            } else if (right != null) {
                return right;
            }
            return null;
        }
    }

    static class Node {
        char root, left, right;

        public Node(char root, char left, char right) {
            this.root = root;
            this.left = left;
            this.right = right;
        }
    }

    static class Tree {
        char c;
        Tree left, right;

        public Tree(char c) {
            this.c = c;
            this.left = null;
            this.right = null;
        }

    }

}
// https://www.acmicpc.net/problem/1991