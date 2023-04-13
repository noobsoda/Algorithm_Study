package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class B3사나운개2991 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A, B, C, D, N;
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 3; i++) {
            int res = 0;
            N = Integer.parseInt(st.nextToken());
            int n1 = N % (A + B);
            int n2 = N % (C + D);
            if (n1 >= 1 && n1 <= A)
                res++;
            if (n2 >= 1 && n2 <= C)
                res++;

            System.out.println(res);

        }

    }
}
// https://www.acmicpc.net/problem/2991