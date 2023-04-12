package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G2청소년상어19236 {
    static int map[][];
    static int dmap[][];
    static int max = 0;

    static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int dy[] = { 0, -1, -1, -1, 0, 1, 1, 1 };

    public static void youngshark(int x, int y, int temp[][], int dtemp[][], int state) {

        int nmap[][] = new int[4][4];
        int ndmap[][] = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(temp[i], 0, nmap[i], 0, temp[i].length);
            System.arraycopy(dtemp[i], 0, ndmap[i], 0, dtemp[i].length);
        }

        // 물고기 먹기
        int nd = ndmap[x][y];

        state += nmap[x][y];
        max = Math.max(state, max);
        nmap[x][y] = 0;
        ndmap[x][y] = 0;

        // 물고기 이동
        for (int i = 1; i <= 16; i++) {
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (nmap[j][k] == i) {
                        // 방향 돌리기
                        for (int z = 0; z < 8; z++) {
                            int d = (ndmap[j][k] + z) % 8;
                            int nx = j + dx[d];
                            int ny = k + dy[d];

                            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                                continue;

                            // 상어 자리에는 못 옴
                            if (nx == x && ny == y)
                                continue;

                            // 교체하고 break
                            int n = nmap[j][k];
                            nmap[j][k] = nmap[nx][ny];
                            nmap[nx][ny] = n;

                            ndmap[j][k] = d;

                            int tempd = ndmap[j][k];
                            ndmap[j][k] = ndmap[nx][ny];
                            ndmap[nx][ny] = tempd;

                            flag = true;
                            break;

                            // 이동할수 없는 칸??

                        }
                    }
                    if (flag)
                        break;
                }
                if (flag)
                    break;
            }
        }

        int nx = x;
        int ny = y;
        for (int i = 0; i < 4; i++) {
            nx += dx[nd];
            ny += dy[nd];

            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                continue;
            if (nmap[nx][ny] == 0)
                continue;

            youngshark(nx, ny, nmap, ndmap, state);

        }
        // 선택지 for문 돌려서 멀티버스 샤크

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[4][4];
        dmap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dmap[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }
        youngshark(0, 0, map, dmap, 0);

        System.out.println(max);
    }

}
// https://www.acmicpc.net/problem/19236