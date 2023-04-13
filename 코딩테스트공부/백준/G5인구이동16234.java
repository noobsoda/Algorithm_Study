package 코딩테스트공부.백준;

import java.io.*;

import java.util.*;

public class G5인구이동16234 {
    static Queue<location> q;
    static LinkedList<location> saveq;
    static int N, L, R, count;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int[][] array;
    static boolean[][] visit;

    public static boolean bfs(int x, int y) {

        q.clear();
        saveq.clear();

        q.add(new location(x, y));
        saveq.add(new location(x, y));
        visit[x][y] = true;
        int sum = array[x][y];

        while (!q.isEmpty()) {
            location now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny])
                    continue;

                int diff = Math.abs(array[now.x][now.y] - array[nx][ny]);
                if (diff < L || diff > R)
                    continue;

                sum += array[nx][ny];
                q.add(new location(nx, ny));
                saveq.add(new location(nx, ny));
                visit[nx][ny] = true;
            }
        }
        if (saveq.size() == 1)
            return false;
        else {
            int average = sum / saveq.size();
            for (int i = 0; i < saveq.size(); i++) {
                array[saveq.get(i).x][saveq.get(i).y] = average;
            }
            return true;
        }
    }

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        L = Integer.parseInt(nv[1]);
        R = Integer.parseInt(nv[2]);

        array = new int[N][N];
        visit = new boolean[N][N];
        q = new LinkedList<>();
        saveq = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(nv[j]);
            }
        }

        boolean trigger;
        while (true) {
            for (boolean[] a : visit) {
                Arrays.fill(a, false);
            }

            trigger = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j] == true)
                        continue;
                    if (bfs(i, j))
                        trigger = true;

                }
            }

            if (trigger)
                count++;
            else
                break;
        }
        System.out.print(count);

    }

    static class location {
        int x, y;

        public location(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}

// https://www.acmicpc.net/problem/16234