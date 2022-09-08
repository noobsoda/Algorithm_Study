package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G4야구17281 {
    static final int MAXVALUE = 9;
    static int N, max = 0;
    static int map[][], per[];
    static boolean visited[];

    public static void check() {
        boolean base[];
        int score = 0;
        int k = 0;

        for (int i = 0; i < N; i++) {

            // 이닝마다 루수 초기화
            base = new boolean[3];
            int out = 0;
            while (out < 3) {
                int n = map[i][per[k]];

                switch (n) {
                    // 홈런이면 루수 전부 골인
                    case 4:
                        for (int j = 0; j < 3; j++) {
                            if (base[j]) {
                                score++;
                                base[j] = false;
                            }
                        }
                        score++;
                        break;
                    // 3루타면 루수 전부 골인 타자 3루수
                    case 3:
                        for (int j = 0; j < 3; j++) {
                            if (base[j]) {
                                score++;
                                base[j] = false;
                            }
                        }
                        base[2] = true;
                        break;
                    // 2루타면 2,3루수 전부 골인 1루수->3루수 타자 -> 2루수
                    case 2:
                        for (int j = 1; j < 3; j++) {
                            if (base[j]) {
                                score++;
                                base[j] = false;
                            }
                        }
                        if (base[0]) {
                            base[2] = true;
                            base[0] = false;
                        }
                        base[1] = true;
                        break;
                    // 1루타면 3루수만 골인 투자~2루수까지 업그레이드
                    case 1:
                        if (base[2]) {
                            score++;
                            base[2] = false;
                        }
                        for (int j = 1; j >= 0; j--) {
                            if (base[j]) {
                                base[j + 1] = true;
                                base[j] = false;
                            }
                        }
                        base[0] = true;
                        break;
                    // 0이면 아웃
                    case 0:
                        out++;
                        break;

                }
                k++;

                if (k >= MAXVALUE)
                    k = 0;

                // 아웃3번 이번 아웃

            }
        }
        max = Math.max(score, max);
    }

    public static void permu(int depth) {
        if (depth == MAXVALUE) {
            check();
            return;
        }
        if (depth == 3) {
            permu(depth + 1);
            return;
        }

        for (int i = 1; i < MAXVALUE; i++) {
            if (visited[i])
                continue;
            per[depth] = i;
            visited[i] = true;
            permu(depth + 1);
            visited[i] = false;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][MAXVALUE];
        visited = new boolean[MAXVALUE];
        per = new int[MAXVALUE];
        visited[0] = true;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAXVALUE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permu(0);

        System.out.println(max);

    }
}
// https://www.acmicpc.net/problem/17281