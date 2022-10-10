package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S1맥주마시면서걸아가기9205 {
    static int N;
    static List<Node> list;
    static boolean dist[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            dist = new boolean[N + 2][N + 2];

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Node(x, y));

            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    Node nowi = list.get(i);
                    Node nowj = list.get(j);

                    if (Math.abs(nowi.x - nowj.x) + Math.abs(nowi.y - nowj.y) <= 1000) {
                        dist[i][j] = true;
                    }
                }
            }

            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        if (dist[i][k] && dist[k][j]) {
                            dist[i][j] = true;
                        }
                    }
                }

            }
            bw.write((dist[0][N + 2 - 1]) ? "happy\n" : "sad\n");

        }
        bw.flush();

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
