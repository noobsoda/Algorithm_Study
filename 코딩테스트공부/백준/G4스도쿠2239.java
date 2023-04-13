package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4스도쿠2239 {
    static final int MAX = 9;
    static int map[][];
    static List<Node> list;

    public static boolean ischeck(int x, int y, int num) {
        // x, y축
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == num)
                return false;
            if (map[i][y] == num)
                return false;
        }
        // 3*3
        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                if (map[i][j] == num)
                    return false;
            }
        }
        return true;

    }

    public static boolean sudoku(int depth) {
        if (depth == list.size()) {
            return true;
        }

        for (int j = 1; j <= MAX; j++) {
            Node now = list.get(depth);

            if (ischeck(now.x, now.y, j)) {
                map[now.x][now.y] = j;
                if (sudoku(depth + 1))
                    return true;
            }
            map[now.x][now.y] = 0;
        }

        return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[MAX][MAX];
        list = new LinkedList<>();

        for (int i = 0; i < MAX; i++) {
            String nv = br.readLine();
            for (int j = 0; j < MAX; j++) {
                map[i][j] = nv.charAt(j) - '0';
                if (map[i][j] == 0)
                    list.add(new Node(i, j));
            }
        }

        sudoku(0);
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                System.out.print(map[i][j]);
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
// https://www.acmicpc.net/problem/2239