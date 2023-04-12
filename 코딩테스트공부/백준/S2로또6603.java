package 코딩테스트공부.백준;

import java.io.*;

public class S2로또6603 {
    static int K;
    static int S[];
    static boolean visited[];

    public static void Lotto(int start, int depth) {
        if (depth == 6) {
            for (int i = 0; i < K; i++) {
                if (visited[i])
                    System.out.print(S[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < K; i++) {
            visited[i] = true;
            Lotto(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[];

        do {
            nv = br.readLine().split(" ");
            K = Integer.parseInt(nv[0]);
            if (K == 0)
                System.exit(0);

            S = new int[K];
            visited = new boolean[K];
            for (int i = 0; i < K; i++) {
                S[i] = Integer.parseInt(nv[i + 1]);
            }
            Lotto(0, 0);
            System.out.println();
        } while (K != 0);

    }
}
