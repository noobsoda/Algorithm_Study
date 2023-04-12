package 코딩테스트공부.코테;

import java.util.*;

public class 카카오코테6 {
    static int dx[] = { 1, 0, 0, -1 };
    static int dy[] = { 0, -1, 1, 0 };
    static char cd[] = { 'd', 'l', 'r', 'u' };
    static int map[][];
    static boolean visited[][];
    static int K, N, M;
    static boolean flag = true;
    static Deque<Character> dq;

    public static void main(String[] args) {
        solution(3, 4, 2, 3, 3, 1, 5);
    }

    public static void check(int diff, int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;

            for (int j = 0; j < diff; j++) {
                // 0 3
                // 1 2
                dq.add(cd[i]);
                if (i == 0)
                    dq.add(cd[3]);
                else if (i == 1)
                    dq.add(cd[2]);
                else if (i == 2)
                    dq.add(cd[1]);
                else
                    dq.add(cd[0]);
            }
            break;

        }
    }

    public static boolean dfs(int x, int y, int r, int c, int k) {

        if (k > K) {
            return false;
        }
        if (x == r && y == c) {
            if (Math.abs(K - k) % 2 == 1) {
                flag = false;
                return true;
            } else {
                int diff = K - k;
                check(diff / 2, x, y);
                return true;
            }
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
                continue;
            dq.add(cd[i]);
            visited[nx][ny] = true;
            if (dfs(nx, ny, r, c, k + 1))
                return true;
            dq.pollLast();
            visited[nx][ny] = true;

        }
        return false;
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        map = new int[n][m];
        visited = new boolean[n][m];
        dq = new ArrayDeque<>();
        K = k;
        N = n;
        M = m;

        dfs(x - 1, y - 1, r - 1, c - 1, 0);
        // 와리가리 추가

        if (flag) {
            StringBuilder sb = new StringBuilder();

            answer = sb.toString();
        } else {
            answer = "impossible";
        }

        return answer;
    }
}
