package 코딩테스트공부;
import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G4최단경로1753 {
    static int V, E, S;
    static ArrayList<Node> arr[];    
    static int dist[];
    static boolean visited[];
    static PriorityQueue<Node> pq;
    static final int INF = 987654321;

    public static void dijkstra(){

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(Node narr : arr[now.e]){
                if(visited[narr.e]) continue;

                if(dist[narr.e] > dist[now.e] + narr.w){
                    dist[narr.e] = dist[now.e] + narr.w;
                    pq.add(new Node(narr.e, dist[narr.e]));
                }

            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        arr = new ArrayList[V+1];
        dist = new int[V+1];
        visited = new boolean[V+1];
        pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= V; i++){
            arr[i] = new ArrayList<>();
            dist[i] = INF;
        }

        dist[S] = 0;
        visited[S] = true;

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[s].add(new Node(e, w));
        }

        pq.add(new Node(S, 0));

        dijkstra();

        for(int i = 1; i <= V; i++){
            bw.write((dist[i] == INF) ?  "INF\n" : dist[i] + "\n");            
        }
        bw.flush();
    }

    static class Node{
        int e, w;
        public Node(int e, int w){
            this.e = e;
            this.w = w;            
        }
    }
}
//https://www.acmicpc.net/problem/1753