package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S1점모으기7571 {
    static int N, M;
    static int mapX[], mapY[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapX = new int[M];
        mapY = new int[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            mapX[i] = x;
            mapY[i] = y;
        }

        Arrays.sort(mapX);
        Arrays.sort(mapY);

        int midX = mapX[M / 2];
        int midY = mapY[M / 2];

        int res = 0;
        for (int i = 0; i < M; i++) {

            res += (Math.abs(mapX[i] - midX) + Math.abs(mapY[i] - midY));
        }

        System.out.println(res);

    }
}
