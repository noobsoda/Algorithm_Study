package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G2모노미노도미노2_20061 {
    static int N, score = 0;
    static int map[][];
    static int bluemap[][];
    static int redmap[][];
    static Queue<Node> q;

    public static void Print() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Arrays.toString(bluemap[i]));
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(redmap[i]));
        }
        System.out.println();
    }

    public static void push() {
        // 탐색하고 땡기기
        // 최대 2번 가능함
        int bluemax = 0;
        int redmax = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (bluemap[j][i] == 1) {
                    bluemax++;
                    break;
                }
            }

            for (int j = 0; j < 4; j++) {
                if (redmap[i][j] == 1) {
                    redmax++;
                    break;
                }

            }
        }

        for (int n = 0; n < 2; n++) {
            boolean blueflag = false;
            boolean redflag = false;
            // 블루 최대횟수 만큼 땡기기
            if (bluemax > 0) {
                blueflag = true;
                bluemax--;
            }
            // 레드 최대횟수 만큼 땡기기
            if (redmax > 0) {
                redflag = true;
                redmax--;
            }
            // 0부터 4까지 고려해서 땡기기
            for (int i = 4; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {

                    if (blueflag) {
                        bluemap[j][i + 1] = bluemap[j][i];
                        bluemap[j][i] = 0;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    if (redflag) {
                        redmap[i + 1][j] = redmap[i][j];
                        redmap[i][j] = 0;
                    }
                }

            }
        }

    }

    public static void expolre() {
        // 점수 획득
        // 탐색하고 제거
        // 최대 2번 가능함
        for (int n = 0; n < 2; n++) {
            boolean blueflag = false;
            boolean redflag = false;
            for (int i = 5; i >= 0; i--) {
                int bluesum = 0;
                int redsum = 0;
                for (int j = 0; j < 4; j++) {
                    if (!blueflag && bluemap[j][i] == 1) {
                        bluesum++;
                    }
                    if (blueflag) {
                        bluemap[j][i + 1] = bluemap[j][i];
                        bluemap[j][i] = 0;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    if (!redflag && redmap[i][j] == 1) {
                        redsum++;
                    }
                    if (redflag) {
                        redmap[i + 1][j] = redmap[i][j];
                        redmap[i][j] = 0;
                    }
                }

                // 4개 다 찼으면 flag ON
                if (bluesum == 4) {
                    blueflag = true;
                    score++;
                    bluesum = 0;

                }
                if (redsum == 4) {
                    redflag = true;
                    score++;
                    redsum = 0;
                }
            }
        }

    }

    public static void monominopush(int x, int y, int t) {
        boolean blue = false;
        boolean red = false;

        if (t == 1) {
            for (int i = 1; i < 5; i++) {

                // 앞에 블럭이 있으면 블럭 설치
                if ((bluemap[x][i + 1] == 1) && !blue) {
                    bluemap[x][i] = 1;
                    blue = true;
                }
                // 앞에 블럭이 없고 벽에 도달했으면 블럭 설치
                if (i == 4 && !blue)
                    bluemap[x][i + 1] = 1;

                if ((redmap[i + 1][y] == 1) && !red) {
                    redmap[i][y] = 1;
                    red = true;
                }

                if (i == 4 && !red)
                    redmap[i + 1][y] = 1;
            }

        } else if (t == 2) {
            for (int i = 1; i < 5; i++) {

                if ((bluemap[x][i + 1] == 1) && !blue) {
                    bluemap[x][i] = 1;
                    bluemap[x][i - 1] = 1;
                    blue = true;
                }
                if (i == 4 && !blue) {
                    bluemap[x][i + 1] = 1;
                    bluemap[x][i] = 1;
                }

                if ((redmap[i + 1][y] == 1 || redmap[i + 1][y + 1] == 1) && !red) {
                    redmap[i][y] = 1;
                    redmap[i][y + 1] = 1;
                    red = true;
                }
                if (i == 4 && !red) {
                    redmap[i + 1][y] = 1;
                    redmap[i + 1][y + 1] = 1;
                }
            }
        } else {
            for (int i = 1; i < 5; i++) {

                if ((bluemap[x][i + 1] == 1 || bluemap[x + 1][i + 1] == 1) && !blue) {
                    bluemap[x][i] = 1;
                    bluemap[x + 1][i] = 1;
                    blue = true;
                }

                if (i == 4 && !blue) {
                    bluemap[x][i + 1] = 1;
                    bluemap[x + 1][i + 1] = 1;
                }

                if (redmap[i + 1][y] == 1 && !red) {
                    redmap[i][y] = 1;
                    redmap[i - 1][y] = 1;
                    red = true;
                }
                if (i == 4 && !red) {
                    redmap[i + 1][y] = 1;
                    redmap[i][y] = 1;
                }
            }
        }
    }

    public static void monominodomino() {
        while (!q.isEmpty()) {
            Node now = q.poll();
            // T만큼 보내기
            monominopush(now.x, now.y, now.t);

            // 점수획득 탐색 및 제거
            expolre();

            // 연한 타일에 있으면 끝 부분제거하고 땡기기
            push();

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[4][4];
        bluemap = new int[4][6];
        redmap = new int[6][4];

        for (int i = 0; i < 6; i++) {
            if (i < 4) {
                Arrays.fill(map[i], 0);
                Arrays.fill(bluemap[i], 0);
            }
            Arrays.fill(redmap[i], 0);
        }
        q = new LinkedList<>();
        String nv[];

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            int t = Integer.parseInt(nv[0]);
            int x = Integer.parseInt(nv[1]);
            int y = Integer.parseInt(nv[2]);

            q.add(new Node(x, y, t));
        }

        monominodomino();

        // 타일 횟수
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (bluemap[j][i] == 1)
                    cnt++;
                if (redmap[i][j] == 1)
                    cnt++;

            }
        }
        System.out.println(score);
        System.out.println(cnt);
    }

    static class Node {
        int x, y, t;

        public Node(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}

// https://www.acmicpc.net/problem/20061