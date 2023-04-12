package 코딩테스트공부.백준;

import java.io.*;

public class G2주사위윷놀이17825 {
    static int dice[], order[], max = 0, ndice[];
    static int A1[] = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 };
    static int B1[] = { 10, 13, 16, 19, 25, 30, 35, 40 };
    static int B2[] = { 20, 22, 24, 25, 30, 35, 40 };
    static int B3[] = { 30, 28, 27, 26, 25, 30, 35, 40 };

    public static int yutplay() {

        int score = 0;
        ndice = new int[5];
        boolean flagA[] = new boolean[5];
        boolean flagB[] = new boolean[5];
        boolean flagC[] = new boolean[5];
        boolean flag25[] = new boolean[5];
        boolean flag30[] = new boolean[5];
        boolean flag35[] = new boolean[5];
        boolean flag40[] = new boolean[5];

        for (int i = 0; i < 10; i++) {

            ndice[order[i]] += dice[i];

            // 다른 루트에 진입하면 다신 못 돌아옴
            if (ndice[order[i]] == 5 && !flagA[order[i]] && !flagB[order[i]] && !flagC[order[i]]) {
                flagA[order[i]] = true;
                ndice[order[i]] = 0;

            } else if (ndice[order[i]] == 10 && !flagA[order[i]] && !flagB[order[i]] && !flagC[order[i]]) {
                flagB[order[i]] = true;
                ndice[order[i]] = 0;
            } else if (ndice[order[i]] == 15 && !flagA[order[i]] && !flagB[order[i]] && !flagC[order[i]]) {
                flagC[order[i]] = true;
                ndice[order[i]] = 0;
            }

            // 25 30 35 40에서 만났을 때 경우의 수 추가해주기
            for (int j = 1; j <= 4; j++) {
                // 자기자신은 제외
                if (order[i] == j)
                    continue;

                // 다른 주사위들과 겹쳐 있을 때
                if (ndice[j] == ndice[order[i]]) {
                    // A루트일때 20을 넘어갔으면 도착지점 중복 가능
                    if (!flagA[order[i]] && !flagB[order[i]] && !flagC[order[i]] &&
                            !flagA[j] && !flagB[j] && !flagC[j] && ndice[j] <= 20 && ndice[order[i]] <= 20)
                        return 0;

                    // B1루트
                    if (flagA[order[i]] && flagA[j] && ndice[j] <= 7 && ndice[order[i]] <= 7)
                        return 0;

                    // B2루트
                    if (flagB[order[i]] && flagB[j] && ndice[j] <= 6 && ndice[order[i]] <= 7)
                        return 0;

                    // B3루트
                    if (flagC[order[i]] && flagC[j] && ndice[j] <= 7 && ndice[order[i]] <= 7)
                        return 0;

                }
            }

            // 파란색 화살표를 탄다면

            // B1루트
            if (flagA[order[i]]) {
                if (ndice[order[i]] > 4)
                    flag25[order[i]] = false;
                if (ndice[order[i]] > 5)
                    flag30[order[i]] = false;
                if (ndice[order[i]] > 6)
                    flag35[order[i]] = false;
                if (ndice[order[i]] > 7) {
                    flag40[order[i]] = false;
                    continue;
                }
                score += B1[ndice[order[i]]];
                if (ndice[order[i]] == 4)
                    flag25[order[i]] = true;
                if (ndice[order[i]] == 5)
                    flag30[order[i]] = true;
                if (ndice[order[i]] == 6)
                    flag35[order[i]] = true;
                if (ndice[order[i]] == 7)
                    flag40[order[i]] = true;
            }
            // B2루트
            else if (flagB[order[i]]) {
                if (ndice[order[i]] > 3)
                    flag25[order[i]] = false;
                if (ndice[order[i]] > 4)
                    flag30[order[i]] = false;
                if (ndice[order[i]] > 5)
                    flag35[order[i]] = false;
                if (ndice[order[i]] > 6) {
                    flag40[order[i]] = false;
                    continue;
                }
                score += B2[ndice[order[i]]];
                if (ndice[order[i]] == 3)
                    flag25[order[i]] = true;
                if (ndice[order[i]] == 4)
                    flag30[order[i]] = true;
                if (ndice[order[i]] == 5)
                    flag35[order[i]] = true;
                if (ndice[order[i]] == 6)
                    flag40[order[i]] = true;
            }
            // B3루트
            else if (flagC[order[i]]) {
                if (ndice[order[i]] > 4)
                    flag25[order[i]] = false;
                if (ndice[order[i]] > 5)
                    flag30[order[i]] = false;
                if (ndice[order[i]] > 6)
                    flag35[order[i]] = false;
                if (ndice[order[i]] > 7) {
                    flag40[order[i]] = false;
                    continue;
                }
                score += B3[ndice[order[i]]];
                if (ndice[order[i]] == 4)
                    flag25[order[i]] = true;
                if (ndice[order[i]] == 5)
                    flag30[order[i]] = true;
                if (ndice[order[i]] == 6)
                    flag35[order[i]] = true;
                if (ndice[order[i]] == 7)
                    flag40[order[i]] = true;
            }
            // A1루트
            else {
                if (ndice[order[i]] > 20) {
                    flag40[order[i]] = false;
                    continue;
                }
                score += A1[ndice[order[i]]];
                if (ndice[order[i]] == 20)
                    flag40[order[i]] = true;
            }

            for (int j = 1; j <= 4; j++) {
                if (order[i] == j)
                    continue;
                if (flag25[order[i]] && flag25[j])
                    return 0;
                if (flag30[order[i]] && flag30[j])
                    return 0;
                if (flag35[order[i]] && flag35[j])
                    return 0;
                if (flag40[order[i]] && flag40[j])
                    return 0;
            }

        }

        return score;
    }

    // 백트래킹 안씀 모든 경우의 수 찾기
    public static void combination(int depth) {
        if (depth == 10) {
            // max = Math.max(max, yutplay());
            int num = yutplay();
            if (max < num) {
                max = num;
            }
        } else {
            for (int i = 1; i <= 4; i++) {
                order[depth] = i;
                combination(depth + 1);

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        dice = new int[10];
        order = new int[10];
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(nv[i]);
        }

        combination(0);

        System.out.println(max);
    }
}

// https://www.acmicpc.net/problem/17825