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

        order(root, root.child.size(), delNum);
        System.out.println(res);
    }

    static void order(Tree tree, int size, int delNum) {
        // 삭제 노드와 만났을 시 돌아가기
        if (tree.n == delNum) {
            // 바로 이전 노드의 상위 노드가 하나밖에 없을 경우 리프 노드가 된다.
            if (size == 1) {
                res++;
            }
            return;
        }
        // 리프 노드시 +1
        if (tree.child.size() == 0) {
            res++;
            return;
        }

        for (Tree temp : tree.child) {
            order(temp, tree.child.size(), delNum);

        }
    }

    static void addTree(Tree addNode, int n) {
        addNode.child.add(new Tree(n));
    }

    static Tree exploreTree(Tree tree, int parent) {
        if (tree == null) {
            return null;
        }
        if (tree.n == parent) {
            return tree;
        } else {
            Tree findChild = null;
            for (Tree temp : tree.child) {
                Tree child = exploreTree(temp, parent);
                if (child != null) {
                    findChild = child;
                }

            }

            return findChild;
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
        List<Tree> child;

        public Tree(int n) {
            this.n = n;
            this.child = new ArrayList<>();
        }

    }
}
// 이진트리인줄알고 착각함 이진트리 아님
// 98%에서 틀림 원인은 루트노드가 리프노드가 안될것이라고 착각한 점
// https://www.acmicpc.net/problem/1068