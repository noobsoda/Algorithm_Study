package 코딩테스트공부;

import java.util.*;
import java.io.*;

public class D3재미있는오셀로4615
{
    static int N, M, B, W;
    static int map[][];
    static int dx[] = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int dy[] = {-1, 0, 1, 1, 1, 0, -1, -1};
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());        
		int T=Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            B = 0;
            W = 0;
           
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            map = new int[N+1][N+1];
            map[N/2][N/2] = 2;
            map[N/2][N/2+1] = 1;
            map[N/2+1][N/2] = 1;
            map[N/2+1][N/2+1] = 2;
            
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                
                map[x][y] = color;
                for(int j = 0; j < 8; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    
                    if(nx < 1 || ny < 1 || nx > N || ny > N)  continue;                               
                 
                    if(map[nx][ny] == map[x][y] || map[nx][ny] == 0)	continue;
                    
                        
                    boolean flag = false;
                      
                    while(map[nx][ny] != color && map[nx][ny] != 0){
                        nx += dx[j];
                        ny += dy[j];
                            
                                
                        if(nx < 1 || ny < 1 || nx > N || ny > N){
                            flag = true;
                            break;                            
                        }                            
                    }
                    if(flag || map[nx][ny] != color)    continue;                
                        
                    nx = x + dx[j];
                    ny = y + dy[j];
                    while(map[nx][ny] != color && map[nx][ny] != 0){                        
                        map[nx][ny] = color;
                        nx += dx[j];
                        ny += dy[j];
                    }
            	}                  
            }
            for(int i = 1; i <= N; i++){
                	for(int j = 1; j <= N; j++){
                  	    if(map[i][j] == 1)  B++;
                 	   else if(map[i][j] == 2) W++;
                	}
            	}
            System.out.println("#" + test_case + " " + B + " " + W);
		
			
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWQmA4uK8ygDFAXj&categoryId=AWQmA4uK8ygDFAXj&categoryType=CODE&problemTitle=4615&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1