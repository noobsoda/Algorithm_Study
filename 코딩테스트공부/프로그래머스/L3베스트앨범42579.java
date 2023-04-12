package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L3베스트앨범42579 {
    static HashMap<String, Integer> hmap;
    static HashMap<String, Integer> hplay1;
    static HashMap<String, Integer> hplay2;
    static PriorityQueue<Node> pq;
    static int map[] = new int[100];
    static boolean visited[];

    public int[] solution(String[] genres, int[] plays) {
        int[] answer = new int[100];
        hmap = new HashMap<>();
        hplay1 = new HashMap<>();
        hplay2 = new HashMap<>();
        pq = new PriorityQueue<>();
        visited = new boolean[plays.length];

        int cnt = 0;
        for (int i = 0; i < genres.length; i++) {
            if (hmap.containsKey(genres[i])) {
                map[hmap.get(genres[i])] += plays[i];
                if (hplay1.get(genres[i]) < plays[i]) {
                    hplay2.put(genres[i], hplay1.get(genres[i]));
                    hplay1.put(genres[i], plays[i]);

                } else {
                    if (!hplay2.containsKey(genres[i]))
                        hplay2.put(genres[i], plays[i]);
                    else if (hplay2.get(genres[i]) < plays[i])
                        hplay2.put(genres[i], plays[i]);

                }
            }
            // 처음 등록
            else {
                hmap.put(genres[i], cnt);
                map[hmap.get(genres[i])] += plays[i];
                hplay1.put(genres[i], plays[i]);
                cnt++;
            }
        }

        for (String s : hmap.keySet()) {
            pq.add(new Node(s, map[hmap.get(s)]));
        }

        cnt = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (int i = 0; i < plays.length; i++)
                if (!visited[i] && plays[i] == hplay1.get(now.s)) {
                    answer[cnt++] = i;
                    visited[i] = true;
                }

            if (hplay2.containsKey(now.s))
                for (int i = 0; i < plays.length; i++)
                    if (!visited[i] && plays[i] == hplay2.get(now.s)) {
                        answer[cnt++] = i;
                        visited[i] = true;
                    }

        }

        int res[] = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            res[i] = answer[i];
        }

        return res;
    }

    static class Node implements Comparable<Node> {
        String s;
        int n;

        public Node(String s, int n) {
            this.s = s;
            this.n = n;
        }

        @Override
        public int compareTo(Node o) {
            return o.n - n;
        }
    }
}
// 장르 내에 재생 횟수가 같은게 2곡이라면?
// https://programmers.co.kr/learn/courses/30/lessons/42579