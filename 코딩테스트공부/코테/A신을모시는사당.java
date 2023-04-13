package 코딩테스트공부.코테;

import java.io.*;
import java.util.*;

public class A신을모시는사당 {
    static int N;
    static int[] leftdp, rightdp, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int res;

        N = Integer.parseInt(st.nextToken());

        leftdp = new int[N];
        rightdp = new int[N];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) {
                arr[i] = 1;
            } else {
                arr[i] = -1;
            }
        }
        leftdp[0] = arr[0];
        max = arr[0];

        rightdp[0] = arr[0];
        min = arr[0];
        for (int i = 1; i < N; i++) {
            leftdp[i] = Math.max(leftdp[i - 1] + arr[i], arr[i]);
            max = Math.max(max, leftdp[i]);

            rightdp[i] = Math.min(rightdp[i - 1] + arr[i], arr[i]);
            min = Math.min(min, rightdp[i]);
        }

        res = Math.max(max, Math.abs(min));

        System.out.println(res);

    }
}
