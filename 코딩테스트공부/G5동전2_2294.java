package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G5동전2_2294 {
    static int N, K;
    static int map[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[K+1];

        map[0] = 1;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            for(int j = 1; j <= K; j++){
                if(j - a < 0)   continue;
                map[j] = map[j-a] + map[j];
            }
        }

        System.out.println((map[K] == 0) ? -1 : 1);
    }    
}
