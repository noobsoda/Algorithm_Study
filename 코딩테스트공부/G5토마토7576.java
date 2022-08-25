import java.io.*;
import java.util.*;

public class G5토마토7576 {
    static int N, M;
    static int map[][];
    static boolean visited[][];
    static Queue<Node> q;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static int bfs(){
        int cnt = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            cnt = now.t;

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == -1)  continue;

                map[nx][ny] = 1;
                q.add(new Node(nx, ny, now.t+1));
                visited[nx][ny] = true;

                

            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0)
                    return -1;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        q = new ArrayDeque<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    q.add(new Node(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int res = bfs();
        System.out.println(res);


    }
    static class Node{
        int x, y, t;        
        public Node(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;            
        }       
    }
}
//https://www.acmicpc.net/problem/7576