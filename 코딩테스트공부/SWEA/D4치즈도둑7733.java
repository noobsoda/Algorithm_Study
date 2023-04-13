package 코딩테스트공부.SWEA;

import java.io.*;
import java.util.*;

public class D4치즈도둑7733 {
    static int N, max;
    static int map[][];
    static boolean visited[][];
    static Queue<Node> q;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static void bfs(int x, int y, int k) {
        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] <= k)
                    continue;

                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }

        }
    }

    public static void dfs(int x, int y, int k) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] <= k)
                continue;

            visited[nx][ny] = true;
            dfs(nx, ny, k);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            max = 0;
            int maxv = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visited = new boolean[N][N];

            q = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxv = Math.max(maxv, map[i][j]);
                }
            }

            int cnt = 0;
            for (int k = 0; k <= maxv - 1; k++) {
                cnt = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] <= k || visited[i][j])
                            continue;

                        visited[i][j] = true;
                        // bfs(i, j, k);
                        dfs(i, j, k);
                        cnt++;
                    }
                }
                for (int z = 0; z < N; z++)
                    Arrays.fill(visited[z], false);
                max = Math.max(cnt, max);
            }

            System.out.println("#" + test_case + " " + max);
        }

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG