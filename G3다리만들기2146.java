import java.io.*;
import java.util.*;

public class G3다리만들기2146 {
    static int N, res = Integer.MAX_VALUE;;
    static int map[][]; 
    static Queue<Node> q;
    static Queue<Node> listq;
    static boolean visited[][]; 
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    //섬 가르기
    public static void division(int x, int y, int color){
        visited[x][y] = true;
        map[x][y] = color;
        q.add(new Node(x, y, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            boolean check = false;
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

                if(map[nx][ny] == 0){
                    check = true;
                }

                if(map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    map[nx][ny] = color;
                    q.add(new Node(nx, ny, 0));
                }
            }
            if(check){
                listq.add(new Node(now.x, now.y, 0));
            }
        }
    }

    public static void least(int x, int y){        
        int color = map[x][y];        
        q.add(new Node(x, y, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == color) continue;
                
                if(map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, now.d+1));
                }
                else{
                    res = Math.min(res, now.d);
                }
            }
        }        
    }
    public static void bridge(){
        int color = 1;
        //다른 지역 표시
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    division(i, j, color);
                    color++;
                }
            }
        }

        while(!listq.isEmpty()){
            for(int i = 0; i < N; i++){
                Arrays.fill(visited[i], false);
            }

            Node list = listq.poll();
            least(list.x, list.y);
        }


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();
        listq = new LinkedList<>();
        String nv[];


        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }

        bridge();
        System.out.println(res);


    }
    static class Node{
        int x, y, d;
        public Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }    
}
