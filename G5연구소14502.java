import java.io.*;
import java.util.*;

public class G5연구소14502 {
    static int N, M, max = 0;
    static int map[][];
    static boolean visited[][];
    static List<Node> list;
    static Queue<Node> q;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static void valuecheck(){

        int tempmap[][] = new int[N][M];
        for(int i = 0; i < N; i++){
            System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);
        }

        int count = 0;
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();
            q.add(new Node(now.x, now.y));
        }
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], false);
        }
        //bfs
        while(!q.isEmpty()){            
            Node n = q.poll();
            visited[n.x][n.y] = true;
            
            for(int i = 0; i < 4; i++){
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || tempmap[nx][ny] == 1) continue;

                if(!visited[nx][ny] && tempmap[nx][ny] == 0){
                    visited[nx][ny] = true; 
                    tempmap[nx][ny] = 1;
                    q.add(new Node(nx, ny));
                }
            }
        }
        
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(tempmap[i][j] == 0){
                    count++;
                }
            }
        }                
        if(max < count){
            max = count;
        }
        
    }

    public static void laboratory(int depth){
        if(depth == 3){
            valuecheck();
            return;
        }
        else{
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){

                    if(map[i][j] == 0){
                        map[i][j] = 1;
                        laboratory(depth+1);
                        map[i][j] = 0;
                    }
                }           
            }
            return;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        map = new int[N][M];
        visited = new boolean[N][M];
        list = new LinkedList<>();
        q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
                if(map[i][j] == 2){
                    list.add(new Node(i, j));
                }
            }
        }
        
        laboratory(0);

        System.out.println(max);
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
    }
    
}

//https://www.acmicpc.net/problem/14502