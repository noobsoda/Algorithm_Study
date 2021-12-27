import java.io.*;
import java.util.*;

public class G3최소비용구하기2_11779 {
    static int N, M, start, end;
    static LinkedList<LinkedList<Node>> list;
    static LinkedList<LinkedList<Integer>> temp;
    static PriorityQueue<Node> pq;
    static int dist[];
    static boolean visit[];

    public static void dijkstra(){
        pq.add(new Node(start, 0));
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(visit, false);

        dist[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visit[now.end]) continue;
            visit[now.end] = true;

            for(Node num : list.get(now.end)){
                int weight = now.weight + num.weight;
                if(dist[num.end] > weight){                    
                    temp.get(num.end).clear();
                    temp.get(num.end).addAll(0, temp.get(now.end));
                    temp.get(num.end).add(num.end);
                    dist[num.end] = weight;
                    pq.add(new Node(num.end, weight));
                }
            }

        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new LinkedList<>();
        pq = new PriorityQueue<>();
        dist = new int[N+1];
        visit = new boolean[N+1];
        String nv[];

        list = new LinkedList<>();
        temp = new LinkedList<>();
        for(int i = 0; i <= N; i++){
            list.add(new LinkedList<>());
            temp.add(new LinkedList<>());
        }

        for(int i = 0; i < M; i++){
            nv = br.readLine().split(" ");
            int s = Integer.parseInt(nv[0]);
            int e = Integer.parseInt(nv[1]);
            int w = Integer.parseInt(nv[2]);
            list.get(s).add(new Node(e, w));
        }
        nv = br.readLine().split(" ");
        start = Integer.parseInt(nv[0]);
        end = Integer.parseInt(nv[1]);

        dijkstra();

        System.out.println(dist[end]);
        //시작지점과 출발지점이 일치하고 temp가 비어있다면
        if(temp.get(end).isEmpty())
            temp.get(end).add(start);

        //시작지점에 들어있지 않다면
        if(temp.get(end).get(0) != start){
            System.out.println(temp.get(end).size()+1);
            System.out.print(start + " ");
        }
        //시작지점이 들어있으면
        else    
            System.out.println(temp.get(end).size());

        for(int i = 0; i < temp.get(end).size(); i++){
            System.out.print(temp.get(end).get(i) + " ");    
        }
    }    

    static class Node implements Comparable<Node>{
        int end, weight;
        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }    
        @Override
        public int compareTo(Node o){
            return weight - o.weight;
        }  
        
    }
}
//https://www.acmicpc.net/problem/11779