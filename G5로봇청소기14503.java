import java.io.*;
import java.util.*;

public class G5로봇청소기14503 {
    static int N, M;
    static int nR, nC, nD, res = 0;
    static int map[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};

    public static void explore(int d){
        if(nD-1 < 0){
            nD += 4;
        }
        nD -= 1;
        int nx = nR + dx[nD];
        int ny = nC + dy[nD];

        if(map[nx][ny] == 0){
            nR = nx;
            nC = ny;
            return;
        }
        else{
            //회전
            if(d < 3){
                explore(d+1);
            }
            //후진
            else if(d == 3){
                nx -= dx[nD]*2;
                ny -= dy[nD]*2;

                if(map[nx][ny] == 1){                    
                    System.out.println(res);
                    System.exit(0);
                }
                else{
                    nR = nx;
                    nC = ny;
                    explore(0);
                }
            }
            
        }
    }
    
    public static boolean robotclean(){
        //현재 위치 청소
        map[nR][nC] = 2;
        res++;        

        int d = 0;
        explore(d);
        
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        
        nR = Integer.parseInt(st.nextToken());
        nC = Integer.parseInt(st.nextToken());
        nD = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(robotclean());

    }
}
//https://www.acmicpc.net/problem/14503