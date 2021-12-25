import java.io.*;
import java.util.*;

public class G4아기상어16236 {
    static int N, sharkx, sharky, sharksize = 2, eatfished = 0;
    static int minx, miny;    
    static Queue<Node> q;
    static int map[][];
    static boolean visited[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int res = 0;
    //물고기 찾기
    public static int eatfish(){
        q.add(new Node(sharkx, sharky, 0));
        visited[sharkx][sharky] = true;
        int time = Integer.MAX_VALUE;
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], false);
        }
        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
    
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || time < now.t+1)  continue;
                
                if(map[nx][ny] <= sharksize && !visited[nx][ny]){                    
                    if(map[nx][ny] != 0 && map[nx][ny] < sharksize){
                        if(time >= now.t+1){
                            time = now.t+1;
                            
                            if(minx > nx){
                                minx = nx;
                                miny = ny;
                            }
                            else if(minx == nx){
                                if(miny > ny){
                                    minx = nx;
                                    miny = ny;
                                }
                            }
                        }
                    }
                    //0일때와 크기가 똑같거나 작을 때 add
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, now.t+1));
                }
            }
        }
        return time;
    }

    public static boolean babyshark(){
        //가장 가까이 있는 물고기 탐색
        int time = 0;
        time += eatfish();

        //물고기 찾을수 없다면 리턴
        if(time == Integer.MAX_VALUE){
            return false;
        }

        //물고기 먹기
        map[minx][miny] = 0;
        sharkx = minx;
        sharky = miny;
        res += time;
        
        //물고기 일정 이상 먹었으면 레벨 업
        eatfished++;
        if(eatfished == sharksize){
            eatfished = 0;
            sharksize++;
        }
        

        return true;


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();
        String nv[];
        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
                if(map[i][j] == 9){
                    sharkx = i;
                    sharky = j;
                    map[i][j] = 0;
                }
            }
        }
        while(babyshark());
        
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
//https://www.acmicpc.net/problem/16236