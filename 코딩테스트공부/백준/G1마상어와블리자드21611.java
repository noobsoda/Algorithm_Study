package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G1마상어와블리자드21611 {
    static int N, M;
    static Queue<Node> q;
    static Queue<mapNode> mapq;
    static int map[][];
    static int earthworm[];
    static int dx[] = { 0, 1, 0, -1 };
    static int dy[] = { -1, 0, 1, 0 };
    static int bx[] = { 0, -1, 1, 0, 0 };
    static int by[] = { 0, 0, 0, -1, 1 };
    static int marble[] = { 0, 0, 0, 0 };

    // 구슬 늘리기
    public static void marblesearch() {
        int length = 0;
        int count = 1;
        int x = N / 2;
        int y = N / 2;
        int num = -1;
        for (int i = 0; i < N * 2 - 1; i++) {
            for (int j = 0; j < i / 2 + 1; j++) {
                if (j == N - 1 || length >= N * N)
                    continue;

                x = x + dx[i % 4];
                y = y + dy[i % 4];

                if (num == 0)
                    continue;

                if (num == map[x][y]) {
                    count++;
                } else {
                    earthworm[length++] = count;
                    earthworm[length++] = num;
                    count = 1;
                    if (num == -1) {
                        earthworm[0] = 0;
                        earthworm[1] = 0;
                        length = 0;
                    }
                    num = map[x][y];
                }

            }
        }

        x = N / 2;
        y = N / 2;
        length = 0;
        for (int i = 0; i < N * 2 - 1; i++) {
            for (int j = 0; j < i / 2 + 1; j++) {
                if (j == N - 1)
                    continue;
                x = x + dx[i % 4];
                y = y + dy[i % 4];

                map[x][y] = earthworm[length++];

            }
        }
        Arrays.fill(earthworm, 0);
    }

    // 구슬 터치기
    public static int bombsearch(int pullcount) {
        int x = N / 2;
        int y = N / 2;
        int count = 0;
        int num = 0;
        for (int i = 0; i < N * 2 - 1; i++) {
            for (int j = 0; j < i / 2 + 1; j++) {
                if (j == N - 1)
                    continue;

                x = x + dx[i % 4];
                y = y + dy[i % 4];

                if (num == map[x][y]) {
                    count++;
                    mapq.add(new mapNode(x, y));
                } else {
                    if (count >= 4) {
                        while (!mapq.isEmpty()) {
                            mapNode mapnode = mapq.poll();
                            marble[map[mapnode.x][mapnode.y]]++;
                            map[mapnode.x][mapnode.y] = 0;
                            pullcount++;
                        }
                        count = 1;
                    } else {
                        mapq.clear();
                        mapq.add(new mapNode(x, y));
                        count = 1;
                    }
                    num = map[x][y];
                }
            }
        }
        return pullcount;
    }

    public static void pull() {
        // 구슬 한칸씩 당기기
        int x = N / 2;
        int y = N / 2;
        int oldx = x, oldy = y;
        for (int i = 0; i < N * 2 - 1; i++) {
            for (int j = 0; j < i / 2 + 1; j++) {
                if (j == N - 1)
                    continue;
                x = x + dx[i % 4];
                y = y + dy[i % 4];

                if (oldx == N / 2 && oldy == N / 2) {
                    oldx = x;
                    oldy = y;
                    continue;
                }
                if (map[oldx][oldy] == 0) {
                    map[oldx][oldy] = map[x][y];
                    map[x][y] = 0;
                }
                oldx = x;
                oldy = y;

            }
        }
    }

    public static void blizard() {
        int pullcount = 0;

        // 먼저 폭발할수 있는지 확인?
        while (!q.isEmpty()) {
            Node now = q.poll();
            int nx = N / 2;
            int ny = N / 2;
            // 구슬 4방향 제거
            for (int i = 0; i < now.s; i++) {
                nx += bx[now.d];
                ny += by[now.d];

                map[nx][ny] = 0;
            }
            pullcount = now.s;

            for (int i = 0; i < pullcount; i++) {
                pull();
            }

            while (pullcount != 0) {
                pullcount = bombsearch(0);
                for (int i = 0; i < pullcount; i++) {
                    pull();
                }
            }
            marblesearch();

        }
        System.out.println(marble[1] * 1 + marble[2] * 2 + marble[3] * 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        q = new LinkedList<>();
        mapq = new LinkedList<>();
        map = new int[N + 1][N + 1];
        earthworm = new int[N * N + 2];

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }

        for (int i = 0; i < M; i++) {
            nv = br.readLine().split(" ");
            int d = Integer.parseInt(nv[0]);
            int s = Integer.parseInt(nv[1]);
            q.add(new Node(d, s));
        }
        blizard();
    }

    static class Node {
        int d, s;

        public Node(int d, int s) {
            this.d = d;
            this.s = s;
        }
    }

    static class mapNode {
        int x, y;

        public mapNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
// https://www.acmicpc.net/problem/21611