package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P4휴대폰자판5670 {
    static TrieNode rootNode;
    static String[] words;
    static double res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        rootNode = new TrieNode();
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            rootNode.addChild(words[i]);
        }
        for (char c : rootNode.childNode.keySet()) {
            exploreTrie(rootNode.childNode.get(c), 1);
        }

        System.out.println(res / words.length);
    }

    public static void exploreTrie(TrieNode tempNode, int cnt) {
        for (char c : tempNode.childNode.keySet()) {
            if (tempNode.childNode.size() == 1) {
                // 가는 길에 마지막 단어가 있으면 곱하기 2
                if (tempNode.childNode.get(c).isLastChar) {
                    res += cnt;
                    exploreTrie(tempNode.childNode.get(c), cnt + 1);
                    // 자동 입력이면 그대로
                } else {
                    exploreTrie(tempNode.childNode.get(c), cnt);
                }
            } else {
                exploreTrie(tempNode.childNode.get(c), cnt + 1);

            }
        }
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
