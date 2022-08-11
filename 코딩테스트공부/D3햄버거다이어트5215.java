package 코딩테스트공부;
import java.util.*;
import java.io.*;

public class D3햄버거다이어트5215
{
    static int N, L, max;
    static int score[];
    static int taste[];
    
    public static void dfs(int start, int sumtaste, int sumscore, int limit){
        if(sumtaste >= limit || start == N){
                     
            max = Math.max(max, sumscore);
            return;
        }
        for(int i = start; i < N; i++){
            
            if(sumtaste + taste[i] > limit)
            	dfs(i+1, sumtaste + taste[i], sumscore, limit);
            else
            	dfs(i+1, sumtaste + taste[i], sumscore + score[i], limit);
        }
        
    }
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{           
            max = 0;
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            
            score = new int[N];
            taste = new int[N];
            
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                taste[i] = Integer.parseInt(st.nextToken());
            }
            
            dfs(0, 0, 0, L);
               
			
            System.out.println("#" + test_case + " " + max);
		}
	}
}