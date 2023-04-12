package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class B2시험감독13458 {
    static int A, B, C;
    static int map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        map = new int[A];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long cnt = 0;
        for (int i = 0; i < A; i++) {
            map[i] -= B;
            cnt++;
            if (map[i] <= 0) {
                continue;
            }

            int n = map[i] / C;
            if (map[i] % C == 0) {
                cnt += n;
            } else
                cnt += n + 1;

        }

        System.out.println(cnt);

    }
}
// https://www.acmicpc.net/problem/13458
// long 까먹음