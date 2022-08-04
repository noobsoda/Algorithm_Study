package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S3구간합구하기4_11659 {
    static int N, M;
    static int sum;
    static int map[];
    static int map2[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            map[i+1] = Integer.parseInt(st.nextToken());
        }
            for(int i = 0; i < N; i++){
            map[i+1] += map[i];
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            System.out.println(map[end] - map[start-1]);
        }
       



    }
}
