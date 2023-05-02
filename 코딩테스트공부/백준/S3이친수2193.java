package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3이친수2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        long d[] = new long[cnt + 1];
        d[0] = 0;
        d[1] = 1;
        for (int i = 2; i <= cnt; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }
        System.out.println(d[cnt]);
    }
}
// https://www.acmicpc.net/problem/2193