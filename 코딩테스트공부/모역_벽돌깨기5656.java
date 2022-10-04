package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class 모역_벽돌깨기5656 {
    static int N, W, H, min = Integer.MAX_VALUE, max;
    static int map[][];
    static int cel[];
    static Queue<Node> q;
    static boolean visited[][];
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public static void Print(int map[][]) {
        for (int i = 0; i < W; i++) {
            System.out.println(Arrays.toString(map[i]));

        }
        System.out.println();
    }

    // 벽돌 내리기
    public static void drop(int tempmap[][]) {
        for (int i = H - 1; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                if (tempmap[i][j] == 0)
                    continue;

                for (int k = H - 1; k >= i; k--) {
                    if (tempmap[k][j] == 0) {
                        tempmap[k][j] = tempmap[i][j];
                        tempmap[i][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    // 폭파작업
    public static int bomb(int tempmap[][]) {
        visited = new boolean[H][W];

        int cnt = 0;
        while (!q.isEmpty()) {
            cnt++;
            Node now = q.poll();
            int size = tempmap[now.x][now.y];
            tempmap[now.x][now.y] = 0;
            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = now.x;
                int ny = now.y;
                // 선택한 벽돌 크기의 -1만큼 이동
                for (int k = 0; k < size - 1; k++) {
                    nx += dx[i];
                    ny += dy[i];

                    if (nx < 0 || ny < 0 || nx >= H || ny >= W || tempmap[nx][ny] == 0 || visited[nx][ny])
                        continue;

                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;

                }
            }
        }
        return cnt;
    }

    // 구현부
    public static int BrickBreak(int tempmap[][], int n) {
        int count = 0;

        for (int j = 0; j < H; j++) {
            if (tempmap[j][n] != 0) {
                q.add(new Node(j, n));
                break;
            }

        }
        // 벽돌 펑
        count = bomb(tempmap);
        // 벽돌 내리기
        drop(tempmap);

        return count;

    }

    public static void copy(int[][] map, int[][] newmap) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newmap[i][j] = map[i][j];
            }
        }
    }

    // 순열
    public static boolean Permu(int depth, int count, int map[][]) {
        if (depth == N) {
            // 다 부쉈다면 집으로
            min = Math.min(max - count, min);
            if (min == 0)
                return true;
            else
                return false;
        }

        for (int i = 0; i < W; i++) {
            int tempmap[][] = new int[H][W];
            copy(map, tempmap);
            int cnt = BrickBreak(tempmap, i);
            cel[depth] = i;
            // 부순게 없다면 다음으로 다 부순것이 아니라면
            if (cnt == 0) {
                min = Math.min(max - count, min);
                continue;
            }

            if (Permu(depth + 1, count + cnt, tempmap))
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            min = Integer.MAX_VALUE;
            max = 0;

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            q = new ArrayDeque<>();
            map = new int[H][W];
            cel = new int[N];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0)
                        max++;
                }
            }

            Permu(0, 0, map);

            System.out.println("#" + tc + " " + min);
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
