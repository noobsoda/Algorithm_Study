import java.io.*;
import java.util.*;
public class G3주사위굴리기2_23288 {
    static int map[][];
    static boolean visited[][];
    static Queue<Node> q;
    static int N, M, K, nA, nB, nC, sum = 0;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static int U, D, F, B, L, R;
    static int direction = 1;
    public static void dice(){

        if(direction == 0){
            //B -> U -> F -> D
            int temp = B;
            B = U;
            U = F;
            F = D;
            D = temp;            
        }
        else if(direction == 1){
            //L -> U -> R -> D
            int temp = L;
            L = D;
            D = R;
            R = U;
            U = temp;
        }
        else if(direction == 2){
            //D -> F -> U -> B
            int temp = D;
            D = F;
            F = U;
            U = B;
            B = temp;
        }
        else if(direction == 3){
            int temp = D;
            //D -> R -> U -> L
            D = L;
            L = U;
            U = R;
            R = temp;
        }
    }

    public static void dicegame(int x, int y){
        for(int i = 0; i < K; i++){
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            //벽에 도달했을시 반대쪽 방향으로 턴
            if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                direction += 2;
                direction %= 4;

                nx = x + dx[direction];
                ny = y + dy[direction];
            }
            //x와 y이동
            x = nx;    
            y = ny;
            dice();

            //주사위 아래값
            nA = D;
            nB = map[nx][ny];            
            nC = bfs(nx, ny, map[nx][ny]);

            sum += nC * nB;

            //시계회전
            if(nA > nB){
                direction += 1;
                direction %= 4;                
            }
            //반시계회전
            else if(nA < nB){
                direction -= 1;
                if(direction < 0)   direction += 4;
            }
            //같으면 그대로
            
                
        }
    }
    public static int bfs(int x, int y, int v){
        q.add(new Node(x, y));
        visited[x][y] = true;
        int count = 1;
        while(!q.isEmpty()){
            Node now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(!visited[nx][ny] && map[nx][ny] == v){
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;        
                    count++;            
                }

            }
        }
        //방문요소 초기화
        for(int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);
        
        return count;

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        K = Integer.parseInt(nv[2]);
        map = new int[N][M];
        visited = new boolean[N][M];
        q = new LinkedList<>();
        //주사위 초기값 할당
        U = 1;    D = 6;    F = 5; 
        B = 2;    L = 4;    R = 3; 

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
       
        dicegame(0, 0);

        System.out.println(sum);




    }
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;            
        }
    }
    
}
//https://www.acmicpc.net/problem/23288