package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P3용량부족5446 {
    static TrieNode rootNode;
    static int T, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            rootNode = new TrieNode();
            rootNode.isProtected = true;
            res = 0;
            int n1 = Integer.parseInt(br.readLine());
            for (int i = 0; i < n1; i++) {
                String removeFile = br.readLine();
                rootNode.addChild(removeFile);
            }
            int n2 = Integer.parseInt(br.readLine());
            for (int i = 0; i < n2; i++) {
                String nRemoveFile = br.readLine();
                rootNode.addProtectChild(nRemoveFile);

            }
            exploreTrie(rootNode);

            System.out.println(res);
        }

    }

    public static void exploreTrie(TrieNode tempNode) {
        if (tempNode.isLastChar && !tempNode.isProtected) {
            res += 1;
            return;
        } else if (!tempNode.isProtected) {
            res += 1;
            return;
        }
        for (char c : tempNode.childNode.keySet()) {
            exploreTrie(tempNode.childNode.get(c));

        }
    }

    static class TrieNode {
        Map<Character, TrieNode> childNode;
        boolean isLastChar;
        boolean isProtected;

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

        public void addProtectChild(String s) {
            TrieNode tempNode = rootNode;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (!tempNode.childNode.containsKey(c)) {
                    tempNode.childNode.put(c, new TrieNode());
                }
                tempNode = tempNode.childNode.get(c);
                tempNode.isProtected = true;
            }
            tempNode.isLastChar = true;
        }

    }
}
// https://www.acmicpc.net/problem/5446
