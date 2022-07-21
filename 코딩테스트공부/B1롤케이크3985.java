package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class B1롤케이크3985 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L, N;
        int max = 0, wp = 0, maxc = 0, mp = 0;
        
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int map[] = new int[L+1];


        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            if(max < l-p){
                max = l-p;
                wp = i+1;
            }
            int cnt = 0;
            for(int j = p; j <= l; j++){                
                if(map[j] != 0) continue;

                map[j] = i+1;
                cnt++;
            }
            if(maxc < cnt){
                maxc = cnt;
                mp = i+1;
            }
            
        }
        System.out.println(wp);
        System.out.println(mp);




    }
}
//https://www.acmicpc.net/problem/3985