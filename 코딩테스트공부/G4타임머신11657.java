package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G4타임머신11657 {
    static final int INF = 987654321;
    static int N, M;
    static long dist[];
    static Node node[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        node = new Node[M+1];
        dist = new long[N+1];
        
        Arrays.fill(dist, INF);
            
        for(int i = 0; i < M; i++){
            
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            node[i] = new Node(a, b, c);
        }

        StringBuilder sb = new StringBuilder();

        if(BellmanFord()){
            for(int i = 2; i <= N; i++){
                sb.append(dist[i] == INF ? "-1\n" : dist[i] + "\n");
            }

        }
        else{
            sb.append("-1\n");            
        }
        bw.write(sb.toString());
        bw.flush();

        

    }    
    private static boolean BellmanFord() {
        dist[1] = 0;

        for(int i = 1; i < N; i++){
            for(int j = 0; j < M; j++){
                Node now = node[j];

                if(dist[now.s] != INF && dist[now.e] > dist[now.s] + now.w){
                    dist[now.e] = dist[now.s] + now.w;
                }
            }
        }

        for(int i = 0; i < M; i++){
            Node now = node[i];

            if(dist[now.s] != INF && dist[now.e] > dist[now.s] + now.w) return false;
        }
        return true;
    }
    static class Node{
        int s, e, w;
        public Node(int s, int e, int w){
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
//https://www.acmicpc.net/problem/11657