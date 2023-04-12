package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5개똥벌레3020 {
    static int N, H, min = Integer.MAX_VALUE;
    static int map[];
    static int hmap[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N];
        hmap = new int[H + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                hmap[map[i]] += 1;
                hmap[0] += -1;
            } else {
                hmap[H] += 1;
                hmap[H - map[i]] += -1;
            }
        }
        int cnt = 0;
        for (int i = H; i > 0; i--) {
            hmap[i - 1] += hmap[i];
        }
        for (int i = 1; i <= H; i++) {
            if (min > hmap[i]) {
                min = hmap[i];
                cnt = 0;
                cnt++;
            } else if (min == hmap[i])
                cnt++;

        }
        System.out.println(min + " " + cnt);

    }
    // 8% 시간 초과
    // 누적합 써야할듯
}
// https://www.acmicpc.net/problem/3020