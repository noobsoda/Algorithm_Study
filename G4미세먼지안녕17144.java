import java.io.*;

public class G4미세먼지안녕17144 {
    static int R, C, T, air[];
    static int map[][];
    static int tempmap[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {1, -1, 0, 0};

    public static int dustcheck(int dust){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] == -1) continue;
                dust += map[i][j];
            }
        }
        return dust;
    }
    //먼지 확산
    public static void spread(int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            //범위 벗어나거나 주변 값들이 더 크거나 같은 경우 컨티뉴
            if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == -1)
                continue;
            
            int diff = map[x][y] / 5;
            tempmap[nx][ny] += diff;
            tempmap[x][y] -= diff;
            
        }

    }
    

    public static void cleandust(){
        //현재 미세먼지 temp에 복사
        for(int i = 0; i < R; i++){
            System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                spread(i, j);
            }
        }

        //확산된 미세먼지 복사
        for(int i = 0; i < R; i++){
            System.arraycopy(tempmap[i], 0, map[i], 0, map[i].length);
        }  


        //다른 사람어떻게 구현했나 찾아보기
        //청정기 윗 부분
        for(int i = air[0]-2; i >= 0; i--){
            map[i+1][0] = map[i][0];
        }
        for(int i = 1; i < C; i++){
            map[0][i-1] = map[0][i];
        }
        for(int i = 1; i <= air[0]; i++){
            map[i-1][C-1] = map[i][C-1];
        }
        for(int i = C-2; i >= 0; i--){
            map[air[0]][i+1] = map[air[0]][i];
        }
        map[air[0]][1] = 0;

        //청정기 아래 부분
        
        for(int i = air[1]+2; i < R; i++){
            map[i-1][0] = map[i][0];
        }
        
        for(int i = 1; i < C; i++){
            map[R-1][i-1] = map[R-1][i];
        }
        
        for(int i = R-2; i >= air[1]; i--){
            map[i+1][C-1] = map[i][C-1];
        }

        for(int i = C-2; i >= 0; i--){
            map[air[1]][i+1] = map[air[1]][i];
        }
        
        map[air[1]][1] = 0;


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        R = Integer.parseInt(nv[0]);
        C = Integer.parseInt(nv[1]);
        T = Integer.parseInt(nv[2]);
        air = new int[2];
        map = new int[R][C];
        tempmap = new int[R][C];

        int k = 0;
        for(int i = 0; i < R; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(nv[j]);
                if(map[i][j] == -1){
                    air[k] = i;
                    k++;
                }
            }
        }
        for(int i = 0; i < T; i++){
            cleandust();
        }

        System.out.println(dustcheck(0));

    }
    
}

//https://www.acmicpc.net/problem/17144