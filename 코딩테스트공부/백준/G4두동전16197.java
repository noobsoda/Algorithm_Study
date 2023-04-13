package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4두동전16197 {
    static int N, M, min = Integer.MAX_VALUE;
    static char map[][];
    // 위 오른쪽 아래 왼쪽
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, 1, 0, -1 };

    public static boolean check(char temp[][], char nmap[][], int count) {

        int cnt = 0;
        int placecnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (nmap[i][j] == 'o')
                    cnt++;
                if (nmap[i][j] == 'o' && temp[i][j] == 'o')
                    placecnt++;

            }
        }
        // 동전 2개가 동시에 떨어지거나, 동전 위치가 움직이지 않은 경우
        if (cnt == 0 || placecnt == 2)
            return false;

        // 하나만 떨어진 경우 종료
        if (cnt == 1) {
            min = Math.min(min, count);
        }

        return true;
    }

    public static void twocoin(int depth, int state, char tempmap[][]) {
        if (depth > 10 || depth > min)
            return;
        char nmap[][] = new char[N][M];
        for (int i = 0; i < N; i++)
            System.arraycopy(tempmap[i], 0, nmap[i], 0, tempmap[i].length);

        if (state == 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    if (nmap[i][j] == 'o') {
                        int nx = i + dx[state];
                        int ny = j + dy[state];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            nmap[i][j] = '.';
                            continue;
                        }
                        if (nmap[nx][ny] == '.') {
                            nmap[nx][ny] = 'o';
                            nmap[i][j] = '.';
                        } else if (nmap[nx][ny] == 'o')
                            return;

                    }
                }
            }
        } else if (state == 1) {
            for (int i = M - 1; i >= 0; i--) {
                for (int j = 0; j < N; j++) {

                    if (nmap[j][i] == 'o') {
                        int nx = j + dx[state];
                        int ny = i + dy[state];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            nmap[j][i] = '.';
                            continue;
                        }
                        if (nmap[nx][ny] == '.') {
                            nmap[nx][ny] = 'o';
                            nmap[j][i] = '.';
                        } else if (nmap[nx][ny] == 'o')
                            return;

                    }
                }
            }
        } else if (state == 2) {
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {

                    if (nmap[i][j] == 'o') {
                        int nx = i + dx[state];
                        int ny = j + dy[state];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            nmap[i][j] = '.';
                            continue;
                        }
                        if (nmap[nx][ny] == '.') {
                            nmap[nx][ny] = 'o';
                            nmap[i][j] = '.';
                        } else if (nmap[nx][ny] == 'o')
                            return;

                    }
                }
            }
        } else if (state == 3) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {

                    if (nmap[j][i] == 'o') {
                        int nx = j + dx[state];
                        int ny = i + dy[state];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            nmap[j][i] = '.';
                            continue;
                        }
                        if (nmap[nx][ny] == '.') {
                            nmap[nx][ny] = 'o';
                            nmap[j][i] = '.';
                        } else if (nmap[nx][ny] == 'o')
                            return;

                    }
                }
            }
        }
        if (state != -1) {
            if (!check(tempmap, nmap, depth))
                return;
        }

        for (int i = 0; i < 4; i++) {
            twocoin(depth + 1, i, nmap);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String nv = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = nv.charAt(j);
            }
        }

        twocoin(0, -1, map);
        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);

    }
}
// https://www.acmicpc.net/problem/16197