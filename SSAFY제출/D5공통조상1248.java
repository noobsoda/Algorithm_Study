package SSAFY제출;

import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")

class D5공통조상1248
{
    static int V, E, V1, V2;
    static ArrayList<Integer> arr[];
    static ArrayList<Integer> rearr[];
    static Queue<Integer> q;
    static boolean visited[];
    
    public static int bfs(){        
        q.add(V1);
        q.add(V2);
        visited[V1] = true;
        visited[V2] = true;
        
        while(!q.isEmpty()){
            int n = q.poll();
            
            for(int now : rearr[n]){
                if(visited[now]){
                    return now;
                }
                q.add(now);
                visited[now] = true;
            }
            
        }
        return 0;
    }
    
	public static void main(String args[]) throws Exception
	{		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());        
		int T=Integer.parseInt(st.nextToken());
		
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());            
            V1 = Integer.parseInt(st.nextToken());
            V2 = Integer.parseInt(st.nextToken());
		
            arr = new ArrayList[V+1];
            rearr = new ArrayList[V+1];
            q = new LinkedList<>();
            visited = new boolean[V+1];
            
            for(int i = 0; i <= V; i++){
                arr[i] = new ArrayList<>();
                rearr[i] = new ArrayList<>();                
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < E; i++){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                arr[a].add(b);
                rearr[b].add(a);                
            }
            
            int res = bfs();
            
            q.clear();
            q.add(res);
            
            int cnt = 0;
            while(!q.isEmpty()){
                int n = q.poll();
                cnt++;
                for(int now : arr[n]){
                    q.add(now);                    
                }                
            }
            
            
            System.out.println("#" + test_case + " " + res + " " + cnt);
            
            
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15PTkqAPYCFAYD&categoryId=AV15PTkqAPYCFAYD&categoryType=CODE&problemTitle=1248&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1