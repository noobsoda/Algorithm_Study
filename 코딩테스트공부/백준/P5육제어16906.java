package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P5육제어16906 {
    static TrieNode rootNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    static class TrieNode {
        Map<Character, TrieNode> childNode;
        boolean isLastChar;
        boolean isRemoved;

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
                tempNode.isRemoved = true;
            }
            tempNode.isLastChar = true;
        }

    }
}
