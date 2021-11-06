import java.io.*;
import java.util.*;


public class G4사다리조작15684 {
    static int N, M, H;
    static int leastline = -1;
    static boolean visited[][];
    static ArrayList<ArrayList<Node>> arr;

    public static boolean check(){
        for(int i = 1; i <= N; i++){
            int n = i;

            for(int j = 1; j <= H; j++){
                for(int k = 0; k < arr.get(j).size(); k++){
                    Node now = arr.get(j).get(k);
                    if(now.a == n){
                        n = now.b;
                    }
                    else if(now.b == n){
                        n = now.a;
                    }
                }                
            }
            // System.out.println(n);

            if(n != i){
                return false;
            }
        }
        return true;
    }
    public static boolean ladder(int h, int n, int depth, int max){
        if(depth == max){
            if(check()){
                leastline = depth;
                return true;
            }            
        }
        
        else{
            for(int i = h; i <= H; i++){
                for(int j = n; j <= N; j++){
                    // 양 옆에 가로줄 있으면 넘기기
                    if(visited[i][j] || visited[i][j-1] || visited[i][j+1])
                        continue;
                    visited[i][j] = true;
                    arr.get(i).add(new Node(j, j+1));
                    if(ladder(h, n, depth+1, max)) return true;
                    visited[i][j] = false;
                    arr.get(i).remove(new Node(j, j+1));
                }
            }
        }        
        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        H = Integer.parseInt(nv[2]);
        arr = new ArrayList<>();
        visited = new boolean[H+1][N+2];
        for(int i = 0; i <= H; i++){
            arr.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++){
            nv = br.readLine().split(" ");
            int a = Integer.parseInt(nv[0]);
            int b = Integer.parseInt(nv[1]);

            arr.get(a).add(new Node(b, b+1));
            visited[a][b] = true;
        }
        
        if(check())
            leastline = 0;
        else{
            for(int i = 1; i <= 3; i++){
                if(ladder(1, 1, 0, i)){
                    break;
                }    
            }
        }

        System.out.println(leastline);


        
    }
    static class Node{
        int a, b;
        public Node(int a, int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object object){
            Node node = (Node)object;
            if(node.a == this.a && node.b == this.b)
                return true;
            
            return false;


        }
    }
    
}

//M이 여러개여도 0이 나올수 있다는 사실을 파악하지 못함
//https://www.acmicpc.net/problem/15684