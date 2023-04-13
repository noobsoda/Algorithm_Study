package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5신기한소수2023 {
    static int N;
    static BufferedWriter bw;

    public static boolean checknum(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void recursive(int depth, int sum) throws IOException {
        if (depth == N - 1) {
            bw.write(sum / 10 + "\n");
        }
        for (int i = 1; i <= 9; i++) {
            int nsum = sum + i;
            if (!checknum(nsum))
                continue;

            recursive(depth + 1, nsum * 10);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        // 첫번째 자리
        for (int i = 2; i <= 9; i++) {
            if (!checknum(i))
                continue;
            recursive(0, i * 10);
        }

        bw.flush();
    }
}
// https://www.acmicpc.net/problem/2023