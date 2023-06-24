package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * 정렬을 먼저 한 후 숫자가 작은것부터 Trie 생성 만약에 중복되는 경로가 생성되는 경우 안됨 새로운 경로를 계속 생성할 것0과 1로
 */

public class P5육제어16906 {
    static int N;
    static TrieNode rootNode;
    static Node arrayNode[];
    static String answer[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        rootNode = new TrieNode();
        N = Integer.parseInt(st.nextToken());
        arrayNode = new Node[N];
        answer = new String[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

        }
        for (int i = 0; i < N; i++) {
            arrayNode[i] = new Node(Integer.parseInt(st.nextToken()), i);
        }

        Arrays.sort(arrayNode, (Node o1, Node o2) -> o1.n - o2.n);

        for (int i = 0; i < N; i++) {
            dfs(arrayNode[i].n, "", arrayNode[i].index);
        }

        boolean resFlag = false;
        for (int i = 0; i < N; i++) {
            if (answer[i] == null || answer[i].equals("-1")) {
                resFlag = true;
                break;
            }
        }

        if (resFlag) {
            bw.write("-1");
        } else {
            bw.write("1" + "\n");
            for (int i = 0; i < N; i++) {
                bw.write(answer[i] + "\n");
            }
        }
        bw.flush();

    }

    public static boolean dfs(int length, String word, int index) {
        if (word.length() >= 1 && rootNode.search(word)) {
            return false;
        }
        if (word.length() == length) {
            rootNode.addChild(word);
            answer[index] = word;
            return true;
        } else {
            if (dfs(length, word + "0", index))
                return true;
            if (dfs(length, word + "1", index))
                return true;

        }
        return false;
    }
    // prefix가 붙으면 안됨 그러면 마지막 Char 뒤에 붙을 수 밖에 없는 경우가 나오는 경우 -1을 출력하고 그게 아니라면 출력

    static class Node {
        int n;
        int index;

        public Node(int n, int index) {
            this.n = n;
            this.index = index;
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> childNode;
        boolean isLastChar;

        public TrieNode() {
            childNode = new HashMap<>();
        }

        public boolean search(String word) {
            TrieNode tempNode = rootNode;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (tempNode.childNode.containsKey(c)) {
                    tempNode = tempNode.childNode.get(c);
                } else {
                    return false;
                }
            }
            if (!tempNode.isLastChar) {
                return false;
            }
            return true;
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
// https://www.acmicpc.net/problem/16906