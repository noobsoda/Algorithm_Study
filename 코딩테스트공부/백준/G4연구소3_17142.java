package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4연구소3_17142 {
    static int N, M;
    static int map[][];
    static int tempmap[][];
    static boolean visited[][];
    static Queue<Node> q;
    static List<Node> list;
    static List<Node> nlist;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int min = Integer.MAX_VALUE;

    public static boolean check(int nmap[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (nmap[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public static void valuecheck() {
        int count = 0;
        for (Iterator<Node> it = nlist.iterator(); it.hasNext();) {
            Node now = it.next();
            q.add(new Node(now.x, now.y, 0));

        }
        for (int i = 0; i < N; i++) {
            tempmap[i] = map[i].clone();
            Arrays.fill(visited[i], false);
        }
        // bfs
        while (!q.isEmpty()) {
            // 2나 0 만나면 추가
            Node now = q.poll();
            visited[now.x][now.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (!visited[nx][ny] && map[nx][ny] != 1) {
                    q.add(new Node(nx, ny, now.count + 1));
                    visited[nx][ny] = true;
                    tempmap[nx][ny] = now.count + 1;
                    if (map[nx][ny] == 0 && now.count + 1 > count)
                        count = now.count + 1;
                }

            }
        }

        if (check(tempmap)) {
            min = Math.min(count, min);

        }
    }

    public static void laboratory(int index, int depth) {
        if (depth == M) {
            valuecheck();
            return;
        } else {
            for (int i = index + 1; i < list.size(); i++) {
                int x = list.get(i).x;
                int y = list.get(i).y;
                nlist.add(new Node(x, y, 0));
                laboratory(i, depth + 1);
                nlist.remove(nlist.size() - 1);

            }
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        map = new int[N][N];
        tempmap = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();
        list = new LinkedList<>();
        nlist = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(nv[j]);
                if (map[i][j] == 2) {
                    list.add(new Node(i, j, 0));
                }

            }
        }

        if (check(map)) {
            min = 0;
        } else {
            laboratory(-1, 0);
        }
        if (min == Integer.MAX_VALUE)
            min = -1;

        System.out.println(min);

    }

    static class Node {
        int x, y, count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
// https://www.acmicpc.net/problem/17142