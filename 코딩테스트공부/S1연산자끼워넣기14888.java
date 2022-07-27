import java.io.*;
import java.util.*;

public class S1연산자끼워넣기14888 {
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int cnt = 0;
    static int map[];
    static int d[];

    public static void dfs(int depth, int sum){
        if(depth == N-1){
            cnt++;
            max = Math.max(sum, max);
            min = Math.min(sum, min);
            return;
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < d[i]; j++){
                d[i]--;
                if(i == 0){
                    dfs(depth+1, sum+map[depth+1]);
                }
                else if(i == 1){
                    dfs(depth+1, sum-map[depth+1]);
                }
                else if(i == 2){
                    dfs(depth+1, sum*map[depth+1]);
                }
                else{
                    dfs(depth+1, sum/map[depth+1]);
                }
                d[i]++;
            }
        }
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N];
        d = new int[4];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            map[i] = Integer.parseInt(st.nextToken());            
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            d[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, map[0]);
        System.out.println(max + "\n" + min);       


    }
}
//https://www.acmicpc.net/problem/14888