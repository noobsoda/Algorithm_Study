package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G4게리맨더링17471 {
    static int N, dist[], min = Integer.MAX_VALUE;
    static boolean visited[];
    static boolean bfsvisited[];
    static ArrayList<Integer> arr[];
    static Queue<Integer> q;

    public static int bfs(int start, boolean flag) {
        int sum = dist[start];
        bfsvisited[start] = true;
        q = new ArrayDeque<>();

        q.add(start);

        while (!q.isEmpty()) {
            int pollnow = q.poll();

            for (int now : arr[pollnow]) {
                if (visited[now] != flag || bfsvisited[now])
                    continue;
                bfsvisited[now] = true;
                q.add(now);
                sum += dist[now];

            }
        }

        return sum;

    }

    public static void combi(int start, int depth, int limit) {
        if (depth == limit) {
            bfsvisited = new boolean[N + 1];
            // bfs로 이어져 있나 체크

            int cnt = 0;
            int sum[] = new int[2];
            for (int i = 1; i <= N; i++) {
                if (bfsvisited[i])
                    continue;

                cnt++;
                if (cnt > 2)
                    return;
                sum[cnt - 1] = bfs(i, visited[i]);

            }
            min = Math.min(Math.abs(sum[0] - sum[1]), min);

            return;

        }
        for (int i = start; i <= N; i++) {
            visited[i] = true;
            combi(i + 1, depth + 1, limit);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();

        }
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.parseInt(st.nextToken());

        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            // 갯수 버리기
            int l = Integer.parseInt(st.nextToken());

            for (int j = 0; j < l; j++)
                arr[i].add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i < N; i++)
            combi(1, 0, i);

        System.out.println((min == Integer.MAX_VALUE) ? -1 : min);

    }
}
// https://www.acmicpc.net/problem/17471