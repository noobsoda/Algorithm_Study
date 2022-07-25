package 코딩테스트공부;

import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")

public class S2DFS와BFS1260 {
    static int N, M, V;
    static boolean visited[];    
    static StringBuilder sb;
    static ArrayList<Integer> arr[];
    

    private static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();

        q.add(v);
        visited[v] = false;
        sb.append(v + " ");

        while(!q.isEmpty()){
            int qv = q.poll();

            for(int now : arr[qv]){
                if(!visited[now])   continue;

                q.add(now);
                visited[now] = false;
                sb.append(now + " ");
            }
            
        }


    }
    private static void dfs(int v) {
        for(int now : arr[v]){
            if(visited[now])    continue;

            visited[now] = true;
            sb.append(now + " ");
            dfs(now);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        
        arr = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i <= N; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);

        }
        for(int i = 0; i <= N; i++){
            Collections.sort(arr[i]);
        }

        visited[V] = true;

        sb = new StringBuilder();
        sb.append(V + " ");
        dfs(V);
        System.out.println(sb.toString());

        sb.setLength(0);

        bfs(V);
        System.out.println(sb.toString());




    }
    
    
    
}
//https://www.acmicpc.net/problem/1260