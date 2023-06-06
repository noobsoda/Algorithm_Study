package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G2디스크트리7432 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        TrieNode rootNode = new TrieNode(false);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), "\\");
            int size = st.countTokens();
            TrieNode nowNode = rootNode;
            for (int j = 0; j < size; j++) {
                String nowString = st.nextToken();
                if (j + 1 == size) {
                    // 중복되는 폴더가 없는 경우 마지막이라고 저장
                    if (!nowNode.childNodes.containsKey(nowString)) {
                        nowNode.childNodes.put(nowString, new TrieNode(true));
                    }
                } else {
                    // 중복되는 폴더가 없는 경우만 저장
                    if (!nowNode.childNodes.containsKey(nowString)) {
                        nowNode.childNodes.put(nowString, new TrieNode(false));
                    }
                    nowNode = nowNode.childNodes.get(nowString);
                    nowNode.isLastChar = false;
                }
            }
        }
        nodePrint(0, rootNode, bw);
        bw.flush();

    }

    private static void nodePrint(int depth, TrieNode node, BufferedWriter bw) throws IOException {
        TrieNode nowNode = node;
        for (String string : nowNode.childNodes.keySet()) {
            for (int i = 0; i < depth; i++) {
                bw.write(" ");
            }
            bw.write(string + "\n");
            TrieNode childNode = nowNode.childNodes.get(string);
            // 자식노드가 마지막이 아니라면 더 들어가셈
            if (!childNode.isLastChar) {
                nodePrint(depth + 1, childNode, bw);
            }
        }

    }

    static class TrieNode {
        // 자식 노드 맵
        Map<String, TrieNode> childNodes;
        // 마지막 글자인지 여부
        boolean isLastChar;

        public TrieNode(boolean isLastChar) {
            this.childNodes = new TreeMap<>();
            this.isLastChar = isLastChar;
        }
    }

}
// https://www.acmicpc.net/problem/7432