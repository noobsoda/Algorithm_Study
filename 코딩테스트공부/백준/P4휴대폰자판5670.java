package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P4휴대폰자판5670 {
    static TrieNode rootNode;
    static String[] words;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        rootNode = new TrieNode();
        words = new String[n];
        for (int i = 0; i < 4; i++) {
            words[i] = br.readLine();
            rootNode.addChild(words[i]);
        }
        for (char c : rootNode.childNode.keySet()) {
            exploreTrie(rootNode.childNode.get(c), 1);
        }

        System.out.println(res);
    }

    public static int exploreTrie(TrieNode tempNode, int cnt) {
        for (char c : tempNode.childNode.keySet()) {
            if (tempNode.childNode.get(c).isLastChar) {
                res++;
            }
            exploreTrie(tempNode.childNode.get(c), 1);
        }
        return 0;
    }

    static class TrieNode {
        Map<Character, TrieNode> childNode;
        boolean isLastChar;

        public TrieNode() {
            childNode = new HashMap<>();
        }

        public void addChild(String word) {
            TrieNode tempNode = rootNode;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (!tempNode.childNode.containsKey(c)) {
                    tempNode.childNode.put(c, new TrieNode());
                }
                tempNode = tempNode.childNode.get(c);
            }
            tempNode.isLastChar = true;

        }
    }
}
