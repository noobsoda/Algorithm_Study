package 코딩테스트공부;
import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")

public class G3학교탐방하기13418 {
    static int N, M;
    static ArrayList<Node> arr[];   
    static int parents[];
    static int dist[];
    static boolean visited[];
    static PriorityQueue<Node> pq1;
    static PriorityQueue<Node> pq2;
    static int S, E, W;

    public static void Prim(){
        pq2.addAll(arr[0]);

        int cnt = 0;
        while(cnt <= N){
            Node now = pq2.poll();

            if(visited[now.e])  continue;
            visited[now.e] = true;
            cnt++;

            dist[now.e] = now.w;
            pq2.addAll(arr[now.e]);


        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        pq1 = new PriorityQueue<>((o1, o2) -> o2.w-o1.w);
        pq2 = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);
        parents = new int[N+1];
        visited = new boolean[N+1];
        dist = new int[N+1];
        
        for(int i = 0; i <= N; i++){
            parents[i] = i;
            arr[i] = new ArrayList<>();
            dist[i] = 1;
        }
        
        int k1 = 0;
        int k2 = 0;
        
        for(int i = 0; i < M+1; i++){
            st = new StringTokenizer(br.readLine());            
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq1.add(new Node(s, e, w));           
            arr[s].add(new Node(e, w));
            arr[e].add(new Node(s, w));

            if(s == 0)
                dist[0] = w;
        }
      
        
        while(!pq1.isEmpty()){
            Node now = pq1.poll();
            
            if(Union(now.s, now.e)){
                if(now.w == 0)
                    k1++;

            }
        }

        pq2.add(new Node(0, 0));
        Prim();

        for(int i = 1; i <= N; i++)
            if(dist[i] == 0)
                k2++;

        System.out.println(k2*k2 - k1*k1);



    }

    public static int Find(int x){
        if(parents[x] == x) return x;
        return parents[x] = Find(parents[x]);
    }
    public static boolean Union(int x, int y){
        int xroot = Find(x);
        int yroot = Find(y);

        if(xroot == yroot) return false;

        parents[yroot] = xroot;
        return true;
        
        

    }


    static class Node{
        int s, e, w;
        public Node(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }
        public Node(int e, int w){
            this.e = e;
            this.w = w;
        }

    }

    
}
//https://www.acmicpc.net/problem/13418