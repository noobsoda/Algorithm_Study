import java.io.*;
import java.util.*;

public class G1마상어와복제23290 {
    static int M, S, sharkx, sharky, sharkmax;
    static List<Node> list;
    static List<Node> copylist;
    static int map[][];
    static int visited[][];
    static Queue<Node> q;
    static int dx[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int dy[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int sx[] = {0, -1, 0, 1, 0};
    static int sy[] = {0, 0, -1, 0, 1};

    // public static void print(){
    //     for(int i = 1; i <= 4; i++){
    //         System.out.println(Arrays.toString(map[i]));
    //     }
    //     System.out.println();
    // }
    //상어가 움직이면서 물고기를 먹으며 냄새를 남긴다.
    public static void sharkmove(){
        //상어 최종위치
        Node now = q.poll();
        sharkx = now.x;
        sharky = now.y;
        visited[sharkx][sharky] = 1;
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node fish = it.next();
            if(sharkx == fish.x && sharky == fish.y){
                visited[fish.x][fish.y] = 2;
                it.remove();
                map[fish.x][fish.y]--;
            }
        }
        //상어 2번째 위치, 상어 1번째 위치
        while(!q.isEmpty()){
            now = q.poll();
            for(Iterator<Node> it = list.iterator(); it.hasNext();){
                Node fish = it.next();
                if(now.x == fish.x && now.y == fish.y){
                    visited[fish.x][fish.y] = 2;
                    it.remove();
                    map[fish.x][fish.y]--;
                }
            }
        }
        return;
    }
    public static void fishmove(){

        for(int i = 1; i <= 4; i++){
            Arrays.fill(map[i], 0);
        }
        //물고기 이동
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();

            int nx = now.x + dx[now.d];
            int ny = now.y + dy[now.d];

            for(int i = 0; i < 9; i++){
                if(i == 8){
                    nx = now.x;
                    ny = now.y;
                    continue;
                }
                if(nx < 1 || ny < 1 || nx > 4 || ny > 4 || visited[nx][ny] != 0) {
                    now.d--;
                    if(now.d < 1) now.d += 8;
                    nx = now.x + dx[now.d];
                    ny = now.y + dy[now.d];
                }
                else{
                    break;
                }
            }
            now.x = nx;
            now.y = ny;
            map[nx][ny]++;
        }
    }
    public static boolean shark(int x, int y, int depth, int max){
        
        if(depth == 3){
            if(sharkmax == max){                
                return true;
            }
        }
        else{
            for(int i = 1; i <= 4; i++){
                int nx = x + sx[i];
                int ny = y + sy[i];
                
                if(nx < 1 || ny < 1 || nx > 4 || ny > 4) continue;

                //상어가 물고기 있는쪽으로 가면 0으로 바꿔준다
                int temp = map[nx][ny];
                map[nx][ny] = 0;
                if(shark(nx, ny, depth+1, max+temp)){
                    q.add(new Node(nx, ny, 0));
                    map[nx][ny] = temp;
                    return true;
                }
                map[nx][ny] = temp;
            }
        }
        return false;
    }
    public static void maxshark(int x, int y, int depth, int max){
        //중복을 허용해야 함 우 좌 우 < 우 우 우 경우일 때 못찾음      
      
        if(depth == 3){
            if(sharkmax < max){
                sharkmax = max;
                return;
            }
        }
        else{
            for(int i = 1; i <= 4; i++){
                int nx = x + sx[i];
                int ny = y + sy[i];                
                
                if(nx < 1 || ny < 1 || nx > 4 || ny > 4) continue;

                int temp = map[nx][ny];
                map[nx][ny] = 0;
                maxshark(nx, ny, depth+1, max+temp);    
                map[nx][ny] = temp;
            }
        }
        return;
    }
    public static void copymagic(){
        //복제마법 시전
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();
            copylist.add(new Node(now.x, now.y, now.d));
        }

        
        //물고기 이동
        fishmove();
        // print();

        //2턴 전 물고기 냄새 제거
        for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= 4; j++){
                if(visited[i][j] > 0)
                    visited[i][j]--;
            }
        }
        sharkmax = 0;

        //물고기 가장 많은 max값 찾기
        maxshark(sharkx, sharky, 0, 0);
        //사전순으로 순회하여 q로 이동할 위치 저장
        shark(sharkx, sharky, 0, 0);

        //q에 저장된 위치로 상어가 움직인다.
        sharkmove();

        

        //복제마법 발동
        list.addAll(0, copylist);
        for(Iterator<Node> it = copylist.iterator(); it.hasNext();){
            Node now = it.next();
            map[now.x][now.y]++;
        }
        copylist.clear();

        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        M = Integer.parseInt(nv[0]);
        S = Integer.parseInt(nv[1]);
        map = new int[5][5];
        visited = new int[5][5];
        list = new LinkedList<>();
        copylist = new LinkedList<>();
        q = new LinkedList<>();

        for(int i = 0; i < M; i++){
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int y = Integer.parseInt(nv[1]);
            int z = Integer.parseInt(nv[2]);

            list.add(new Node(x, y, z));
        }

        nv = br.readLine().split(" ");
        sharkx = Integer.parseInt(nv[0]);
        sharky = Integer.parseInt(nv[1]);
        visited[sharkx][sharky] = 1;
        
        for(int i = 0; i < S; i++){
            copymagic();
            // print();
        }
        System.out.println(list.size());

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
// https://www.acmicpc.net/problem/23290
// 중복 허용의 대해서 생각을 못해서 오래 걸렸음