import java.io.*;
import java.util.*;

public class B1색종이2563 {
    static int N, res;
    static int map[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[101][101];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            map[x+10][y] += -1;
            map[x][y] += 1;
            map[x][y+10] += -1;
            map[x+10][y+10] += 1;
            
        }

       
        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 100; j++){
                map[i][j+1] += map[i][j];
            } 
        }

        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 100; j++){
                map[j+1][i] += map[j][i];
            }
        }
        for(int i = 0; i < 101; i++){
            for(int j = 0; j < 101; j++){
                if(map[i][j] != 0)
                    res++;
            }
        }
        
        System.out.println(res);
        
    }
}
