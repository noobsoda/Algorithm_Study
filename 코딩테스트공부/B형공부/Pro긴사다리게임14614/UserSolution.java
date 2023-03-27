package 코딩테스트공부.B형공부.Pro긴사다리게임14614;

import java.util.*;

@SuppressWarnings("unchecked")
public class UserSolution {
    public static void main(String[] args) {
        init();

        add(6, 97);

        add(7, 47);
        System.out.println(numberOfCross(7));
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
        int leftUpKey = treeMap[mX].floorKey(mY);
        int rightUpkey = treeMap[mX + 1].floorKey(mY);

        int leftDownKey = treeMap[mX].ceilingKey(mY);
        int rightDownKey = treeMap[mX + 1].ceilingKey(mY);

        Node lNode = new Node(treeMap[mX].get(leftUpKey).idx);
        Node rNode = new Node(treeMap[mX + 1].get(rightUpkey).idx);

        treeMap[mX].put(mY, lNode);
        treeMap[mX + 1].put(mY, rNode);

        Node upLNode = treeMap[mX].get(leftUpKey);
        Node upRNode = treeMap[mX + 1].get(rightUpkey);

        Node downLNode = treeMap[mX].get(leftDownKey);
        Node downRNode = treeMap[mX + 1].get(rightDownKey);

        // 위 연결
        link(upRNode, lNode);
        link(upLNode, rNode);

        // 아래 연결
        link(lNode, downRNode);
        link(rNode, downLNode);
    }

    public static void remove(int mX, int mY) {

        Node downLNode = treeMap[mX].remove(mY);
        Node downRNode = treeMap[mX + 1].remove(mY);

        int leftUpKey = treeMap[mX].lowerKey(mY);
        int rightUpkey = treeMap[mX + 1].lowerKey(mY);

        Node upLNode = treeMap[mX].get(leftUpKey);
        Node upRNode = treeMap[mX + 1].get(rightUpkey);

        // 연결
        link(upRNode, downRNode.nxt);
        link(upLNode, downLNode.nxt);

    }

    public static int numberOfCross(int mID) {
        int cnt = 0;
        Node node = treeMap[mID].get(0);
        while (node.nxt.idx != -1) {
            cnt++;
            node = node.nxt;
        }
        return cnt;
    }

    public static int participant(int mX, int mY) {
        int key = treeMap[mX].floorKey(mY);
        Node node = treeMap[mX].get(key);
        while (node.prev != null) {
            node = node.prev;
        }
        return node.idx;
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
