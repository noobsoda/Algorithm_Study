package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G3저울2437 {
    static int N;
    static int map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(map);

        // 누적합
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + 1 < map[i]) {
                break;
            }
            sum += map[i];

        }
        System.out.println(sum + 1);

    }
}
// https://www.acmicpc.net/problem/2437