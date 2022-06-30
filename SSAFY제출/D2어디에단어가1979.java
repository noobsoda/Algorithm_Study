package SSAFY제출;

import java.util.*;
import java.io.*;

class D2어디에단어가1979
{        
    static int N, K;
    static int map[][];
    
	public static void main(String args[]) throws Exception
    {

        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int result = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            map = new int[N][N];
            
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());                    
                }                
            }
            
            for(int i = 0; i < N; i++){
                int k = 0;
                for(int j = 0; j < N; j++){
                    if(map[i][j] == 1)             		       
                        k++;                    
                    if(map[i][j] == 0){
                     	if(k == K)    result++;                           
                        k = 0;
                    }                    
                }
                if(k == K)    result++;
                      
            }
            for(int i = 0; i < N; i++){                
                int k = 0;                
                for(int j = 0; j < N; j++){
                    if(map[j][i] == 1)                        
                      	k++;                         
                    if(map[j][i] == 0){
                     	if(k == K)    result++;                           
                        k = 0;
                    }                    
                }
                if(k == K)    result++;
                
                                
            }
                    
                
                
            
			System.out.println("#" + test_case + " " + result);
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PuPq6AaQDFAUq&categoryId=AV5PuPq6AaQDFAUq&categoryType=CODE&problemTitle=1979&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1