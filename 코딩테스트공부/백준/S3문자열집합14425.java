package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3문자열집합14425 {
    static int N, M, res;
    static Set<String> hSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        hSet = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            hSet.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            if (hSet.contains(br.readLine()))
                res++;

        }
        System.out.println(res);

    }

}
// https://www.acmicpc.net/problem/14425