package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5마상어와비바라기21610 {
    static int N, M;
    static int map[][];

    static boolean cloud[][];
    static boolean tempcloud[][];

    static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
    static Queue<Node> q;

    public static void print() {
        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    public static void watercopy(int x, int y) {
        for (int i = 1; i <= 4; i++) {
            int nx = x + dx[i * 2];
            int ny = y + dy[i * 2];

            if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] <= 0)
                continue;
            map[x][y]++;

        }
    }

    public static void movecloud(int d, int s) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cloud[i][j]) {
                    int r = i + dx[d] * s;
                    int c = j + dy[d] * s;

                    while (r < 1)
                        r += N;
                    while (r > N)
                        r -= N;
                    while (c < 1)
                        c += N;
                    while (c > N)
                        c -= N;

                    tempcloud[r][c] = true;
                }
            }
        }

    }

    public static void bibalagi() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            // 1. 구름이 이동한다.
            movecloud(now.d, now.s);

            // 2. 구름에서 비가 내린다
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (tempcloud[i][j]) {
                        map[i][j]++;
                    }
                }
            }

            // 3. 구름이 사라진다

            for (int i = 1; i <= N; i++) {
                System.arraycopy(tempcloud[i], 0, cloud[i], 0, tempcloud[i].length);
            }
            for (int i = 1; i <= N; i++) {
                Arrays.fill(cloud[i], false);
            }

            // 4. 물복사버그 마법 시전
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (tempcloud[i][j])
                        watercopy(i, j);
                }
            }

            // 5. 구름 생성
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (!tempcloud[i][j] && map[i][j] >= 2) {
                        map[i][j] -= 2;
                        cloud[i][j] = true;
                    }

                }
            }
            for (int i = 1; i <= N; i++) {
                Arrays.fill(tempcloud[i], false);
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        map = new int[N + 1][N + 1];
        cloud = new boolean[N + 1][N + 1];
        tempcloud = new boolean[N + 1][N + 1];
        q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(nv[j - 1]);
            }
        }

        for (int i = 0; i < M; i++) {
            nv = br.readLine().split(" ");
            int d = Integer.parseInt(nv[0]);
            int s = Integer.parseInt(nv[1]);

            q.add(new Node(d, s));
        }
        for (int i = N - 1; i <= N; i++) {
            for (int j = 1; j <= 2; j++) {
                cloud[i][j] = true;
            }
        }
        bibalagi();

        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                res += map[i][j];
            }
        }

        System.out.println(res);

    }

    static class Node {
        int d, s;

        public Node(int d, int s) {
            this.d = d;
            this.s = s;
        }
    }
}

// https://www.acmicpc.net/problem/21610