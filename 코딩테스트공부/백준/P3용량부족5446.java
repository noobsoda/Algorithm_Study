package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P3용량부족5446 {
    static TrieNode rootNode;
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n1 = Integer.parseInt(br.readLine());
            for (int i = 0; i < n1; i++) {
                String s = br.readLine();
            }
            int n2 = Integer.parseInt(br.readLine());
            for (int i = 0; i < n2; i++) {
                String s = br.readLine();

            }

        }

    }

    static class TrieNode {
        Map<Character, TrieNode> childNode;
        boolean isLastChar;

        public TrieNode() {
            childNode = new HashMap<>();
        }

        public void addChild(String s) {
            TrieNode tempNode = rootNode;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (!tempNode.childNode.containsKey(c)) {
                    tempNode.childNode.put(c, new TrieNode());
                }
                tempNode = tempNode.childNode.get(c);
            }
            tempNode.isLastChar = true;
        }
    }
}
// https://www.acmicpc.net/problem/5446
