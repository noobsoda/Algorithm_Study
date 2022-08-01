package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S3스위치켜고끄기1244 {
    static int N, M;
    static boolean switchmap[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        switchmap = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int n = Integer.parseInt(st.nextToken());

            switch(n){
                case 0:
                    switchmap[i] = false;
                    break;
                case 1:
                    switchmap[i] = true;

            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int sw = Integer.parseInt(st.nextToken());

            if(gender == 1){
                for(int j = sw; j <= N; j += sw){                    
                    switchmap[j] = !switchmap[j];                    
                }
            }
            else{                
                switchmap[sw] = !switchmap[sw];

                int j = sw;
                int k = sw;
                boolean flag = true;
                while(j >= 1 && k <= N && flag){
                    j--;
                    k++;
                    if(j < 1 || k > N)
                        break;
            

                    if(switchmap[j] == switchmap[k]){
                        switchmap[j] = !switchmap[j];
                        switchmap[k] = !switchmap[k];

                        flag = true;
                    }
                    else{
                        flag = false;
                    }

                }
                
            }
        }
    
        for(int i = 1; i <= N; i++){
            if(switchmap[i])
                bw.write(1 + " ");
            else
                bw.write(0 + " ");

            if(i % 20 == 0)
                bw.write("\n");
        }

        bw.flush();






    }
}
//비트마스킹으로 0,1 바꾸는법 없을까
//https://www.acmicpc.net/problem/1244