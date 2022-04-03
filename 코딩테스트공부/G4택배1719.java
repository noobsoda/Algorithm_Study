package 코딩테스트공부;
import java.io.*;
import java.util.*;
public class G4택배1719 {
    static int N, M;
    static int map[][];
    static int res[][];
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        res = new int[N+1][N+1];


        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= N; j++){
                map[i][j] = INF;
                if(i == j){
                    map[i][j] = 0;
                    res[i][j] = 0;
                }
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[a][b] = w;
            map[b][a] = w;
            res[a][b] = b;
            res[b][a] = a;
        }
        for(int i = 1; i <= N; i++){
            map[i][i] = 0;
        }

        

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                        res[i][j] = res[i][k];
                    }

                }
            }
        }
        
        for(int i = 1; i <= N; i++)
            System.out.println(Arrays.toString(res[i]));


        for(int i = 1; i <= N; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j <= N; j++){
                if(res[i][j] == 0){
                    sb.append('-');
                    sb.append(' ');
                    continue;
                }
                sb.append(res[i][j]);
                sb.append(' ');
            }
            System.out.println(sb.toString());
        }
    }
}
//https://www.acmicpc.net/submit/1719
//와 1등