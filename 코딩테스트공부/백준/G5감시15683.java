package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5감시15683 {
    static int N, M, min = Integer.MAX_VALUE;
    static int map[][];
    static int dx1[][] = { { -1 }, { 0 }, { 1 }, { 0 } };
    static int dy1[][] = { { 0 }, { 1 }, { 0 }, { -1 } };
    static int dx2[][] = { { -1, 1 }, { 0, 0 } };
    static int dy2[][] = { { 0, 0 }, { 1, -1 } };
    static int dx3[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int dy3[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int dx4[][] = { { -1, 0, 1 }, { 0, 1, 0 }, { 1, 0, -1 }, { 0, -1, 0 } };
    static int dy4[][] = { { 0, 1, 0 }, { 1, 0, -1 }, { 0, -1, 0 }, { -1, 0, 1 } };
    static int dx5[] = { -1, 0, 1, 0 };
    static int dy5[] = { 0, 1, 0, -1 };
    static List<Point> list;

    public static int BlindSpot(int nmap[][]) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (nmap[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    public static void watch(int depth, int nmap[][]) {
        if (depth == list.size()) {
            min = Math.min(min, BlindSpot(nmap));
            return;
        } else {
            int tempmap[][] = new int[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(nmap[i], 0, tempmap[i], 0, tempmap[i].length);
            }
            Point now = list.get(depth);
            if (now.d == 1) {
                for (int i = 0; i < 4; i++) {
                    boolean flag = false;
                    int nx = now.x;
                    int ny = now.y;
                    do {
                        nx += dx1[i][0];
                        ny += dy1[i][0];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            flag = true;
                            continue;
                        }
                        if (nmap[nx][ny] == 6) {
                            flag = true;
                            continue;
                        }
                        if (nmap[nx][ny] == 0) {
                            nmap[nx][ny] = -1;
                        }
                    } while (!flag);

                    watch(depth + 1, nmap);

                    for (int j = 0; j < N; j++) {
                        System.arraycopy(tempmap[j], 0, nmap[j], 0, tempmap[j].length);
                    }
                }
            } else if (now.d == 2) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        boolean flag = false;
                        int nx = now.x;
                        int ny = now.y;
                        do {
                            nx += dx2[i][j];
                            ny += dy2[i][j];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                flag = true;
                                continue;
                            }
                            if (nmap[nx][ny] == 6) {
                                flag = true;
                                continue;
                            }
                            if (nmap[nx][ny] == 0) {
                                nmap[nx][ny] = -1;
                            }
                        } while (!flag);

                    }

                    watch(depth + 1, nmap);

                    for (int k = 0; k < N; k++) {
                        System.arraycopy(tempmap[k], 0, nmap[k], 0, tempmap[k].length);
                    }
                }
            } else if (now.d == 3) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        boolean flag = false;
                        int nx = now.x;
                        int ny = now.y;
                        do {
                            nx += dx3[i][j];
                            ny += dy3[i][j];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                flag = true;
                                continue;
                            }
                            if (nmap[nx][ny] == 6) {
                                flag = true;
                                continue;
                            }
                            if (nmap[nx][ny] == 0) {
                                nmap[nx][ny] = -1;
                            }
                        } while (!flag);

                    }

                    watch(depth + 1, nmap);

                    for (int k = 0; k < N; k++) {
                        System.arraycopy(tempmap[k], 0, nmap[k], 0, tempmap[k].length);
                    }
                }
            } else if (now.d == 4) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        boolean flag = false;
                        int nx = now.x;
                        int ny = now.y;
                        do {
                            nx += dx4[i][j];
                            ny += dy4[i][j];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                flag = true;
                                continue;
                            }
                            if (nmap[nx][ny] == 6) {
                                flag = true;
                                continue;
                            }
                            if (nmap[nx][ny] == 0) {
                                nmap[nx][ny] = -1;
                            }
                        } while (!flag);

                    }

                    watch(depth + 1, nmap);

                    for (int k = 0; k < N; k++) {
                        System.arraycopy(tempmap[k], 0, nmap[k], 0, tempmap[k].length);
                    }
                }
            } else if (now.d == 5) {
                for (int i = 0; i < 4; i++) {
                    boolean flag = false;
                    int nx = now.x;
                    int ny = now.y;
                    do {
                        nx += dx5[i];
                        ny += dy5[i];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            flag = true;
                            continue;
                        }
                        if (nmap[nx][ny] == 6) {
                            flag = true;
                            continue;
                        }
                        if (nmap[nx][ny] == 0) {
                            nmap[nx][ny] = -1;
                        }
                    } while (!flag);
                }
                watch(depth + 1, nmap);

                for (int j = 0; j < N; j++) {
                    System.arraycopy(tempmap[j], 0, nmap[j], 0, tempmap[j].length);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        list = new LinkedList<>();
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(nv[j]);
                if (map[i][j] != 0 && map[i][j] != 6) {
                    list.add(new Point(i, j, map[i][j]));
                }
            }
        }

        watch(0, map);

        System.out.println(min);

    }

    static class Point {
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}

// https://www.acmicpc.net/problem/15683