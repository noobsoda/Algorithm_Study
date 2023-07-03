package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S4비밀번호찾기17219 {
    static int N, M;
    static Map<String, String> passwordMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        passwordMap = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            passwordMap.put(st.nextToken(), st.nextToken());
        }
        for (int i = 0; i < M; i++) {
            String site = br.readLine();
            bw.write(passwordMap.get(site) + "\n");
        }
        bw.flush();

    }
}
// https://www.acmicpc.net/problem/17219