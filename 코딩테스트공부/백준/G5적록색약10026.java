package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5적록색약10026 {
    static int N;
    static char cmap[][];
    static boolean visited1[][];
    static boolean visited2[][];
    static Queue<Node> q;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static void bfs(char c1, char c2, boolean visited[][]) {

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
                    continue;

                if (cmap[nx][ny] == c1 || cmap[nx][ny] == c2) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        cmap = new char[N][N];
        visited1 = new boolean[N][N];
        visited2 = new boolean[N][N];
        q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String nv = br.readLine();
            for (int j = 0; j < N; j++) {
                cmap[i][j] = nv.charAt(j);
            }
        }

        int res1 = 0, res2 = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited1[i][j]) {
                    q.add(new Node(i, j));
                    visited1[i][j] = true;
                    bfs(cmap[i][j], cmap[i][j], visited1);
                    res1++;
                }
                if (!visited2[i][j]) {
                    q.add(new Node(i, j));
                    visited2[i][j] = true;
                    if (cmap[i][j] == 'B')
                        bfs(cmap[i][j], cmap[i][j], visited2);
                    else
                        bfs('R', 'G', visited2);
                    res2++;
                }
            }
        }

        System.out.println(res1 + " " + res2);

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
// https://www.acmicpc.net/problem/10026