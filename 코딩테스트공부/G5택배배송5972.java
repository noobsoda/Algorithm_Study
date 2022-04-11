package 코딩테스트공부;
import java.io.*;
import java.util.*;
@SuppressWarnings("unchecked")

public class G5택배배송5972 {
    static int N, M;
    static PriorityQueue<Node> pq;
    static ArrayList<Node> arr[];
    static int dist[];
    static boolean visited[];

    public static void dijkstra(int start){
        pq.add(new Node(start, 0));
        visited[start] = true;

        while(!pq.isEmpty()){
            Node nq = pq.poll();
            visited[nq.end] = true;
            for(Node narr : arr[nq.end]){
                if(visited[narr.end]) continue;

                if(dist[narr.end] > narr.w + nq.w){                   
                    dist[narr.end] = narr.w + nq.w;
                    pq.add(new Node(narr.end, narr.w + nq.w));
                }

            }
        }
        

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        arr = new ArrayList[N+1];
        dist = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i <= N; i++){
            arr[i] = new ArrayList<>();
            dist[i] = 987654321;
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }

        dijkstra(1);
        System.out.println(dist[N]);

    }
    static class Node implements Comparable<Node>{
        int end, w;
        public Node(int end, int w){
            this.end = end;
            this.w = w;            
        }
        @Override
        public int compareTo(Node o){
            return w-o.w;
        }
    }
}
//https://www.acmicpc.net/problem/5972