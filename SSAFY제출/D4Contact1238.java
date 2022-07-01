package SSAFY제출;

import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")

class D4Contact1238
{
    static ArrayList<Integer>[] list;
    static Queue<Node> q;
    static boolean visited[];
    
    public static int cnetwork(int start){
        int max = 0;
        int d = 0;
        q = new LinkedList<>();
        visited = new boolean[101];
        
        q.add(new Node(start, 0));
        visited[start] = true;
        
        while(!q.isEmpty()){
            
            Node now = q.poll();
            
            for(int n : list[now.n]){
                if(visited[n]) continue;
                
                visited[n] = true;
                q.add(new Node(n, now.d+1));     
            }
           
            if(d < now.d){                
            	max = 0;
                d = now.d;
            }
             max = Math.max(now.n, max);            
        }
        return max;
        
    }
	public static void main(String args[]) throws Exception
	{		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;	
		int T=10;	

		for(int test_case = 1; test_case <= T; test_case++)
		{			
        	st = new StringTokenizer(br.readLine(), " ");
			
        	int N = Integer.parseInt(st.nextToken());
        	int start = Integer.parseInt(st.nextToken());
            list = new ArrayList[101];
            for(int i = 0; i <= 100; i++){
                list[i] = new ArrayList<>();                
            }
            
            st = new StringTokenizer(br.readLine(), " ");

            for(int i = 0; i < N/2; i++){                
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    
                    list[a].add(b);        
            }            
            System.out.println("#" + test_case + " " + cnetwork(start));
            
		}
	}
    static class Node{
        int n, d;
        public Node(int n, int d){
            this.n = n;
            this.d = d;         	   
        }
        
    }
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD&categoryId=AV15B1cKAKwCFAYD&categoryType=CODE&problemTitle=1238&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1