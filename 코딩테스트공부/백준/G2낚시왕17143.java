package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G2낚시왕17143 {
    static int R, C, M;
    static int map[][];
    static List<Shark> list;
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, 1, -1 };
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new LinkedList<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            list.add(new Shark(r - 1, c - 1, s, d - 1, z));
            map[r - 1][c - 1] = z;
        }
        Collections.sort(list);

        // 낚시왕 이동 i는 낚시왕
        for (int i = 0; i < C; i++) {

            // 가까운 상어 get
            for (int j = 0; j < R; j++) {
                if (map[j][i] != 0) {
                    res += map[j][i];
                    map[j][i] = 0;
                    break;
                }
            }
            for (Iterator<Shark> it = list.iterator(); it.hasNext();) {
                Shark shark = it.next();
                if (map[shark.r][shark.c] == 0)
                    it.remove();
            }
            for (int j = 0; j < R; j++) {
                Arrays.fill(map[j], 0);
            }

            // 상어 move
            for (Iterator<Shark> it = list.iterator(); it.hasNext();) {
                Shark shark = it.next();

                // 샤크 무브
                int nows = 0;
                if (shark.d == 0 || shark.d == 1)
                    nows = dx[shark.d] * shark.s % ((R - 1) * 2);

                else
                    nows = dy[shark.d] * shark.s % ((C - 1) * 2);

                nows = Math.abs(nows);

                for (int j = 0; j < nows; j++) {
                    if (shark.d == 0 || shark.d == 1) {
                        if (shark.r + dx[shark.d] < 0 || shark.r + dx[shark.d] >= R)
                            shark.d = (shark.d + 1) % 2;

                        shark.r += dx[shark.d];

                    } else {
                        if (shark.c + dy[shark.d] < 0 || shark.c + dy[shark.d] >= C)
                            if (++shark.d % 4 == 0)
                                shark.d = 2;

                        shark.c += dy[shark.d];
                    }
                }

                // 같은 공간에 상어 있으면 잡아먹기
                if (map[shark.r][shark.c] > shark.z) {
                    it.remove();
                } else {
                    map[shark.r][shark.c] = shark.z;
                }

            }

        }
        System.out.println(res);

    }

    static class Shark implements Comparable<Shark> {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public int compareTo(Shark o) {
            return o.z - z;
        }
    }
}
// https://www.acmicpc.net/problem/17143