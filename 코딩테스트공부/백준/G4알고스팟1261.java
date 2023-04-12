package 코딩테스트공부.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.PriorityQueue;

public class G4알고스팟1261 {

    static int N, M;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int map[][];
    static boolean visit[][];
    static PriorityQueue<Node> pq;

    public static int bfs() {
        pq.add(new Node(0, 0, 0));
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nwb = now.wb;

                if (nx == N - 1 && ny == M - 1)
                    return nwb;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                // 0이고 방문 안했을 때
                if (map[nx][ny] == 0 && !visit[nx][ny]) {
                    pq.add(new Node(nx, ny, nwb));
                    visit[nx][ny] = true;
                }
                // 1이고 방문 안했을 때
                else if (map[nx][ny] == 1 && !visit[nx][ny]) {
                    pq.add(new Node(nx, ny, nwb + 1));
                    visit[nx][ny] = true;
                }

            }

        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        M = Integer.parseInt(nv[0]);
        N = Integer.parseInt(nv[1]);
        map = new int[N][M];
        visit = new boolean[N][M];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }

        System.out.println(bfs());

    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int wb;

        public Node(int x, int y, int wb) {
            this.x = x;
            this.y = y;
            this.wb = wb;
        }

        @Override
        public int compareTo(Node o) {
            return wb - o.wb;
        }
    }

}
// https://www.acmicpc.net/problem/1261