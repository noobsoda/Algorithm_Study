package 코딩테스트공부.백준;

import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class G4빙산2573 {
    static int array[][];
    static int temparray[][];
    static int N, M;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static boolean visit[][];
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        int count = 0;
        int year = -1;
        int melted = 0;
        array = new int[N][M];
        temparray = new int[N][M];
        visit = new boolean[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(nv[j]);
            }
        }
        // 현재 빙하 복사
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, temparray[i], 0, array[0].length);
        }

        while (true) {
            melted = 0;
            count = 0;
            for (boolean a[] : visit) {
                Arrays.fill(a, false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visit[i][j])
                        continue;

                    if (array[i][j] != 0) {
                        bfs(i, j);
                        count++;
                    }
                    if (array[i][j] == 0)
                        melted++;
                }
            }
            // 녹은 빙하 복사
            for (int i = 0; i < temparray.length; i++) {
                System.arraycopy(temparray[i], 0, array[i], 0, temparray[0].length);
            }

            year++;
            if (count >= 2) {
                System.out.println(year);
                break;
            }
            // 빙하가 전부 녹았으면 스탑
            if (melted == N * M) {
                break;
            }

        }

    }

    // 몇 덩어리인지 체크하기
    public static void bfs(int x, int y) {
        q.add(new Node(x, y));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny])
                    continue;
                if (array[nx][ny] != 0 && visit[nx][ny] == false) {
                    q.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
                // 1이상 일 때 빙하 녹이기
                if (array[nx][ny] == 0 && temparray[now.x][now.y] > 0)
                    temparray[now.x][now.y]--;
            }
        }
    }

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
// https://www.acmicpc.net/problem/2573