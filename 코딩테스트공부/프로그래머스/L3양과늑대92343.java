package 코딩테스트공부.프로그래머스;

import java.util.*;

@SuppressWarnings("unchecked")

public class L3양과늑대92343 {
    static Queue<Node> q;
    static ArrayList<Integer> arr[];

    public static void main(String[] args) {
        int info[] = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
        int edges[][] = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
                { 4, 6 }, { 8, 9 } };
        System.out.println(solution(info, edges));
    }

    public static int solution(int[] info, int[][] edges) {
        int answer = 0;

        arr = new ArrayList[info.length];
        q = new LinkedList<>();
        for (int i = 0; i < info.length; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i[] : edges) {
            arr[i[0]].add(i[1]);
            arr[i[1]].add(i[0]);
        }

        boolean visit[] = new boolean[info.length];
        visit[0] = true;

        q.add(new Node(1, 0, visit));

        while (!q.isEmpty()) {
            Node now = q.poll();
            answer = Math.max(answer, now.sheep);

            // 현재까지 방문한 노드
            for (int i = 0; i < now.visit.length; i++) {
                if (now.visit[i]) {
                    for (int n : arr[i]) {

                        // 이미 방문했으면 넘기기
                        if (now.visit[n])
                            continue;

                        int sheep = now.sheep;
                        int wolf = now.wolf;

                        // 양보다 늑대가 많거나 같다면
                        if (info[n] == 0)
                            sheep++;
                        else
                            wolf++;
                        if (wolf >= sheep)
                            continue;

                        // 배열 복사
                        boolean visited[] = new boolean[now.visit.length];
                        System.arraycopy(now.visit, 0, visited, 0, now.visit.length);

                        visited[n] = true;
                        q.add(new Node(sheep, wolf, visited));
                    }
                }
            }
        }

        return answer;
    }

    static class Node {
        int sheep;
        int wolf;
        boolean visit[];

        public Node(int sheep, int wolf, boolean visit[]) {
            this.sheep = sheep;
            this.wolf = wolf;
            this.visit = visit;
        }
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/92343