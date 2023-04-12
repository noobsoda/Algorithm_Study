package 코딩테스트공부.프로그래머스;

import java.util.*;
import java.util.Map.Entry;

class L2메뉴리뉴얼72411 {
    static int max;
    static boolean visited[];
    static HashMap<String, Integer> map;
    static HashMap<Integer, String> res;

    public static void main(String[] args) {
        String orders[] = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
        int course[] = { 2, 3, 4 };
        String[] answer = solution(orders, course);

        for (String s : answer) {
            System.out.println(s);
        }
    }

    public static void check(String s, int clength) {
        StringBuilder sb = new StringBuilder();
        char c[] = new char[clength];
        int cnt = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                c[cnt] = s.charAt(i);
                cnt++;
            }
        }
        Arrays.sort(c);
        sb.append(c);

        s = sb.toString();

        if (map.containsKey(s)) {
            int n = map.get(s);
            map.put(s, n + 1);
            max = Math.max(max, n + 1);
        } else {
            map.put(s, 1);
        }
    }

    public static void dfs(int start, int depth, int size, int maxdepth, String s) {
        if (depth == maxdepth) {
            check(s, maxdepth);
        }

        for (int i = start; i < size; i++) {
            visited[i] = true;
            dfs(i + 1, depth + 1, size, maxdepth, s);
            visited[i] = false;
        }

    }

    public static String[] solution(String[] orders, int[] course) {
        int cnt = 0;
        String[] answer = {};
        map = new HashMap<>();
        res = new HashMap<>();
        // dfs 조합으로 course 만큼 뽑아서 hashmap에 저장

        for (int c = 0; c < course.length; c++) {
            max = 0;
            for (int i = 0; i < orders.length; i++) {
                if (course[c] > orders[i].length())
                    continue;
                visited = new boolean[orders[i].length()];
                dfs(0, 0, orders[i].length(), course[c], orders[i]);
            }

            // hash 검사
            for (Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max) {
                    res.put(cnt, entry.getKey());
                    cnt++;
                }
            }
            map.clear();
        }
        answer = new String[cnt];

        for (Entry<Integer, String> entry : res.entrySet()) {
            answer[entry.getKey()] = entry.getValue();
        }

        Arrays.sort(answer);
        return answer;
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/72411#