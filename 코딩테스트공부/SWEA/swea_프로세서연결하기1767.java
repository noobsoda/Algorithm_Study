package 코딩테스트공부.SWEA;

import java.io.*;
import java.util.*;

public class swea_프로세서연결하기1767 {
    static int N, min;
    static int map[][];
    static boolean visited[][];
    static List<Node> list;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static int direction(Node n, int state, int tempmap[][]) {
        int cnt = 0;

        int nx = n.x;
        int ny = n.y;

        if (nx < 1 || ny < 1 || nx >= N - 1 || ny >= N - 1) {
            return 0;
        }

        while (true) {
            nx += dx[state];
            ny += dy[state];

            cnt++;
            if (tempmap[nx][ny] == 2 || tempmap[nx][ny] == 1)
                return -1;
            tempmap[nx][ny] = 2;
            if (nx < 1 || ny < 1 || nx >= N - 1 || ny >= N - 1) {
                return cnt;
            }
        }

    }

    public static void dfs(int start, int depth, int tempmap[][], int sum) {
        if (sum >= min)
            return;
        if (depth == N) {
            min = Math.min(min, sum);
            return;
        }
        int tempmap2[][] = new int[N][N];

        for (int i = start; i < list.size(); i++) {
            Node now = list.get(i);

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < N; k++) {
                    System.arraycopy(tempmap[k], 0, tempmap2[k], 0, N);
                }
                int n = direction(now, j, tempmap2);
                if (n == -1)
                    continue;
                dfs(i + 1, depth + 1, tempmap2, sum + n);

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1)
                        list.add(new Node(i, j));
                }
            }

            dfs(0, 0, map, 0);

            System.out.println("#" + test_case + " " + min);
        }

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
