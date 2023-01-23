package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class C미팅 {
    static int N, M, C;
    static int W[][];
    static int [] A, B;
    static int res;

    public static void dfs(int depth, int start, int max, int small[], int big[]){
        if(depth == small.length){
            return;
        }
        for(int i = start; i < big.length; i++){

            res = Math.max(res, max + W[small[depth]][big[i]]);

            dfs(depth+1, i+1, max + W[small[depth]][big[i]], small, big);
            
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        W = new int[C][C];
        A = new int[N];
        B = new int[M];

        for(int i = 0; i < C; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken())-1;
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){            
            B[i] = Integer.parseInt(st.nextToken())-1;
        }

        if(A.length < B.length){
            dfs(0, 0, 0, A, B);
        }else{
            dfs(0, 0, 0, B, A);
        }

        System.out.println(res);


    }
}
