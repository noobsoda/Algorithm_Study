package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S5돌게임9655 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        System.out.println(N % 2 == 1 ? "SK" : "CY");

    }

}
// https://www.acmicpc.net/problem/9655
