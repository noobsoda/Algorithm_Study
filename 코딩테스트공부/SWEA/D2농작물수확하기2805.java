package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

class D2농작물수확하기2805 {
    static int N;
    static int res = 0;
    static int map[][];
    static boolean visited[][];
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static Queue<Node> q;

    public static void bfs() {

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (now.w >= N / 2 || visited[nx][ny])
                    continue;

                res += map[nx][ny];
                visited[nx][ny] = true;
                q.add(new Node(nx, ny, now.w + 1));
            }

        }

    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            res = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                String nv = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = nv.charAt(j) - '0';
                }
            }
            q.add(new Node(N / 2, N / 2, 0));
            visited[N / 2][N / 2] = true;
            res += map[N / 2][N / 2];
            bfs();

            System.out.println("#" + test_case + " " + res);
        }
    }

    static class Node {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;

        }

    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB&categoryId=AV7GLXqKAWYDFAXB&categoryType=CODE&problemTitle=2805&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1