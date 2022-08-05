package 코딩테스트공부;


import java.util.*;
import java.io.*;

public class D3암호생성기1225
{
    static Queue<Integer> q;
    static int map[];
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		StringTokenizer st;		
		
		for(int test_case = 1; test_case <= 1; test_case++)
		{
            map = new int[8];
			st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            q = new ArrayDeque<>();
            
            for(int i = 0; i < 8; i++){
                q.add(Integer.parseInt(st.nextToken()));     
            }
            
            Exit:
            while(!q.isEmpty()){
                int cnt = 0;
                for(int i = 0; i < 5; i++){
                    cnt++;
                    int now = q.poll();
                                        
                    if(now-cnt <= 0){                 
                        q.add(0);
                    	break Exit;
                    }

                    q.add(now-cnt);
                }
            }
            int cnt = 0;
            while(!q.isEmpty()){
                map[cnt++] = q.poll();                
            }
            
            
            
            bw.write("#" + n + " ");
            for(int i = 0; i < 8; i++){
                bw.write(map[i] + " ");                
            }
            bw.write("\n");
			
		}
        bw.flush();
	}
}
