package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G1비숍1799 {
    static int N, max = 0;
    static int map[][];
    static int dx[] = {-1, -1, 1, 1};
    static int dy[] = {-1, 1, -1, 1};

    public static boolean check(int x, int y){
        for(int i = 0; i < 4; i++){
            boolean flag = true;
            int nx = x;
            int ny = y;
            while(flag){
                nx += dx[i];
                ny += dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) break;

                if(map[nx][ny] == 2)
                    return false;
            }

        }
        return true;
    }

    public static void bishop(int x, int y, int cnt){
        if(x >= N && y > N){
            max = Math.max(max, cnt);
            return;
        }
        if(y > N){
            bishop(x+1, y, cnt);
            return;
        }
        if(map[x][y] == 1){
            if(check(x, y)){
                map[x][y] = 2;
                bishop(x, y+1, cnt+1);
                map[x][y] = 1;
            }
        }
        else{
            bishop(x, y+1, cnt);
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

        bishop(0, 0, 0);
        System.out.println(check(0, 0) ? "yes" : "No");
    }
    
}
//https://www.acmicpc.net/problem/1799