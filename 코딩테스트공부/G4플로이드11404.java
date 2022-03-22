package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G4플로이드11404 {
    static int N, M;
    static int dist[][];
    static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];

        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= N; j++){
                dist[i][j] = INF;
                if(i == j)  dist[i][j] = 0;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(dist[a][b] < w)  continue;
            dist[a][b] = w;
        }        

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(dist[i][j] == INF)
                    dist[i][j] = 0;
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

    }
    
    
}
//https://www.acmicpc.net/problem/11404