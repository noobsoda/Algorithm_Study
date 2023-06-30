package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S4나는야포켓몬마스터이다솜1620 {
    static int N, M;
    static Map<String, Integer> hMap;
    static Map<Integer, String> reverseHMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        hMap = new HashMap<>();
        reverseHMap = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            hMap.put(s, i);
            reverseHMap.put(i, s);

        }
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            char c = s.charAt(0);
            // 숫자라면
            if (Character.isDigit(c)) {
                bw.write(reverseHMap.get(Integer.parseInt(s)));
            } else {
                bw.write(hMap.get(s) + "");
            }
            bw.newLine();

        }
        bw.flush();
    }

}
// https://www.acmicpc.net/problem/1620