package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G2미네랄2_18500 {
    static int R, C, N;
    static char map[][];
    static int visited[][];
    static Queue<Node> q;
    static Queue<Node> listq;
    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };
    static final int RED = 1, BLUE = 2;

    public static void Print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean falling() {
        boolean fallflag = false;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] == BLUE) {
                    if (i + 1 == R) {
                        fallflag = true;
                        return fallflag;
                    }
                    if (visited[i + 1][j] == RED) {
                        fallflag = true;
                        return fallflag;
                    }
                }
            }
        }
        for (int i = R - 2; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] == BLUE) {
                    map[i + 1][j] = map[i][j];
                    map[i][j] = '.';
                    visited[i + 1][j] = visited[i][j];
                    visited[i][j] = 0;
                }
            }
        }
        return fallflag;
    }

    public static boolean explore(int x, int y, int color) {
        boolean flyflag = false;
        q.add(new Node(x, y));
        visited[x][y] = color;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= R && !flyflag)
                    flyflag = true;
                if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny] == color)
                    continue;

                if (map[nx][ny] == 'x') {
                    visited[nx][ny] = color;
                    q.add(new Node(nx, ny));

                }
            }
        }
        return flyflag;
    }

    public static void mineral(int n, int odd) {
        for (int i = 0; i < R; i++) {
            Arrays.fill(visited[i], 0);
        }

        // 막대 던지기
        boolean flag = false;
        if (odd == 0) {
            for (int i = 0; i < C; i++) {
                if (map[R - n][i] == 'x' && !flag) {
                    map[R - n][i] = '.';
                    flag = true;
                }
            }
        } else {
            for (int i = 0; i < C; i++) {
                if (map[R - n][C - 1 - i] == 'x' && !flag) {
                    map[R - n][C - 1 - i] = '.';
                    flag = true;
                }
            }
        }

        boolean flyflag[] = new boolean[R * C];
        int cnt = 0;
        // bfs로 클러스터가 떨어지는지 체크
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && visited[i][j] == 0) {
                    listq.add(new Node(i, j));
                    flyflag[cnt] = explore(i, j, RED);
                    cnt++;
                }
            }
        }
        if (listq.size() < 2) {
            listq.clear();
            return;
        }

        // 공중에 떠있는것을 블루로 설정할것
        // 레드와 블루로 나눠놓음
        int size = listq.size();
        Node blue;
        for (int i = 0; i < size; i++) {
            // bfs로 공중에 떠있는거 찾기
            blue = listq.poll();
            if (flyflag[i]) {
                continue;
            } else {
                explore(blue.x, blue.y, BLUE);
                while (!falling())
                    ;
                break;
            }

        }
        listq.clear();

        // 1씩 내리면서 블루가 레드와 만나거나 혹은 땅에 도착할시 stop while문 사용

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        R = Integer.parseInt(nv[0]);
        C = Integer.parseInt(nv[1]);
        map = new char[R][C];
        visited = new int[R][C];
        q = new LinkedList<>();
        listq = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            nv = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                map[i][j] = nv[j].charAt(0);

            }
        }
        N = Integer.parseInt(br.readLine());
        nv = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            mineral(Integer.parseInt(nv[i]), i % 2);
        }
        Print();

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
// https://www.acmicpc.net/problem/18500