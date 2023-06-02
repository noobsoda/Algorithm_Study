package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3돌게임3_9657 {
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());

        System.out.println(N % 7 == 0 || N % 7 == 2 ? "CY" : "SK");

    }

}
// https://www.acmicpc.net/problem/9657
