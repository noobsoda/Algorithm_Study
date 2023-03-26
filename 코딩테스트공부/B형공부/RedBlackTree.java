package 코딩테스트공부.B형공부;

import java.io.*;
import java.util.*;

public class RedBlackTree {
    public static void main(String[] args) {
        TreeMap<Node, String> tMap = new TreeMap<>((o1, o2) -> {
            if (o1.x == o2.x) {
                return (o1.y.length() - o2.y.length()) * -1;
            }
            return o1.x - o2.x;
        });
        TreeMap<Node, String> tMap2 = new TreeMap<>((o1, o2) -> {
            if (o1.x == o2.x) {
                return (o1.y.length() - o2.y.length()) * -1;
            }
            return o1.x - o2.x;
        });

        for (int i = 1; i < 100; i++) {
            tMap.put(new Node(i, "2"), "2");
        }
        tMap.put(new Node(1, "22"), "2");
        tMap.remove(new Node(50, "2"));

        System.out.println(tMap.headMap(new Node(50, "2")));

    }

    static class Node {
        int x;
        String y;

        public Node(int x, String y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node [x=" + x + ", y=" + y + "]";
        }

    }
}
