package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3개미굴14725 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        TrieNode rootNode = new TrieNode(false);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            TrieNode nowNode = rootNode;
            for (int j = 0; j < size; j++) {
                nowNode = nowNode.addChild(st.nextToken());
            }
        }
        print("", rootNode, bw);
        bw.flush();

    }

    private static void print(String prefix, TrieNode node, BufferedWriter bw) throws IOException {
        TrieNode nowNode = node;
        for (String string : nowNode.childNodes.keySet()) {

            bw.write(prefix + string + "\n");
            TrieNode childNode = nowNode.childNodes.get(string);
            // 자식노드가 마지막이 아니라면 더 들어가셈
            if (childNode.isNotLastChild) {
                print(prefix + "--", childNode, bw);
            }
        }

    }

    static class TrieNode {
        // 자식 노드 맵
        Map<String, TrieNode> childNodes;
        // 마지막 자식인지 여부
        boolean isNotLastChild;

        public TrieNode(boolean isLastChar) {
            this.childNodes = new TreeMap<>();
            this.isNotLastChild = isLastChar;
        }

        public TrieNode addChild(String name) {
            if (!childNodes.containsKey(name)) {
                childNodes.put(name, new TrieNode(true));
            }
            return childNodes.get(name);
        }
    }

}
// https://www.acmicpc.net/problem/14725
// 356ms -> 304ms 최적화