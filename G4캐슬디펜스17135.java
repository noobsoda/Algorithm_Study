import java.io.*;
import java.util.*;

public class G4캐슬디펜스17135 {
    static int N, M, D, max, enemyremove, res = 0;
    static int map[][];
    static int tempmap[][];
    static boolean visited[];
    static boolean bfsvisited[][];
    static Queue<Node> q;
    static Queue<Node> targetq;

    static int dx[] = {0, -1, 0};
    static int dy[] = {-1, 0, 1};

    //적 내려오기
    public static void push(){
        //한칸씩 땅기기
        for(int i = N-2; i >= 0; i--){
            for(int j = 0; j < M; j++){
                tempmap[i+1][j] = tempmap[i][j];
            }
        }
        //맨 위 부분 0
        for(int i = 0; i < M; i++){
            tempmap[0][i] = 0;
        }
    }
    
    public static boolean Archercheck(){
        //타겟 탐색
        for(int i = 0; i < M; i++){
            if(visited[i]){
                bfsvisited = new boolean[N+1][M];
                q.add(new Node(N, i, 0));
                bfsvisited[N][i] = true;

                while(!q.isEmpty()){
                    Node now = q.poll();
        
                    for(int j = 0; j < 3; j++){
                        int nx = now.x + dx[j];
                        int ny = now.y + dy[j];
        
                        if(nx < 0 || ny < 0 || nx >= N || ny >= M || now.d >= D) continue;
                        
                        if(tempmap[nx][ny] == 1){
                            targetq.add(new Node(nx, ny, 0));
                            q.clear();
                            break;
                        }
                        //사거리 D이하만 들어올수 있음
                        if(!bfsvisited[nx][ny] && now.d < D-1){
                            bfsvisited[nx][ny] = true;
                            q.add(new Node(nx, ny, now.d+1));
                        }
                        
                    }
                }
            }
            
        }

        //동시에 화살 쏘기 같은적을 쏠수도 있음
        while(!targetq.isEmpty()){
            Node target = targetq.poll();
            if(tempmap[target.x][target.y] == 1){
                tempmap[target.x][target.y] = 0;
                enemyremove++;
            }
        }

        //적 내리기
        push();

        //적 남아있으면 다시 재반복        
        boolean flag = false;
        for(int i = N-1; i >= 0; i--){            
            for(int j = 0; j < M; j++){
                if(tempmap[i][j] == 1){
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
            
        }
        
        

        if(flag){
            Archercheck();
        }

        if(enemyremove == max)
            return true;
        else
            return false;
    }

    public static boolean castledefense(int start, int depth){
        if(depth == 3){
            enemyremove = 0;
            for(int i = 0; i < N+1; i++){
                System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);
            }
            //적 전부 제거했으면 max에 도달했으니 더 이상 할 필요 없음
            if(Archercheck()) return true;
            if(res < enemyremove){
                res = enemyremove;
            }
            //res = Math.max(res, enemyremove);
            return false;
        }
        

        for(int i = start; i < M; i++){
            visited[i] = true;
            if(castledefense(i+1, depth+1)) return true;
            visited[i] = false;
        }

        return false;
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N+1][M];
        tempmap = new int[N+1][M];
        visited = new boolean[M];
        q = new LinkedList<>();
        targetq = new LinkedList<>();

        max = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) max++;
            }
        }
        if(castledefense(0, 0)) res = enemyremove;        

        
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
//https://www.acmicpc.net/problem/17135