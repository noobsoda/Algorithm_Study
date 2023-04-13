package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L3단어변환43163 {
    static Queue<Node> q;
    static int N, M;
    static boolean visited[];

    public static int bfs(String target, String[] words) {

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.s.equals(target))
                return now.n;

            for (int k = 0; k < M; k++) {
                String b = now.s.substring(0, k) + now.s.substring(k + 1);
                for (int i = 0; i < N; i++) {
                    if (visited[i])
                        continue;
                    String word = words[i].substring(0, k) + words[i].substring(k + 1);
                    if (!b.equals(word))
                        continue;

                    q.add(new Node(words[i], now.n + 1));
                    visited[i] = true;

                    // 단어 하나 차이만 나면 add
                }
            }

        }
        return 0;
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        N = words.length;
        M = begin.length();
        q = new LinkedList<>();
        visited = new boolean[N];

        // words 안에 target 없으면 return 0;

        boolean flag = false;
        for (int i = 0; i < N; i++) {
            if (words[i].equals(target))
                flag = true;
        }
        if (!flag)
            return 0;

        // bfs 사용

        q.add(new Node(begin, 0));
        answer = bfs(target, words);

        return answer;
    }

    static class Node {
        String s;
        int n;

        public Node(String s, int n) {
            this.s = s;
            this.n = n;
        }
    }
}