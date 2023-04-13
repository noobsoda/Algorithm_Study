package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4PuyoPuyo11559 {
    static int res = 0;
    static char map[][];
    static boolean visited[][];
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static Queue<Node> q;

    public static void Print() {
        for (int i = 0; i < 12; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    public static int bfs(int x, int y, char c, int state) {
        int max = 1;
        q.add(new Node(x, y));
        visited[x][y] = true;

        if (state == 1)
            map[x][y] = '.';

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6)
                    continue;
                if (visited[nx][ny] || map[nx][ny] != c)
                    continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;

                if (state == 1)
                    map[nx][ny] = '.';

                max++;
            }
        }
        return max;
    }
    // 내리기

    public static void down() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k <= i; k++) {
                    if (map[11 - i + k][j] == '.') {
                        map[11 - i + k][j] = map[10 - i + k][j];
                        map[10 - i + k][j] = '.';
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        visited = new boolean[12][6];
        q = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }

        }
        boolean flag = true;
        while (flag) {
            flag = false;

            for (int k = 0; k < 12; k++)
                Arrays.fill(visited[k], false);

            // 현재 위치에서 전부 터트리고 +
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] == '.' || visited[i][j])
                        continue;

                    int cnt = bfs(i, j, map[i][j], 0);
                    if (cnt > 3) {
                        for (int k = 0; k < 12; k++)
                            Arrays.fill(visited[k], false);

                        bfs(i, j, map[i][j], 1);
                        flag = true;

                    }

                }
            }
            if (flag)
                res++;

            down();

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
// https://www.acmicpc.net/problem/11559