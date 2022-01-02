import java.io.*;
import java.util.*;

public class G5로봇청소기14503 {
    static int N, M;
    static int nR, nC, nD;
    static int map[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    
    public static void robotclean(){

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        nv = br.readLine().split(" ");
        nR = Integer.parseInt(nv[0]);
        nC = Integer.parseInt(nv[1]);
        nD = Integer.parseInt(nv[2]);

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }


    }
}
