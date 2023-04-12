package 코딩테스트공부.프로그래머스;

import java.util.*;

@SuppressWarnings("unchecked")

public class L4동굴탐험67260 {
    static Queue<Integer> q;
    static ArrayList<Integer> arr[];
    static boolean visited[];
    static int deny[];
    static int savepoint[];

    public static void main(String[] args) {
        solution(9, new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
                new int[][] { { 8, 5 }, { 6, 7 }, { 4, 1 } });
    }

    public static boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        arr = new ArrayList[n];
        q = new LinkedList<>();

        visited = new boolean[n];
        deny = new int[n];
        savepoint = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i[] : path) {
            arr[i[0]].add(i[1]);
            arr[i[1]].add(i[0]);
        }
        for (int i[] : order) {
            deny[i[1]] = i[0];
        }
        // 시작하기 위한 조건이 있다면 무조건 false
        if (deny[0] != 0)
            return false;

        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int num : arr[now]) {
                if (visited[num])
                    continue;

                // 방문 열쇠를 아직 방문하지 않았을 경우
                if (!visited[deny[num]]) {
                    savepoint[deny[num]] = num;
                    continue;
                }

                // 방문 열쇠를 방문하고, 일반의 경우
                visited[num] = true;
                q.add(num);
            }

            // 방문 열쇠를 뒤늦게 방문한 경우
            if (!visited[savepoint[now]]) {
                visited[savepoint[now]] = true;
                q.add(savepoint[now]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i])
                answer = false;
        }

        return answer;
    }

}
// https://programmers.co.kr/learn/courses/30/lessons/67260