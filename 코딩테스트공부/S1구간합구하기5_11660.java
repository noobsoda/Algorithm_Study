import java.io.*;
import java.util.*;

public class S1구간합구하기5_11660 {
    static int N, M;
    static int map[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }
        //2차원 누적합
        //가로
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N-1; j++){
                map[i][j+1] += map[i][j];
            }
        }
        //세로
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N-1; j++){
                map[j+1][i] += map[j][i];
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x1, y1, x2, y2;

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());


            int res = map[x2][y2] - (map[x1-1][y2] + map[x2][y1-1] - map[x1-1][y1-1]);
            bw.write(res + "\n");
            
        }


        bw.flush();


    }
}
