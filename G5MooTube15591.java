import java.io.*;
import java.util.*;

public class G5MooTube15591 {
    static int N, Q;
    static ArrayList<Node>[] list;
    static int cnt[];
    static boolean visited[];
    static Queue<Node> q;

    public static int MooTube(int k, int n){
        int ans = 0;
        Arrays.fill(visited, false);        

        q.add(new Node(n, Integer.MAX_VALUE));
        visited[n] = true;
        while(!q.isEmpty()){
            Node now = q.poll();

            for(Node nowlist : list[now.end]){
                int weight = Math.min(now.weight, nowlist.weight);
                if(visited[nowlist.end])
                    continue;
                if(weight >= k){
                    ans++;
                    visited[nowlist.end] = true;
                    q.add(new Node(nowlist.end, weight));
                }
            }
        }

        return ans;

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        Q = Integer.parseInt(nv[1]);

        list = new ArrayList[N+1];

        visited = new boolean[N+1];
        // cnt = new int[Q];
        q = new LinkedList<>();
        for(int i = 1; i <= N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++){
            nv = br.readLine().split(" ");
            int start = Integer.parseInt(nv[0]);
            int end = Integer.parseInt(nv[1]);
            int weight = Integer.parseInt(nv[2]);

            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }
        

        for(int i = 0; i < Q; i++){
            nv = br.readLine().split(" ");
            int k = Integer.parseInt(nv[0]);
            int v = Integer.parseInt(nv[1]);

            int res = MooTube(k, v);  
            
            System.out.println(res);
           
        }
        // for(int i = 0; i < Q; i++){
        //     System.out.println(cnt[i]);
        // }
        

    }    
    static class Node{
        int end, weight;
        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
        
    }
}

//https://www.acmicpc.net/problem/15591