package 코딩테스트공부.코드트리;

import java.io.*;
import java.util.*;

public class G1토끼와경주 {
    static int Q;
    static Rabbit[] rabbits;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            switch (q) {
                case 100:
                    int n = Integer.parseInt(st.nextToken());
                    int m = Integer.parseInt(st.nextToken());
                    int p = Integer.parseInt(st.nextToken());
                    rabbits = new Rabbit[p];

                    for (int j = 0; j < p; j++) {
                        int pid = Integer.parseInt(st.nextToken());
                        int dist = Integer.parseInt(st.nextToken());
                        rabbits[i] = new Rabbit(pid, dist);
                    }
                    init(n, m, p, rabbits);
                    break;
                case 200:
                    int k = Integer.parseInt(st.nextToken());
                    int s = Integer.parseInt(st.nextToken());
                    proceedRace(k, s);
                    break;
                case 300:
                    int pid = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    updateDistance(pid, l);
                    break;
                case 400:
                    int res = selectBestRabbit();
                    System.out.println(res);
                    break;
            }

        }
    }

    private static void init(int n, int m, int p, Rabbit[] rabbits) {
    }

    private static void proceedRace(int k, int s) {
    }

    private static void updateDistance(int pid, int l) {
    }

    private static int selectBestRabbit() {
        return 0;
    }

    static class Rabbit {
        int pid;
        int dist;
        int count;
        int score;

        public Rabbit(int pid, int dist) {
            this.pid = pid;
            this.dist = dist;
        }
    }

}
