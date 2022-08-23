import java.io.*;
import java.util.*;

public class G5ABCDE13023 {
    static int N, M;
    static boolean visited[];
    static ArrayList<Integer> arr[];
    static PriorityQueue<Node> pq;
    static int dist[];

    
    
    public static boolean dfs(int start, int depth){
        if(depth == 5)
            return true;

        for(int now : arr[start]){
            if(visited[now])    continue;

            visited[now] = true;
            if(dfs(now, depth+1))   return true;
            visited[now] = false;

        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N];
        visited = new boolean[N];
        arr = new ArrayList[N];
        pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for(int i = 0; i < N; i++){
            arr[i] = new ArrayList<>();
        }




        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
            dist[a]++;
            dist[b]++;
        }
        for(int i = 0; i < N; i++){
            pq.add(new Node(dist[i], i));
        }
        boolean flag = false;

       while(!pq.isEmpty()){
            Node now = pq.poll();
            visited[now.e] = true;
            if(dfs(now.e, 1)){
                flag = true;                
            }
            visited[now.e] = false;;
        }
                
        System.out.println(flag ? 1 : 0);



        //한붓그리기 5개 이상?

    }
    static class Node{
        int w, e;
        public Node(int w, int e){
            this.w = w;
            this.e = e;
        }
    }
    
}
//https://www.acmicpc.net/problem/13023