import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class G2구슬탈출213460 {

    static char array[][];
    static int N, M, n1, n2;
    static Queue<location> q;
    //위 왼쪽 아래 오른쪽 순서
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int R1, R2, B1, B2;
    static boolean visit[][][][];
    
    public static int bfs(){
        q.add(new location(R1, R2, B1, B2, 1));
        visit[R1][R2][B1][B2] = true;

        while(!q.isEmpty()){
            location now = q.poll();

            for(int i = 0 ; i < 4; i++){
                int nRx = now.Rx;
                int nRy = now.Ry;
                int nBx = now.Bx;
                int nBy = now.By;
                int ncount = now.count;

                if(now.count > 10)
                    return -1;

                int redhole = 0;
                int bluehole = 0;
               
                while(array[nRx+dx[i]][nRy+dy[i]] != '#'){                    
                    nRx += dx[i];
                    nRy += dy[i];

                    if(array[nRx][nRy] == 'O'){
                        redhole = 1;
                        break;
                    }
                    
                }
                while(array[nBx+dx[i]][nBy+dy[i]] != '#'){                    
                    nBx += dx[i];
                    nBy += dy[i];   
                    
                    if(array[nBx][nBy] == 'O'){
                        bluehole = 1;
                        break;
                    }
                }                
                              
                if(bluehole == 1)
                    continue;

                if(redhole == 1 && bluehole != 1)
                    return ncount;

                if(nRx == nBx && nRy == nBy){
                    if(i == 0)                    
                        if(now.Rx < now.Bx)
                            nBx -= dx[i];
                        else
                            nRx -= dx[i];
                    else if(i == 1)
                        if(now.Ry < now.By)
                            nBy -= dy[i];
                        else
                            nRy -= dy[i];
                    else if(i == 2)
                        if(now.Rx < now.Bx)
                            nRx -= dx[i];
                        else
                            nBx -= dx[i];
                    else
                        if(now.Ry < now.By)
                            nRy -= dy[i];
                        else
                            nBy -= dy[i];
                }

                
                if(visit[nRx][nRy][nBx][nBy] == false){                    
                    visit[nRx][nRy][nBx][nBy] = true;                    
                    q.add(new location(nRx, nRy, nBx, nBy, ncount+1));
                }                    
            }
            
        }
        return -1;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        array = new char[N][M];
        visit = new boolean[N][M][N][M];
        q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            String Str = br.readLine();
            for(int j = 0; j < M; j++){
                array[i][j] = Str.charAt(j);
                if(Str.charAt(j) == 'R'){
                    R1 = i;
                    R2 = j;
                }
                if(Str.charAt(j) == 'B'){
                    B1 = i;
                    B2 = j;
                }                
            }
        }

        System.out.println(bfs());

    }
    static class location{
        int Rx, Ry, Bx, By, count;
        public location(int Rx, int Ry, int Bx, int By, int count){
            this.Rx = Rx;
            this.Ry = Ry;
            this.Bx = Bx;
            this.By = By;
            this.count = count;
        }
    }

}

//https://www.acmicpc.net/problem/13460A