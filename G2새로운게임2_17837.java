import java.io.*;
import java.util.*;

public class G2새로운게임2_17837 {
    static int N, K;
    static int dx[] = {0, 0, 0, -1, 1};
    static int dy[] = {0, 1, -1, 0, 0};
    static int map[][];
    static int visitmap[][];
    static List<Node> list;    
    public static void withmove(int x, int y, int d, int floor, int nfloor){
        Node node;
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            node = it.next();
            if(node.x != x || node.y != y || node.floor <= floor)  continue;

            int nx = node.x + dx[d];
            int ny = node.y + dy[d];

            visitmap[node.x][node.y]--;
            visitmap[nx][ny]++;
            node.x = nx;
            node.y = ny;
            node.floor += nfloor;
        }
    }
    public static void reversemove(int x, int y, int nfloor, int reversefloor){
        Node node;
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            node = it.next();
            if(node.x != x || node.y != y || node.floor <= nfloor)  continue;
            for(int i = 0; i < reversefloor; i++){
                if(node.floor == i + nfloor+1){
                    node.floor = visitmap[x][y] - i;
                    break;
                }
            }
            
        }
    }

    public static boolean newgame(){
        Node node;
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            node = it.next();

            int nx = node.x + dx[node.d];
            int ny = node.y + dy[node.d];
            //위에 벽에 도달했을 시
            if(nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] == 2){
                if(node.d == 1)    node.d = 2;
                else if(node.d == 2)    node.d = 1;                
                else if(node.d == 3)    node.d = 4;                
                else if(node.d == 4)    node.d = 3;
                
                nx = node.x + dx[node.d];
                ny = node.y + dy[node.d];

                if(nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] == 2) continue;
                //반대쪽 벽도 못가거나 파란벽이면 컨티뉴
                //반대쪽으로 바라보게 하고 이동시키기
            }            
            
            visitmap[node.x][node.y]--;
            //현재 쌓여있는 층인 nfloor와 초기층 할당하기 위한 nvisit
            int nfloor = visitmap[nx][ny];
            int nvisit = ++visitmap[nx][ny];

            withmove(node.x, node.y, node.d, node.floor, nfloor);

            node.floor = nvisit;          
                   
            node.x = nx;
            node.y = ny;
            if(map[nx][ny] == 1){
                int reversefloor = visitmap[nx][ny] - nfloor;
                if(reversefloor == 1)   continue;
                reversemove(nx, ny, nfloor, reversefloor);
            }

            if(visitmap[nx][ny] >= 4){                    
                return true;
            }
                
            //이동시켰는데 만약에 빨간벽이면 floor 반전시키기            
        }      

        return false;
        
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        K = Integer.parseInt(nv[1]);

        list = new LinkedList<>();

        map = new int[N+1][N+1];
        visitmap = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            nv = br.readLine().split(" ");
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(nv[j-1]);
            }
        }
        for(int i = 0; i < K; i++){
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int y = Integer.parseInt(nv[1]);
            int d = Integer.parseInt(nv[2]);
            list.add(new Node(x, y, d, 1));
            visitmap[x][y]++;
        }

        for(int i = 1; i <= 1001; i++){
            if(newgame()){
                System.out.println(i);
                break;
            }            
            
            if(i == 1001)
                System.out.println(-1);
        }
        


        
    }
    static class Node{
        int x, y, d, floor;
        public Node(int x, int y, int d, int floor){
            this.x = x;
            this.y = y;
            this.d = d;
            this.floor = floor;
        }
    }
    
}

//https://www.acmicpc.net/problem/17837