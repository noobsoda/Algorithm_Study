package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3피보나치수의확장1788 {
    static int N;
    static long signDp[], unSignDp[];
    static final int DIV = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        boolean sign = true;
        if (N < 0) {
            N = Math.abs(N);
            sign = false;
        }

        signDp = new long[N + 1];
        unSignDp = new long[N + 1];

        long res = ((N > 0) ? fibonacci(N, sign) : fibonacci(Math.abs(N), sign)) % DIV;

        if (res > 0) {
            System.out.println(1);
        } else if (res == 0) {
            System.out.println(0);
        } else {
            System.out.println(-1);
        }

        System.out.println(Math.abs(res));

    }

    private static long fibonacci(int n, boolean sign) {
        if (sign) {
            for (int i = 1; i <= n; i++) {
                if (i == 1) {
                    signDp[1] = 1;
                } else if (i == 2) {
                    signDp[2] = 1;
                } else {
                    signDp[i] = (signDp[i - 2] + signDp[i - 1]) % DIV;
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (i == 1) {
                    unSignDp[1] = 1;
                } else if (i == 2) {
                    unSignDp[2] = -1;
                } else {
                    unSignDp[i] = (unSignDp[i - 2] - unSignDp[i - 1]) % DIV;
                }
            }
        }
        return sign ? signDp[n] : unSignDp[n];
    }
}
// https:// www.acmicpc.net/problem/1788