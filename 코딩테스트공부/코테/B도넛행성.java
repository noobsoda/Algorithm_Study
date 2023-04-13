package 코딩테스트공부.코테;

import java.io.*;
import java.util.*;

public class B도넛행성 {
    static int N, M;
    static int map[][];
    static boolean visited[][];
    static Queue<Node> q;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    private static void bfs(int x, int y) {
        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0) {
                    nx += N;
                } else if (ny < 0) {
                    ny += M;
                } else if (nx >= N) {
                    nx -= N;
                } else if (ny >= M) {
                    ny -= M;
                }
                if (visited[nx][ny] || map[nx][ny] == 1)
                    continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int res = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                    res++;
                }
            }
        }

        System.out.println(res);

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
