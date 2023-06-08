package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3게임닉네임16934 {
    static int N;
    static Node rootNode;
    static Map<String, Integer> hMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        rootNode = new Node();
        hMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            Node tempNode = rootNode;
            boolean printFlag = false;
            for (int j = 0; j < s.length(); j++) {
                if (!printFlag)
                    bw.write(s.charAt(j));
                if (tempNode.addChildNode(s.charAt(j))) {
                    if (!printFlag) {
                        bw.newLine();
                        printFlag = true;
                    }
                }
                tempNode = tempNode.childNodes.get(s.charAt(j));
            }
            // 마지막까지 도달했는데 없다면 숫자 붙이기
            if (!printFlag) {
                if (hMap.containsKey(s)) {
                    bw.write(hMap.get(s) + 1 + "\n");
                } else {
                    bw.write("\n");
                }
            }
            // 완전 문자열 저장
            if (hMap.containsKey(s)) {
                hMap.put(s, hMap.get(s) + 1);
            } else {
                hMap.put(s, 1);
            }

        }
        bw.flush();

    }

    static class Node {
        Map<Character, Node> childNodes;

        public Node() {
            childNodes = new HashMap<>();
        }

        // true라면 처음으로 가는 길 false면 이미 있는 길
        public boolean addChildNode(char c) {
            if (!childNodes.containsKey(c)) {
                childNodes.put(c, new Node());
                return true;
            }
            return false;

        }
    }

}
// https://www.acmicpc.net/problem/16934