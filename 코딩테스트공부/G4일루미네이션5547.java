package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G4일루미네이션5547 {
    static int H, W;
    static int map[][];
    static boolean bfsvisited[][];
    static boolean visited[][];
    static Queue<Node> q;
    // 홀수일때
    static int odx[] = { -1, 0, 1, 0, -1, 1 };
    static int ody[] = { 0, -1, 0, 1, 1, 1 };

    // 짝수일때
    static int edx[] = { -1, 0, 1, 0, -1, 1 };
    static int edy[] = { 0, -1, 0, 1, -1, -1 };

    // 벽에 둘러쌓여있는지 확인하기
    public static boolean bfs(Node start, boolean flag) {
        boolean rflag = true;
        // 사방이 벽으로 막혀있을때 visited
        if (flag) {
            visited[start.x][start.y] = true;
            bfsvisited[start.x][start.y] = false;
        } else {
            bfsvisited[start.x][start.y] = true;
        }

        q.add(start);

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = now.x;
                int ny = now.y;
                // 홀수일때
                if (now.x % 2 == 0) {
                    nx += odx[i];
                    ny += ody[i];
                }
                // 짝수일때
                else {
                    nx += edx[i];
                    ny += edy[i];
                }
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                    rflag = false;
                    continue;
                }

                if (flag) {
                    if (!bfsvisited[nx][ny] || map[nx][ny] == 1)
                        continue;
                } else {
                    if (bfsvisited[nx][ny] || map[nx][ny] == 1)
                        continue;
                }

                q.add(new Node(nx, ny));
                if (flag) {
                    visited[nx][ny] = true;
                    bfsvisited[nx][ny] = false;
                } else {
                    bfsvisited[nx][ny] = true;

                }

            }
        }
        return rflag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        bfsvisited = new boolean[H][W];
        visited = new boolean[H][W];
        q = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 바깥으로 이어져 있으면 갇힌게 아닌것
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (bfsvisited[i][j] || map[i][j] == 1)
                    continue;
                boolean flag = bfs(new Node(i, j), false);

                if (flag)
                    bfs(new Node(i, j), true);
            }
        }

        // bfs visit한거 visit2 방향 아닌곳으로 cnt++

        int sum = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0)
                    continue;
                int cnt = 0;
                for (int k = 0; k < 6; k++) {
                    int nx = i;
                    int ny = j;
                    // 홀수일때
                    if (i % 2 == 0) {
                        nx += odx[k];
                        ny += ody[k];
                    }
                    // 짝수일때
                    else {
                        nx += edx[k];
                        ny += edy[k];
                    }
                    // 맵 바깥이면 +1
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                        cnt++;
                        continue;
                    }
                    // 칠하는 곳이 벽 안이거나 벽이면 칠하지 않기
                    if (map[nx][ny] == 1 || visited[nx][ny])
                        continue;

                    // 벽 바깥이고 맵 바깥이 아니면 +1
                    cnt++;

                }
                sum += cnt;

            }
        }
        // System.out.println();
        // Print();

        System.out.println(sum);

    }

    public static void Print() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (visited[i][j])
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
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
