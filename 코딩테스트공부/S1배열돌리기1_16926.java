package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S1배열돌리기1_16926 {
    static int N, M, R;
    static int map[][];

    public static void rotate(int depth){
        int temp = map[depth][depth];
        for(int i = depth+1; i < M-depth; i++){
            map[depth][i-1] = map[depth][i];
        }
        for(int i = depth+1; i < N-depth; i++){
            map[i-1][M-1-depth] = map[i][M-1-depth];
        }
        for(int i = depth+1; i < M-depth; i++){
            map[N-1-depth][M-1-i+1] = map[N-1-depth][M-1-i];
        }
        for(int i = depth+1; i < N-depth; i++){
            map[N-1-i+1][depth] = map[N-1-i][depth];
        }
        map[depth+1][depth] = temp;
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Math.min(N, M);
        for(int k = 0; k < R; k++)
            for(int i = 0; i < min/2; i++)
                rotate(i);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                bw.write(map[i][j] + " ");                        
            }
            bw.write("\n");
        }
        bw.flush();

    }
   
}
