package 코딩테스트공부.SWEA;

import java.io.*;
import java.util.*;

public class D4재관이의대량할인4050 {
    static int N, sum;
    static int map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            sum = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
                sum += map[i];
            }

            Arrays.sort(map);

            for (int i = N - 3; i >= 0; i -= 3) {
                sum -= map[i];
            }

            System.out.println("#" + test_case + " " + sum);

        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIseXoKEUcDFAWN