package 코딩테스트공부;
import java.io.*;
import java.util.*;
public class G4성곽2234 {
    static int N, M, max = 0, summax = 0;
    static int mx, my;
    static int map[][];
    static int tempmap[][];
    static Queue<Node> q;    
    static boolean visited[][];
    static int c[];
    static int d[] = {8, 4, 2, 1};
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};

    //2개의 방 연결시켜서 가장 큰 방 찾기
    public static void link(int x, int y, int color){
        q.add(new Node(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny])    continue;
                
                if(map[nx][ny] != color){
                    summax = Math.max(c[color] + c[map[nx][ny]], summax);
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny));                

            }
        }
    }
    //방을 색으로 칠하고 큰 방 찾기
    public static void bfs(int x, int y, int color){
        int cnt = 1;
        q.add(new Node(x, y));
        visited[x][y] = true;
        map[x][y] = color;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(tempmap[now.x][now.y] - d[i] >= 0){
                    tempmap[now.x][now.y] -= d[i];
                    continue;
                }
                if(visited[nx][ny])    continue;

                visited[nx][ny] = true;
                map[nx][ny] = color;
                q.add(new Node(nx, ny));
                cnt++;                
            }
        }
        c[color] = cnt;
        max = Math.max(cnt, max);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tempmap = new int[N][M];
        visited = new boolean[N][M];
        c = new int[N*M];
        q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());                
            }
            System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j])   continue;

                bfs(i, j, cnt++);                
            }
        }
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j])   continue;

                link(i, j, map[i][j]);                
            }
        }
        

        System.out.println(cnt + "\n" + max + "\n" + summax);

    }
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
//https://www.acmicpc.net/problem/2234