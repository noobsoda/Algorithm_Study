import java.io.*;
import java.util.*;

public class G1온풍기안녕23289 {
    static int R, C, K, W, choco = 0;
    static int map[][], tempmap[][];
    static boolean wallmap[][][];
    static boolean visited[][];
    static List<Node> list;
    static List<Node> klist;
    static Queue<OnNode> q;
    //오른쪽 왼쪽 위쪽 아래쪽

    static int dsx[][] = {{0}, {-1, 0, 1}, {-1, 0, 1}, {-1, -1, -1}, {1, 1, 1}, };
    static int dsy[][] = {{0}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {-1, 0, 1}};
    static int dx[] = {0, 0, 0, -1, 1};
    static int dy[] = {0, 1, -1, 0, 0};

    public static void tempdiv(int x, int y){
        for(int i = 1; i <= 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 1 || ny < 1 || nx > R || ny > C || map[x][y] <= map[nx][ny])
                continue;
            if(!wallcheck(nx, ny, i, 1)) continue;
            int diff = (map[x][y] - map[nx][ny]) / 4;
            tempmap[nx][ny] += diff;
            tempmap[x][y] -= diff;
            
        }
    }
    
    public static void blow(){
        while(!q.isEmpty()){
            //중복 제거하기 위해 visited

            OnNode now = q.poll();           
            map[now.x][now.y] += now.count;
            //한 원소마다 3개씩
            for(int i = 0; i < 3; i++){
                int nx = now.x + dsx[now.d][i];
                int ny = now.y + dsy[now.d][i];
                
                //경로 밖으로 나가거나 1이 되면 컨티뉴
                if(nx < 1 || ny < 1 || nx > R || ny > C || now.count <= 1)
                    continue;
                if(wallcheck(nx, ny, now.d, i) && !visited[nx][ny]){
                    q.add(new OnNode(nx, ny, now.d, now.count-1));
                    visited[nx][ny] = true;
                }
            }
        }
    }
    public static boolean wallcheck(int x, int y, int d, int near){
        if(d == 1){// 오른쪽 방향에서 위 가운데 아래 순서
            if(near == 0){
                if(wallmap[x][y-1][1] == true || wallmap[x+1][y-1][0] == true)
                    return false;
            }
            else if(near == 1){
                if(wallmap[x][y-1][1] == true)
                    return false;
            }
            else{
                if(wallmap[x][y-1][1] == true || wallmap[x][y-1][0] == true)
                    return false;
            }
        }
        else if(d == 2){
            if(near == 0){
                if(wallmap[x][y][1] == true || wallmap[x+1][y+1][0] == true)
                    return false;
            }
            else if(near == 1){
                if(wallmap[x][y][1] == true)
                    return false;
            }
            else{
                if(wallmap[x][y][1] == true || wallmap[x][y+1][0] == true)
                    return false;
            }
        }
        //위 아래에서 왼쪽 가운데 오른쪽
        else if(d == 3){
            if(near == 0){
                if(wallmap[x+1][y][1] == true || wallmap[x+1][y][0] == true)
                    return false;
            }
            else if(near == 1){
                if(wallmap[x+1][y][0] == true)
                    return false;
            }
            else{
                if(wallmap[x+1][y-1][1] == true || wallmap[x+1][y][0] == true)
                    return false;
            }
        }
        else{
            if(near == 0){
                if(wallmap[x-1][y][1] == true || wallmap[x][y][0] == true)
                    return false;
            }
            else if(near == 1){
                if(wallmap[x][y][0] == true)
                    return false;
            }
            else{
                if(wallmap[x-1][y-1][1] == true || wallmap[x][y][0] == true)
                    return false;
            }
        }

        return true;
    }
    public static boolean hotblower(){
        //바람이 분다
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();
            int d = now.t;
            int nx = now.x + dx[d];
            int ny = now.y + dy[d];

            q.add(new OnNode(nx, ny, d, 5));
            blow();
                
            for(int i = 1; i <= R; i++){
                Arrays.fill(visited[i], false);
            }
        }
        //온도가 조절된다
        
        for(int i = 1; i <= R; i++){
            System.arraycopy(map[i], 0, tempmap[i], 0, map[i].length);
        }

        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                tempdiv(i, j);
            }
        }
        //조절한 온도 복사
        for(int i = 1; i <= R; i++){
            System.arraycopy(tempmap[i], 0, map[i], 0, map[i].length);
        }
        
        //온도가 1이상인 바깥 온도가 1감소한다

        for(int i = 1; i <= R; i++){
            if(map[i][1] >= 1)
                map[i][1]--;
            if(map[i][C] >= 1)
                map[i][C]--;
        }
        for(int i = 2; i <= C-1; i++){
            if(map[1][i] >= 1)
                map[1][i]--;
            if(map[R][i] >= 1)
                map[R][i]--;
        }

        //초콜릿이 맛있다.
        choco++;

        //조사하는칸 온도를 확인한다.
        for(Iterator<Node> it = klist.iterator(); it.hasNext();){
            Node now = it.next();
            if(map[now.x][now.y] < K){
                return false;
            }
        }
        return true;
        
        
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        R = Integer.parseInt(nv[0]);
        C = Integer.parseInt(nv[1]);
        K = Integer.parseInt(nv[2]);
        
        map = new int[R+1][C+1];
        tempmap = new int[R+1][C+1];
        wallmap = new boolean[R+1][C+1][2];
        visited = new boolean[R+1][C+1];
        list = new LinkedList<>();
        klist = new LinkedList<>();
        q = new LinkedList<>();

        for(int i = 1; i <= R; i++){
            nv = br.readLine().split(" ");
            for(int j = 1; j <= C; j++){
                map[i][j] = Integer.parseInt(nv[j-1]);
                if(map[i][j] != 0){
                    if(map[i][j] == 5){
                        klist.add(new Node(i, j, map[i][j]));
                        map[i][j] = 0;
                        continue;
                    }
                    list.add(new Node(i, j, map[i][j]));
                    map[i][j] = 0;
                }
            }
        }

        W = Integer.parseInt(br.readLine());

        for(int i = 0; i < W; i++){
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int y = Integer.parseInt(nv[1]);
            int t = Integer.parseInt(nv[2]);
            wallmap[x][y][t] = true;
        }
        for(int i = 0; i < 100; i++){
            if(hotblower())
                break;
            
            if(i == 99)
                choco++;
        }
        System.out.println(choco);
        

    }
    static class Node{
        int x, y, t;
        public Node(int x, int y, int t){
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    static class OnNode{
        int x, y, d, count;
        public OnNode(int x, int y, int d, int count){
            this.x = x;
            this.y = y;
            this.d = d;
            this.count = count;
        }
    }
     
}
//https://www.acmicpc.net/problem/23289