package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * 조건
 * 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
 * M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다.
 * 세 개의 정수 a, b, c 또는 a, b, c, d가 주어지는데, a가 1인 경우 b번째 수부터 c번째 수에 d를 더하고, a가 2인
 * 경우에는 b번째 수부터 c번째 수의 합을 구하여 출력하면 된다.
 * 입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.
 */

public class P4구간합구하기2_10999 {
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

        }

    }
}
// https://www.acmicpc.net/problem/10999