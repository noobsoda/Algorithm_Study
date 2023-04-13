package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3경사로14890 {
    static int N, L;
    static int map[][];
    static int res = 0;

    public static void runway(int i, int j) {
        for (i = 0; i < N; i++) {
            boolean visited[] = new boolean[N];

            boolean flag = true;
            for (j = 1; j < N; j++) {
                // 높이가 같으면 다음 칸
                if (map[i][j] == map[i][j - 1])
                    continue;

                // 다음 칸 높이가 2 이상이면 X
                int diff = Math.abs(map[i][j] - map[i][j - 1]);
                if (diff > 1) {
                    flag = false;
                    break;
                }

                // 누가 더 낮느냐를 판단해야 함
                // 가는 길이 높으면 이전 L만큼
                // 가는 길이 낮으면 이후 L만큼
                // L제한거리
                if (map[i][j] > map[i][j - 1]) {
                    for (int k = 0; k < L; k++) {

                        if (j - 1 - k < 0) {
                            flag = false;
                            break;
                        }

                        if (map[i][j - 1] != map[i][j - 1 - k] || visited[j - 1 - k]) {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    for (int k = 0; k < L; k++) {
                        if (j + k >= N) {
                            flag = false;
                            break;
                        }
                        if (map[i][j] != map[i][j + k]) {
                            flag = false;
                            break;
                        }
                    }
                    // 끝부분만 경사로 표시
                    if (flag)
                        visited[j + L - 1] = true;

                }

            }
            if (flag)
                res++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        // 가로
        for (int i = 0; i < N; i++) {
            boolean visited[] = new boolean[N];

            boolean flag = true;
            for (int j = 1; j < N; j++) {
                // 높이가 같으면 다음 칸
                if (map[i][j] == map[i][j - 1])
                    continue;

                // 다음 칸 높이가 2 이상이면 X
                int diff = Math.abs(map[i][j] - map[i][j - 1]);
                if (diff > 1) {
                    flag = false;
                    break;
                }

                // 누가 더 낮느냐를 판단해야 함
                // 가는 길이 높으면 이전 L만큼
                // 가는 길이 낮으면 이후 L만큼
                // L제한거리
                if (map[i][j] > map[i][j - 1]) {
                    for (int k = 0; k < L; k++) {

                        if (j - 1 - k < 0) {
                            flag = false;
                            break;
                        }

                        if (map[i][j - 1] != map[i][j - 1 - k] || visited[j - 1 - k]) {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    for (int k = 0; k < L; k++) {
                        if (j + k >= N) {
                            flag = false;
                            break;
                        }
                        if (map[i][j] != map[i][j + k]) {
                            flag = false;
                            break;
                        }
                    }
                    // 끝부분만 경사로 표시
                    if (flag)
                        visited[j + L - 1] = true;

                }

            }
            if (flag)
                res++;
        }

        for (int i = 0; i < N; i++) {
            boolean visited[] = new boolean[N];
            boolean flag = true;
            for (int j = 1; j < N; j++) {
                // 높이가 같으면 다음 칸
                if (map[j][i] == map[j - 1][i])
                    continue;

                // 다음 칸 높이가 2 이상이면 X
                int diff = Math.abs(map[j][i] - map[j - 1][i]);
                if (diff > 1) {
                    flag = false;
                    break;
                }

                // 누가 더 낮느냐를 판단해야 함
                // 가는 길이 높으면 이전 L만큼
                // 가는 길이 낮으면 이후 L만큼
                // L제한거리
                if (map[j][i] > map[j - 1][i]) {
                    for (int k = 0; k < L; k++) {
                        if (j - 1 - k < 0) {
                            flag = false;
                            break;
                        }
                        if (map[j - 1][i] != map[j - 1 - k][i] || visited[j - 1 - k]) {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    for (int k = 0; k < L; k++) {
                        if (j + k >= N) {
                            flag = false;
                            break;
                        }
                        if (map[j][i] != map[j + k][i]) {
                            flag = false;
                            break;
                        }
                    }
                    // 끝부분만 경사로 표시
                    if (flag)
                        visited[j + L - 1] = true;
                }

            }
            if (flag)
                res++;

        }
        System.out.println(res);

    }
}
// https://www.acmicpc.net/problem/14890
// 160ms