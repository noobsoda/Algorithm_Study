package 코딩테스트공부;

import java.util.*;
import java.io.*;

class 모역_디저트카페2105
{
    static int N, answer;
    static int dx[] = {1, 1, -1, -1};
    static int dy[] = {1, -1, -1, 1};
    static int map[][];
    static int dessert[];
    public static void dfs(int x, int y, int ox, int oy, int d, int length, boolean flag){

        if(d == 4) return;
        
        if(x == ox && y == oy && flag){
            answer = Math.max(answer, length); 
            return;
        }
        

        if(x < 0 || y < 0 || x >= N || y >= N) return;

        if(dessert[map[x][y]] == 0){
            dessert[map[x][y]] = 1;
            dfs(x + dx[d], y + dy[d], ox, oy, d, length+1, true);

            dfs(x + dx[(d+1) % 4] , y + dy[(d+1) % 4], ox, oy, d+1, length+1, true);

            dessert[map[x][y]] = 0;

        }
        else
            return;
        
        
           
        
    }
	public static void main(String args[]) throws Exception
	{		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new  StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{            
            answer = -1;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            map = new int[N][N];
            dessert = new int[101];
            
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){                    
                    map[i][j] = Integer.parseInt(st.nextToken());                    
                }                
            }
            
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dfs(i, j, i, j, 0, 0, false);
                    //dfs들어갈곳
                }                
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
