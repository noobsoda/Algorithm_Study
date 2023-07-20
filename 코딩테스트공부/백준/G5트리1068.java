package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5트리1068 {
    static int N, delNum, res;
    static Queue<Node> q;
    static Tree root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();

        // 노드와 부모 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = new Tree(i);
            } else {
                Tree temp = exploreTree(root, parent);
                if (temp == null) {
                    q.add(new Node(i, parent));
                } else {
                    addTree(temp, i);
                }
            }
        }
        // 현재 트리에 부모가 없을시 부모 생길때까지 넣어주기
        while (!q.isEmpty()) {
            Node now = q.poll();
            Tree temp = exploreTree(root, now.parent);
            if (temp == null) {
                q.add(now);
            } else {
                addTree(temp, now.n);
            }
        }
        // 지울 노드 입력
        st = new StringTokenizer(br.readLine());
        delNum = Integer.parseInt(st.nextToken());

        order(root, delNum);
        System.out.println(res);
    }

    static void order(Tree tree, int delNum) {
        // 삭제 노드와 만났을 시 돌아가기
        if (tree.n == delNum) {
            return;
        }
        // 리프 노드시 +1
        if (tree.left == null && tree.right == null) {
            res++;
            return;
        }

        order(tree.left, delNum);
        order(tree.right, delNum);
    }

    static void addTree(Tree addNode, int n) {
        if (addNode.left == null) {
            addNode.left = new Tree(n);
        } else {
            addNode.right = new Tree(n);
        }
    }

    static Tree exploreTree(Tree tree, int parent) {
        if (tree == null) {
            return null;
        }
        if (tree.n == parent) {
            return tree;
        } else {
            Tree left = exploreTree(tree.left, parent);
            Tree right = exploreTree(tree.right, parent);

            if (left != null) {
                return left;
            } else if (right != null) {
                return right;
            }
            return null;
        }
    }

    static class Node {
        int n, parent;

        public Node(int n, int parent) {
            this.n = n;
            this.parent = parent;
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
