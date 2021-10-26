import java.io.*;
import java.util.*;

public class G4나무재테크16234 {
    static int N, M, K;
    static int pmap[][];
    static int map[][];
    static int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    static List<Node> arr;
    static Node node;

    public static void spring(){
        for(Iterator<Node> it = arr.iterator(); it.hasNext();){
            node = it.next();
            if(map[node.x][node.y] >= node.z){
                map[node.x][node.y] -= node.z;
                node.z++;
            }   
            else{
                node.alive = false;
            }
        }
        
    }
    public static void summer(){    
        for(Iterator<Node> it = arr.iterator(); it.hasNext();){
            node = it.next();
            if(node.alive == false){
                map[node.x][node.y] += node.z / 2;
                it.remove();
            } 
        }
       
    }

    public static void autumn(){
        List<Node> newarr = new LinkedList<>();
        for(Iterator<Node> it = arr.iterator(); it.hasNext();){
            node = it.next();
            if(node.z % 5 == 0){
                for(int j = 0; j < 8; j++){                    
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];
                    if(nx < 1 || ny < 1 || nx > N || ny > N)    continue;
                    newarr.add(new Node(nx, ny, 1, true));
                }
            }
        }
        arr.addAll(0, newarr);
        
    }

    public static void winter(){
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                map[i][j] += pmap[i][j];
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        String[] nv = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        K = Integer.parseInt(nv[2]);

        map = new int[N+1][N+1];
        pmap = new int[N+1][N+1];
        arr = new LinkedList<>();

        for(int i = 1; i <= N; i++){
            nv = br.readLine().split(" ");
            for(int j = 1; j <= N; j++){
                map[i][j] = 5;
                pmap[i][j] = Integer.parseInt(nv[j-1]);
            }
        }
        for(int i = 0; i < M; i++){
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int y = Integer.parseInt(nv[1]);
            int z = Integer.parseInt(nv[2]);
            arr.add(new Node(x, y, z, true));
        }

        for(int i = 0; i < K; i++){
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(arr.size());
        



    }
    static class Node implements Comparable<Node>{
        int x, y, z;
        boolean alive;
        public Node(int x, int y, int z, boolean alive){
            this.x = x;
            this.y = y;
            this.z = z;
            this.alive = alive;
        }
        @Override
        public int compareTo(Node o){
            return this.z - o.z;
        }
        
    }
}
