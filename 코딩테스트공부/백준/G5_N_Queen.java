package 코딩테스트공부.백준;

import java.io.*;

public class G5_N_Queen {
    static int N, count;
    static int map[];

    public static boolean checked(int x) {
        for (int i = 0; i < x; i++) {
            if (map[x] == map[i] || Math.abs(x - i) == Math.abs(map[x] - map[i]))
                return false;
        }
        return true;
    }

    public static void nqueen(int x) {
        if (x == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            map[x] = i;
            if (checked(x)) {
                nqueen(x + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N];
        count = 0;

        nqueen(0);
        System.out.println(count);

    }

}

// https://www.acmicpc.net/problem/9663
