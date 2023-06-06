package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1접두사찾기14426 {
    static int N, M, res;
    static Set<String> hSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        hSet = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                sb.append(s.charAt(j));
                hSet.add(sb.toString());
            }
        }

        for (int i = 0; i < M; i++) {
            if (hSet.contains(br.readLine()))
                res++;
        }
        System.out.println(res);

    }

}
// https://www.acmicpc.net/problem/14426