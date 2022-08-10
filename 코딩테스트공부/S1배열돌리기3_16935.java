package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S1배열돌리기3_16935 {
    static int N, M, R;
    static int map[][];

    public static void rotate(int cnt){
        int tempmap[][] = new int[N][M];
        for(int i = 0; i < N; i++)
        System.arraycopy(map[i], 0, tempmap[i], 0, tempmap[i].length);
        if(cnt == 1){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    map[N-1-i][j] = tempmap[i][j];
                }
            }
        }
        else if(cnt == 2){
            for(int i = 0; i < M; i++){
                for(int j = 0; j < N; j++){
                    map[j][M-1-i] = tempmap[j][i];
                }
            }
        }
        else if(cnt == 3){
            tempmap = new int[M][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    tempmap[j][N-1-i] = map[i][j];
                }
            } 
            map = new int[M][N];
            for(int i = 0; i < M; i++){
                System.arraycopy(tempmap[i], 0, map[i], 0, tempmap[i].length);
            }
            int temp = N;
            N = M;
            M = temp;
        }
        else if(cnt == 4){
            tempmap = new int[M][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    tempmap[M-1-j][i] = map[i][j];
                }
            } 
            map = new int[M][N];
            for(int i = 0; i < M; i++){
                System.arraycopy(tempmap[i], 0, map[i], 0, tempmap[i].length);
            }
            int temp = N;
            N = M;
            M = temp;
        }
        else if(cnt == 5){
            for(int i = 0; i < N/2; i++){
                for(int j = M/2; j < M; j++){
                    map[i+N/2][j] = map[i][j];
                }
            }   
            for(int i = 0; i < N/2; i++){
                for(int j = 0; j < M/2; j++){
                    map[i][j+M/2] = map[i][j];
                }
            } 
            for(int i = N/2; i < N; i++){
                for(int j = 0; j < M/2; j++){
                    map[i-N/2][j] = map[i][j];
                }
            }
            for(int i = N/2; i < N; i++){
                for(int j = M/2; j < M; j++){
                    map[i][j-M/2] = tempmap[i][j];
                }
            }         
        }
        else{
            for(int i = 0; i < N/2; i++){
                for(int j = M/2; j < M; j++){
                    map[i][j-M/2] = map[i][j];
                }
            }   
            for(int i = N/2; i < N; i++){
                for(int j = M/2; j < M; j++){
                    map[i-N/2][j] = map[i][j];
                }
            }
            for(int i = N/2; i < N; i++){
                for(int j = 0; j < M/2; j++){
                    map[i][j+M/2] = map[i][j];
                }
            }
            for(int i = 0; i < N/2; i++){
                for(int j = 0; j < M/2; j++){
                    map[i+N/2][j] = tempmap[i][j];
                }
            } 
                     
        }
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

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < R; i++){
            int n = Integer.parseInt(st.nextToken());
            rotate(n);
        }


        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                bw.write(map[i][j] + " ");                        
            }
            bw.write("\n");
        }
        bw.flush();

    }
   
}
