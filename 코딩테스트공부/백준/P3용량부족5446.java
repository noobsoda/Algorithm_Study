package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P3용량부족5446 {
    static TrieNode rootNode;
    static int T, res;
    static int isLastCharFlag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            rootNode = new TrieNode();
            res = 0;
            isLastCharFlag = -1;
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

            boolean canRmAll = true;

            for (char c : rootNode.childNode.keySet()) {
                TrieNode tempNode = rootNode.childNode.get(c);

                if (!tempNode.isRemoved) {
                    canRmAll = false;
                    break;
                }

            }
            for (char c : rootNode.childNode.keySet()) {
                res += exploreTrie(rootNode.childNode.get(c));
            }
            // 한 글자로 모든 노드를 표현할 수 있다면 1
            System.out.println((canRmAll) ? 1 : res);
        }

    }

    public static int exploreTrie(TrieNode tempNode) {
        int ret = 0;
        // 마지막 노드이고 보호되는 노드라면 +1
        if (tempNode.isLastChar && !tempNode.isRemoved) {
            ret++;
        }
        // 지워야하는 노드라면 +1 하고 탐색 X
        if (tempNode.isRemoved) {
            ret++;
        } else { // 보호되는 노드라면 더 깊게 탐색
            for (char c : tempNode.childNode.keySet()) {
                ret += exploreTrie(tempNode.childNode.get(c));

            }
        }
        return ret;

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

        public void addProtectChild(String s) {
            TrieNode tempNode = rootNode;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (!tempNode.childNode.containsKey(c)) {
                    tempNode.childNode.put(c, new TrieNode());
                }
                tempNode = tempNode.childNode.get(c);
                tempNode.isRemoved = false;

            }
        }

    }
}
// https://www.acmicpc.net/problem/5446
