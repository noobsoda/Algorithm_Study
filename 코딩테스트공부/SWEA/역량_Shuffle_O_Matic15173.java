package 코딩테스트공부.SWEA;

import java.io.*;
import java.util.*;

public class 역량_Shuffle_O_Matic15173 {
    static int N, min;
    static int map[];
    static Integer ascendingmap[];
    static Integer reversemap[];

    public static boolean ArrEquals(int tempmap[]) {
        boolean flag1 = true;
        boolean flag2 = true;
        for (int i = 0; i < N; i++) {
            if (tempmap[i] != ascendingmap[i])
                flag1 = false;
            if (tempmap[i] != reversemap[i])
                flag2 = false;
        }

        if (flag1 || flag2)
            return true;

        return false;

    }

    public static boolean shuffle(int x, int tempmap[]) {

        int deck1[] = new int[N / 2];
        int deck2[] = new int[N / 2];
        for (int i = 0; i < N; i++) {
            if (i < N / 2)
                deck1[i] = tempmap[i];
            else
                deck2[i - N / 2] = tempmap[i];

        }

        if (x < N / 2) {
            int cnt = 0;
            for (int i = 0; i < N / 2 - x; i++) {
                tempmap[i] = deck1[i];
                cnt++;
            }
            for (int i = 0; i < x; i++) {
                tempmap[cnt++] = deck2[i];
                tempmap[cnt++] = deck1[N / 2 - x + i];
            }
            // 중간 스왑코드만

            for (int i = N / 2 + x; i < N; i++) {
                tempmap[i] = deck2[i - N / 2];
            }

        } else {
            x = N - x - 1;
            int cnt = 0;
            for (int i = 0; i < N / 2 - x; i++) {
                tempmap[i] = deck2[i];
                cnt++;
            }

            for (int i = 0; i < x; i++) {
                tempmap[cnt++] = deck1[i];
                tempmap[cnt++] = deck2[N / 2 - x + i];
            }

            for (int i = N / 2 + x; i < N; i++) {
                tempmap[i] = deck1[i - N / 2];
            }

        }

        // 정렬 됬나 확인
        if (ArrEquals(tempmap))
            return true;

        return false;

    }

    public static void permu(int depth, int tempmap[]) {

        if (depth >= min) {
            return;
        }
        // 5 넘어가면 안 됨
        if (depth == 6) {
            return;
        }

        for (int i = 1; i < N; i++) {
            int tempmap2[] = new int[N];
            System.arraycopy(tempmap, 0, tempmap2, 0, N);

            if (shuffle(i, tempmap2)) {
                min = depth;
                return;
            }
            permu(depth + 1, tempmap2);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            map = new int[N];
            ascendingmap = new Integer[N];
            reversemap = new Integer[N];

            for (int i = 0; i < N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
                ascendingmap[i] = map[i];
                reversemap[i] = map[i];
            }
            Arrays.sort(ascendingmap);
            Arrays.sort(reversemap, Comparator.reverseOrder());

            if (ArrEquals(map))
                min = 0;
            else
                permu(1, map);

            if (min == Integer.MAX_VALUE)
                min = -1;

            System.out.println("#" + test_case + " " + min);

        }

    }
}
// 5
// 4
// 1 2 3 4
// 4
// 4 2 3 1
// 6
// 6 5 4 2 3 1
// 8
// 6 1 4 7 2 5 8 3
// 12
// 2 7 4 1 3 5 8 10 12 9 6 11