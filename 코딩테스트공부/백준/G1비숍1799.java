package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G1비숍1799 {
    static int N, max = 0;
    static int map[][];
    static boolean colors[][];
    static int res[] = new int[2];
    static int dx[] = { -1, -1, 1, 1 };
    static int dy[] = { -1, 1, -1, 1 };

    public static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            boolean flag = true;
            int nx = x;
            int ny = y;
            while (flag) {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    break;

                if (map[nx][ny] == 2)
                    return false;
            }

        }
        return true;
    }

    static void solve(int index, boolean color, int cnt) {
        for (int i = index + 1; i < N * N; i++) {
            int x = i / N;
            int y = i % N;

            // 현재 탐색중인 색이 아니거나, 놓을 수 없거나, 대각선에 비숍이 존재하거나
            if (colors[x][y] != color || map[x][y] == 0 || !check(x, y))
                continue;

            // 백트래킹
            map[x][y] = 2;
            solve(i, color, cnt + 1);
            map[x][y] = 1;
        }
        if (color) {
            res[0] = Math.max(res[0], cnt);
        } else {
            res[1] = Math.max(res[1], cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        colors = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                colors[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0);
            }
        }

        solve(-1, true, 0);
        solve(-1, false, 0);

        System.out.println(res[0] + res[1]);
    }

}
// https://www.acmicpc.net/problem/1799