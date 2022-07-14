package SSAFY제출;

import java.util.*;
import java.io.*;

class Solution
{
    static Queue<Node> q;
    static boolean visited[][];
	static int map[][];
    static Node start, end;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    
    public static boolean bfs(){
        q.add(start);
        visited[start.x][start.y] = true;
        while(!q.isEmpty()){
            Node now = q.poll();
            if(end.x == now.x && end.y == now.y)
                return true;
            
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= 16 || ny >= 16)	continue;
                if(map[nx][ny] == 1 || visited[nx][ny]) continue;
                
                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
                
            }
        }
        return false;
        
    }
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
				
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            
            st = new StringTokenizer(br.readLine());
            st.nextToken();
			map = new int[16][16];
            visited = new boolean[16][16];
            q = new LinkedList<>();
            start = new Node(-1, -1);
            end = new Node(-1, -1);
                  
            for(int i = 0; i < 16; i++){
                String s = br.readLine();                
                for(int j = 0; j < 16; j++){
                    map[i][j] = s.charAt(j) - '0';       
                    if(map[i][j] == 2){
                        start.x = i;
                        start.y = j;                     	   
                    }
                    else if(map[i][j] == 3){
                        end.x = i;
                        end.y = j;
                    }                        
                }
            }
            int answer = 0;
            if(bfs())	answer = 1;	
           
			System.out.println("#" + test_case + " " + answer);
		}
	}
    static class Node{
        int x, y;
        public Node(int x, int y){
         	this.x = x;
            this.y = y;
        }
        
    }
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14vXUqAGMCFAYD&categoryId=AV14vXUqAGMCFAYD&categoryType=CODE&problemTitle=1226&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1