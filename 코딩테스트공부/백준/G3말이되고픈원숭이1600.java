package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3말이되고픈원숭이1600 {
    static int K, W, H, min = Integer.MAX_VALUE;
    static int map[][];
    static boolean visited[][][];
    static Queue<Node> q;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int dsx[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
    static int dsy[] = { -2, -1, 1, 2, 2, 1, -1, -2 };

    public static void bfs() {
        q.add(new Node(0, 0, K, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == H - 1 && now.y == W - 1) {
                min = now.w;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W)
                    continue;
                if (visited[nx][ny][now.k] || map[nx][ny] == 1)
                    continue;

                q.add(new Node(nx, ny, now.k, now.w + 1));
                visited[nx][ny][now.k] = true;

                if (now.x == H - 1 && now.y == W - 1) {
                    min = now.w;
                    return;
                }
            }

            if (now.k <= 0)
                continue;

            for (int i = 0; i < 8; i++) {
                int nx = now.x + dsx[i];
                int ny = now.y + dsy[i];

                if (nx < 0 || ny < 0 || nx >= H || ny >= W)
                    continue;
                if (visited[nx][ny][now.k - 1] || map[nx][ny] == 1)
                    continue;

                q.add(new Node(nx, ny, now.k - 1, now.w + 1));
                visited[nx][ny][now.k - 1] = true;

            }

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];
        q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println((min == Integer.MAX_VALUE) ? "-1" : min);

        if (min == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(min);

    }

    static class Node {
        int x, y, k, w;

        public Node(int x, int y, int k, int w) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.w = w;
        }

    }
}
// 메모리초과 시간초과는 안나는데 Queue에 너무 많이 넣음
// https://www.acmicpc.net/problem/1600
