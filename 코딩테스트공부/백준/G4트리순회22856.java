package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4트리순회22856 {
    static int N, moveCnt, nodeCnt, res;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();
        Tree root = new Tree(1);

        for (int i = 0; i < N; i++) {
            // 탐색을 하고 없으면 list에 넣어서 무한 뺑뺑이
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
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
        moveCnt = -1;
        inOrder(root);
        System.out.println(res);
    }

    private static void inOrder(Tree tree) {
        if (nodeCnt == N) {
            res = moveCnt;
        }
        if (tree == null || tree.n == -1) {
            return;
        }
        moveCnt++;
        nodeCnt++;
        inOrder(tree.left);
        inOrder(tree.right);
        moveCnt++;

    }

    private static void addTree(int left, int right, Tree tree) {
        tree.left = new Tree(left);
        tree.right = new Tree(right);

    }

    private static Tree exploreTree(int n, Tree tree) {
        if (tree == null) {
            return null;
        }
        if (tree.n == n) {
            return tree;
        } else {
            Tree left = exploreTree(n, tree.left);
            Tree right = exploreTree(n, tree.right);

            if (left != null) {
                return left;
            } else if (right != null) {
                return right;
            }
            return null;
        }
    }

    static class Node {
        int root, left, right;

        public Node(int root, int left, int right) {
            this.root = root;
            this.left = left;
            this.right = right;
        }
    }

    static class Tree {
        int n;
        Tree left, right;

        public Tree(int n) {
            this.n = n;
            this.left = null;
            this.right = null;
        }

    }
}
