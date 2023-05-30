package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * 단순 홀짝 문제
 */
public class S3돌게임9659 {
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());

        System.out.println(N % 2 == 1 ? "SK" : "CY");

    }

}
// https://www.acmicpc.net/problem/9659
