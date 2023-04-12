package 코딩테스트공부.프로그래머스;

import java.util.*;

@SuppressWarnings("unchecked")

// 정점마다 순회 bfs
// visit을 정점마다 초기화
// 순회하는곳과 원점 둘다 값을 지워서 누가 이겼다 누가 졌다 표시해서 하나만 남은곳이 정확한 순위

public class L3순위49191 {
    static ArrayList<Integer> arr[];
    static boolean dist[][];

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } }));
    }

    public static void bfs(int n, int max) {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[max];

        q.add(n);
        visited[n] = true;

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int now : arr[v]) {

                if (visited[now])
                    continue;

                q.add(now);
                visited[now] = true;
                dist[n][now] = true;
                dist[now][n] = true;
            }
        }

    }

    public static int solution(int n, int[][] results) {
        int answer = 0;

        arr = new ArrayList[n + 1];
        dist = new boolean[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < results.length; i++) {
            arr[results[i][0]].add(results[i][1]);
        }
        for (int i = 1; i <= n; i++) {
            bfs(i, n + 1);
        }

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (!dist[i][j])
                    cnt++;
            }
            if (cnt == 1) {
                answer++;
            }
        }

        return answer;
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/49191