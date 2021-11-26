import java.io.*;
import java.util.*;

public class G4주사위굴리기14499 {

    static int map[][];
    static int N, M, sX, sY, K;
    static Queue<Integer> q;
    static int dx[] = {0, 0, 0, -1, 1};
    static int dy[] = {0, 1, -1, 0, 0};
    static int U = 0, D = 0, F = 0, B = 0, L = 0, R = 0;
    
    public static void dice(int direction){
        
        if(direction == 1){
            //L -> U -> R -> D
            int temp = L;
            L = D;
            D = R;
            R = U;
            U = temp;                       
        }
        else if(direction == 2){
            int temp = D;
            //D -> R -> U -> L
            D = L;
            L = U;
            U = R;
            R = temp;
        }
        else if(direction == 3){
            //B -> U -> F -> D
            int temp = B;
            B = U;
            U = F;
            F = D;
            D = temp; 
        }
        else if(direction == 4){
            //D -> F -> U -> B
            int temp = D;
            D = F;
            F = U;
            U = B;
            B = temp;
        }
        
    }
    
    public static void dicegame(int x, int y){
        
        while(!q.isEmpty()){
            int d = q.poll();
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;
            
            x = nx;
            y = ny;

            dice(d);
            if(map[nx][ny] == 0){
                map[nx][ny] = D;
            }
            else{
                D = map[nx][ny];
                map[nx][ny] = 0;
            }
            System.out.println(U);
            
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        sX = Integer.parseInt(nv[2]);
        sY = Integer.parseInt(nv[3]);
        K = Integer.parseInt(nv[4]);
        q = new LinkedList<>();        
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
        nv = br.readLine().split(" ");
        for(int i = 0; i < K; i++){            
            q.add(Integer.parseInt(nv[i]));
        }
        dicegame(sX, sY);

    }
    
}

//https://www.acmicpc.net/problem/14499