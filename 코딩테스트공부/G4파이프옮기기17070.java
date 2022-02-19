package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G4파이프옮기기17070 {
    static int N, res = 0;;
    static int map[][];

    static int dx[] = {0, 1, 1};
    static int dy[] = {1, 1, 0};

    public static boolean check(int x, int y, int state){
        if(state != 1){
            //nx, ny로 바꿀것
            if(map[x+dx[state]][y+dy[state]] == 1)
                return false;
        }
        else{
            for(int i = 0; i < 3; i++){
                if(map[x+dx[i]][y+dy[i]] == 1)
                    return false;
            }
        }
        

        return true;

    }

    //0 가로, 1 대각선, 2 세로
    public static void dfs(int x, int y, int state){

        if(x == N-1 && y == N-1){
            res++;
            return;      
        }

        if(state == 0){
            for(int i = 0; i < 2; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(!check(x, y, i)) continue;

                dfs(nx, ny, i);
            }

        }  
        else if(state == 1){
            for(int i = 0; i < 3; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(!check(x, y, i)) continue;

                dfs(nx, ny, i);
            }
            
        }      
        else{
            for(int i = 1; i < 3; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(!check(x, y, i)) continue;

                dfs(nx, ny, i);
            }
        }       

        
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                              
            }
        }

        dfs(0, 1, 0);
        System.out.println(res);
    }
}
//https://www.acmicpc.net/problem/17070