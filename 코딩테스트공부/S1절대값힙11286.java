package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S1절대값힙11286 {
    static int N;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if(n != 0){
                
                pq.add(new Node(n));
            }
            else{
                if(pq.isEmpty())    
                    System.out.println(0);
                else
                    System.out.println(pq.poll().n);
            }
        }
    }
    static class Node implements Comparable<Node>{
        int n;
        public Node(int n){
            this.n = n;
        }
        @Override
        public int compareTo(Node o) {
            if(Math.abs(this.n) == Math.abs(o.n)){
                return n - o.n;
            }
            return Math.abs(this.n) - Math.abs(o.n);
        }
    
    }
}


//https://www.acmicpc.net/problem/11286