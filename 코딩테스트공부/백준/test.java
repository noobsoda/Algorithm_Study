package 코딩테스트공부.백준;

import java.util.*;

public class test {

    public static int minBombPasses(int n, int[][] edges) {
        // 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        int minPasses = Integer.MAX_VALUE;

        // 각 노드에서 시작해봄
        for (int start = 1; start <= n; start++) {
            int result = bfs(start, n, graph);
            if (result != -1) {
                minPasses = Math.min(minPasses, result);
            }
        }

        return minPasses == Integer.MAX_VALUE ? -1 : minPasses;
    }

    private static int bfs(int start, int n, List<List<Integer>> graph) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { start, 0 });

        int[] bombs = new int[n + 1];
        bombs[start] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int passes = current[1];

            for (int neighbor : graph.get(node)) {
                if (bombs[neighbor] == 0) {
                    bombs[neighbor] = bombs[node] + 1;
                    queue.add(new int[] { neighbor, passes + 1 });
                } else {
                    bombs[neighbor] = Math.min(bombs[neighbor], bombs[node] + 1);
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (bombs[i] >= 2) {
                count++;
            }
        }

        return count >= 2 ? bombs[start] : -1;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[][] edges1 = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 3, 5 }, { 4, 5 } };
        System.out.println(minBombPasses(n1, edges1)); // 예제 1: 4

        int n2 = 4;
        int[][] edges2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 } };
        System.out.println(minBombPasses(n2, edges2)); // 예제 2: 2

        int n3 = 4;
        int[][] edges3 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 1 } };
        System.out.println(minBombPasses(n3, edges3)); // 예제 3: -1
    }
}