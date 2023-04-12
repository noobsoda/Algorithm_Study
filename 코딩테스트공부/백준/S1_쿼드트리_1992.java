package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1_쿼드트리_1992 {
    static int N, R, C;
    static int map[][];

    public static boolean check(int r, int c, int n) {
        int last = map[r][c];

        for (int i = r; i < r + n / 2; i++) {
            for (int j = c; j < c + n / 2; j++) {
                if (last != map[i][j])
                    return false;
            }
        }

        return true;

    }

    public static void merge(int r, int c, int n, StringBuilder sb) {

        if (n == 1) {
            System.out.println();
            return;
        }
        // 체크하고 들어감 들어가기전에 (

        if (!check(r, c, n)) {
            sb.append('(');
            merge(r, c, n / 2, sb);
            sb.append(')');
        } else
            sb.append(map[r][c]);

        if (!check(r, c + n / 2, n)) {
            sb.append('(');
            merge(r, c + n / 2, n / 2, sb);
            sb.append(')');
        } else
            sb.append(map[r][c + n / 2]);

        if (!check(r + n / 2, c, n)) {
            sb.append('(');
            merge(r + n / 2, c, n / 2, sb);
            sb.append(')');
        } else
            sb.append(map[r + n / 2][c]);

        if (!check(r + n / 2, c + n / 2, n)) {
            sb.append('(');
            merge(r + n / 2, c + n / 2, n / 2, sb);
            sb.append(')');
        } else
            sb.append(map[r + n / 2][c + n / 2]);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String nv = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = nv.charAt(j) - '0';
            }
        }
        StringBuilder sb = new StringBuilder();

        if (!check(0, 0, N * 2)) {
            sb.append('(');
            merge(0, 0, N, sb);
            sb.append(')');
        } else {
            sb.append(map[0][0]);
        }

        System.out.println(sb.toString());

    }
}
// https://www.acmicpc.net/problem/1992