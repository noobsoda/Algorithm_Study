package 코딩테스트공부.SWEA.SSAFY제출;

import java.util.*;
import java.io.*;

class D4보급로1249 {
    static final int INF = 987654321;
    static int N;
    static int map[][];
    static int dist[][];
    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static Queue<Node> pq;

    public static void bfs(int x, int y) {
        pq.add(new Node(x, y, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (dist[nx][ny] > dist[now.x][now.y] + map[nx][ny]) {
                    dist[nx][ny] = dist[now.x][now.y] + map[nx][ny];
                    pq.add(new Node(nx, ny, dist[nx][ny]));
                }

            }
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            dist = new int[N][N];
            pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
                Arrays.fill(dist[i], INF);
            }

            dist[0][0] = 0;
            bfs(0, 0);

            System.out.println("#" + test_case + " " + dist[N - 1][N - 1]);

        }

    }

    static class Node implements Comparable<Node> {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD&categoryId=AV15QRX6APsCFAYD&categoryType=CODE&problemTitle=1249&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
// dy 초기화 실수해서 오래걸림