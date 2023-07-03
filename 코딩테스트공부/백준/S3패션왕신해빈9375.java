package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3패션왕신해빈9375 {
    static int T, N;
    static Map<String, Integer> clothMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            int res = 1;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            clothMap = new HashMap<>();

            for (int j = 0; j < N; j++) {
                String cloth[] = br.readLine().split(" ");
                if (clothMap.containsKey(cloth[1])) {
                    clothMap.put(cloth[1], clothMap.get(cloth[1]) + 1);
                } else {
                    clothMap.put(cloth[1], 2);
                }

            }
            for (String type : clothMap.keySet()) {
                res *= clothMap.get(type);
            }
            bw.write(res - 1 + "\n");

        }
        bw.flush();
    }
}
// https://www.acmicpc.net/problem/9375