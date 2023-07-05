package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3서로다른부분문자열11478 {
    static String input;
    static Set<String> hSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        hSet = new HashSet<>();

        dfs(0);
        System.out.println(hSet.size());

    }

    public static void dfs(int idx) {
        if (idx == input.length()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < input.length(); i++) {
            sb.append(input.charAt(i));
            hSet.add(sb.toString());

        }
        dfs(idx + 1);

    }
}
// https://www.acmicpc.net/problem/11478