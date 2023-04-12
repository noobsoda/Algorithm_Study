package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4탈출3055 {
    static int R, C;
    static char cmap[][];
    static boolean visited[][];
    static PriorityQueue<Node> pq;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static int bfs() {
        int cnt = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny] || cmap[nx][ny] == 'X')
                    continue;

                // 고슴도치가 굴에 방문 했을 시 최소시간 반환
                if (now.c == 'S' && cmap[nx][ny] == 'D')
                    return now.t + 1;

                if (cmap[nx][ny] == '.') {
                    pq.add(new Node(nx, ny, now.t + 1, now.c));
                    visited[nx][ny] = true;
                }

            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cmap = new char[R][C];
        visited = new boolean[R][C];
        pq = new PriorityQueue<>();

        for (int i = 0; i < R; i++) {
            String nv = br.readLine();
            for (int j = 0; j < C; j++) {
                cmap[i][j] = nv.charAt(j);
                if (cmap[i][j] == '*' || cmap[i][j] == 'S')
                    pq.add(new Node(i, j, 0, cmap[i][j]));
            }
        }

        int res = bfs();
        System.out.println((res == 0) ? "KAKTUS" : res);

    }

    static class Node implements Comparable<Node> {
        int x, y, t;
        char c;

        public Node(int x, int y, int t, char c) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            if (this.t == o.t) {
                if (this.c == '*')
                    return this.c;
                else
                    return o.c;
            }
            return this.t - o.t;
        }
    }
}
// https://www.acmicpc.net/problem/3055