package 코딩테스트공부.백준;

import java.util.ArrayList;
// dfs 활용법 못 찾다가 인터넷 보고 함
import java.io.*;

public class G4스도쿠우2580 {
    static int map[][];
    static ArrayList<Point> check;

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

    public static boolean sudoku(int count) {
        if (count == check.size()) {
            return true;
        } else {
            Point p = check.get(count);
            for (int i = 1; i <= 9; i++) {
                if (ischeck(p.x, p.y, i)) {
                    map[p.x][p.y] = i;
                    if (sudoku(count + 1) == true)
                        return true;
                    else
                        map[p.x][p.y] = 0;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        check = new ArrayList<>();
        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String nv[] = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                int v = Integer.parseInt(nv[j]);
                map[i][j] = v;
                if (v == 0) {
                    check.add(new Point(i, j));
                }
            }
        }

        sudoku(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(map[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

// https://www.acmicpc.net/problem/2580