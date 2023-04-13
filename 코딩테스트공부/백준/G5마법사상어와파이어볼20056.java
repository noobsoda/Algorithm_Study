package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5마법사상어와파이어볼20056 {
    static int N, M, K;
    static int map[][];
    static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static List<Node> list;
    static Node node;

    public static void firedivision(int r, int c) {
        int m = 0, s = 0, d = 0, count = 0;
        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            node = it.next();
            if (node.r != r || node.c != c)
                continue;
            m += node.m;
            s += node.s;
            d += node.d % 2;
            count++;

            it.remove();
        }
        m /= 5;
        s /= count;
        if (m > 0) {
            if (d == 0 || d == count) {
                for (int i = 0; i < 4; i++) {
                    list.add(new Node(r, c, m, s, i * 2));
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    list.add(new Node(r, c, m, s, i * 2 + 1));
                }

            }
        }

    }

    public static void fireball() {
        int result = 0;
        for (int i = 0; i < K; i++) {
            for (Iterator<Node> it = list.iterator(); it.hasNext();) {
                node = it.next();
                node.r += dr[node.d] * node.s;
                node.c += dc[node.d] * node.s;

                while (node.r < 1)
                    node.r += N;
                while (node.r > N)
                    node.r -= N;
                while (node.c < 1)
                    node.c += N;
                while (node.c > N)
                    node.c -= N;

                map[node.r][node.c]++;
            }
            // 파이어볼 체크
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (map[j][k] >= 2) {
                        firedivision(j, k);
                    }
                }
            }
            for (int a[] : map) {
                Arrays.fill(a, 0);
            }
        }

        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            node = it.next();
            result += node.m;
        }
        System.out.println(result);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        K = Integer.parseInt(nv[2]);

        list = new LinkedList<>();
        map = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            nv = br.readLine().split(" ");
            int r = Integer.parseInt(nv[0]);
            int c = Integer.parseInt(nv[1]);
            int m = Integer.parseInt(nv[2]);
            int s = Integer.parseInt(nv[3]);
            int d = Integer.parseInt(nv[4]);

            list.add(new Node(r, c, m, s, d));
        }

        fireball();

    }

    static class Node {
        int r, c, m, s, d;

        public Node(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
// https://www.acmicpc.net/problem/20056