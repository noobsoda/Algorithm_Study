package 코딩테스트공부.B형공부.Pro긴사다리게임14614;

import java.util.*;

@SuppressWarnings("unchecked")
public class UserSolution {
    public static void main(String[] args) {
        init();
        add(4, 1);

        treeMap[4].get(args);

    }

    static int N = 100;
    static TreeMap<Integer, Node> treeMap[] = new TreeMap[N + 1];

    public static void init() {
        for (int i = 0; i <= N; i++) {
            treeMap[i] = new TreeMap<>();
            Node startNode = new Node(i);
            Node endNode = new Node(-1);
            treeMap[i].put(0, startNode);
            treeMap[i].put(1000000000, endNode);
            link(startNode, endNode);
        }
    }

    public static void link(Node prev, Node nxt) {
        prev.nxt = nxt;
        nxt.prev = prev;
    }

    public static void add(int mX, int mY) {
        int leftKey = treeMap[mX].floorKey(mY);
        int rightkey = treeMap[mX + 1].floorKey(mY);

        Node lNode = new Node(treeMap[mX].get(leftKey).idx);
        Node rNode = new Node(treeMap[mX].get(rightkey).idx);

        treeMap[mX].put(mY, lNode);
        treeMap[mX + 1].put(mY, rNode);

        Node upRNode = treeMap[mX + 1].get(rightkey);
        Node upLNode = treeMap[mX].get(leftKey);

        link(lNode, upRNode);
        link(rNode, upLNode);
    }

    public void remove(int mX, int mY) {
    }

    public int numberOfCross(int mID) {
        return 0;
    }

    public int participant(int mX, int mY) {
        return 0;
    }

    static class Node {
        int idx;
        Node prev, nxt;

        public Node(int idx) {
            this.idx = idx;
            this.prev = null;
            this.nxt = null;
        }

    }
}
