import java.io.*;
import java.util.*;

public class G5감시15683 {
    static int N, M;
    static int map[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static List<Point> list;
    
    public static void watch(int depth){
        if(depth == list.size()){
            return;
        }
        else{
            
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
                if(map[i][j] != 0 || map[i][j] != 6){
                    list.add(new Point(i, j, map[i][j]));
                }
            }
        }



    }
    static class Point{
        int x, y, d;
        public Point(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
