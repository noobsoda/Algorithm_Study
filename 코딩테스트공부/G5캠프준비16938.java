package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G5캠프준비16938 {
    static int N, L, R, X;
    static int map[];
    static int cnt = 0;

    public static void dfs(int index, int depth, int depthmax, int sum, int min, int max){
        if(depth == depthmax){
            //X, L, R 체크
            if(sum >= L && sum <= R && max - min >= X){
                cnt++;
            }
            return;
        }


        for(int i = index+1; i < map.length; i++){            
            int n = sum + map[i];
            int nmin = Math.min(min, map[i]);
            int nmax = Math.max(max, map[i]);
            //n > R 경우 return 정렬 했기 때문에 이후 값 크기가 더 커서, 현재 상태의 for문 돌릴 필요 없음
            
            if(n > R) return;
            dfs(i, depth+1, depthmax, n, nmin, nmax);            
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(map);        

        for(int i = 2; i <= map.length; i++){
            dfs(-1, 0, i, 0, Integer.MAX_VALUE, 0);
        }

        System.out.println(cnt);
    }
}
//https://www.acmicpc.net/problem/16938