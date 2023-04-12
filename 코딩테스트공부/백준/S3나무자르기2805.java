package 코딩테스트공부.백준;

import java.io.*;

public class S3나무자르기2805 {
    static int N, M;
    static int tree[];
    static long max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        tree = new int[N];

        nv = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(nv[i]);
            max = Math.max(max, tree[i]);
        }

        long start = 0;
        long end = max;

        while (start <= end) {
            long mid = (start + end) / 2;
            long res = 0;
            for (int i = 0; i < N; i++) {
                if (tree[i] > mid) {
                    res += (tree[i] - mid);

                }
            }
            if (res >= M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);
    }

}
// https://www.acmicpc.net/problem/2805