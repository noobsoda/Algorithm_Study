package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

class 모역_미생물격리2382 {
    static int N, M, K;
    static int map[][];
    static int dmap[][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    static PriorityQueue<Node> pq;

    public static int microbe() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], 0);
            Arrays.fill(dmap[i], 0);
        }
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nx = now.x + dx[now.d];
            int ny = now.y + dy[now.d];
            map[nx][ny] += now.n;
            dmap[nx][ny] = now.d;
        }
        for (int i = 0; i < N; i++) {
            // 상
            if (map[0][i] != 0) {
                map[0][i] /= 2;
                dmap[0][i] = 1;
            }
            // 하
            if (map[N - 1][i] != 0) {
                map[N - 1][i] /= 2;
                dmap[N - 1][i] = 0;
            }
            // 좌
            if (map[i][0] != 0) {
                map[i][0] /= 2;
                dmap[i][0] = 3;
            }
            // 우
            if (map[i][N - 1] != 0) {
                map[i][N - 1] /= 2;
                dmap[i][N - 1] = 2;
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    pq.add(new Node(i, j, map[i][j], dmap[i][j]));
                    answer += map[i][j];
                }
            }
        }
        return answer;

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            dmap = new int[N][N];
            pq = new PriorityQueue<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                pq.add(new Node(x, y, n, d - 1));
            }
            int answer = 0;
            for (int i = 0; i < M; i++)
                answer = microbe();

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, n, d;

        public Node(int x, int y, int n, int d) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return n - o.n;
        }

    }
}
// https://swexpertacademy.com/main/solvingProblem/solvingProblem.do