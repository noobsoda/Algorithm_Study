package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S4요세푸스문제1158 {
    static int N, K;
    static boolean visited[];
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        bw.write("<");
        int cnt = 1;
        while (!q.isEmpty()) {
            int n = q.poll();
            if (cnt == K) {
                cnt = 0;
                if (q.isEmpty())
                    bw.write(n + "");
                else
                    bw.write(n + ", ");
            } else {
                q.add(n);
            }

            cnt++;
        }

        bw.write(">");

        bw.flush();
    }
}
