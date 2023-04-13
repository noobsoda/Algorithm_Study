package 코딩테스트공부.프로그래머스;

import java.util.*;

class L3여행경로43164 {
    static boolean visited[];

    public static boolean dfs(String[][] tickets, String word, int depth, String[] answer) {
        // 알파벳 우선순위
        HashMap<String, Integer> hmap = new HashMap<>();

        int cnt = 0;
        for (String s[] : tickets) {
            if (visited[cnt]) {
                cnt++;
                continue;
            }
            if (s[0].equals(word)) {
                hmap.put(s[1], cnt);

            }

            cnt++;
        }
        String words[] = new String[hmap.size()];

        cnt = 0;
        for (String s : hmap.keySet()) {
            words[cnt] = s;
            cnt++;
        }
        Arrays.sort(words);

        for (String s : words) {
            visited[hmap.get(s)] = true;
            answer[depth] = s;
            if (dfs(tickets, s, depth + 1, answer))
                return true;
            visited[hmap.get(s)] = false;
        }

        if (hmap.size() == 0) {
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i])
                    return false;
            }
            return true;
        }

        return false;
    }

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        String[] answer = new String[tickets.length + 1];
        answer[0] = "ICN";

        dfs(tickets, "ICN", 1, answer);

        return answer;
    }
}

// dfs 사용해서 알파벳 우선순위로 가서 전부 방문 못하면 다른곳 방문
// https://programmers.co.kr/learn/courses/30/lessons/43164