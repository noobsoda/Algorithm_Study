import java.io.*;
import java.util.*;

public class G2상어중학교21609 {
    static int N, M, score = 0;
    static int map[][], tempmap[][];
    static boolean visited[][];
    static Queue<Node> q, blockq;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static void print(){
        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    //중력
    public static void gravity(){
        for(int i = 0; i < N-1; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k <= i; k++){
                    if(map[N-2-i+k][j] == -1 || map[N-2-i+k][j] == -2)
                        continue;
                    if(map[N-1-i+k][j] == -2){
                        map[N-1-i+k][j] = map[N-2-i+k][j];
                        map[N-2-i+k][j] = -2;
                    }
                }
            }            
        }
    }
    //블럭제거
    public static void blockremove(int x, int y){
        
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], false);
        }

        q.add(new Node(x, y, 0, 0));
        visited[x][y] = true;

        int value = map[x][y];        
        map[x][y] = -2;

        while(!q.isEmpty()){
            Node now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == -1 )  continue;

                if((map[nx][ny] == value || map[nx][ny] == 0) && !visited[nx][ny]){
                    q.add(new Node(nx, ny, 0, 0));
                    visited[nx][ny] = true;
                    map[nx][ny] = -2;
                }
            }
        }
        

    }
    //블럭찾기
    public static void blockexplore(int x, int y){
        int max = 1, rainbow = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 0)
                    visited[i][j] = false;
            }
        }
        q.add(new Node(x, y, 0, 0));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == -1 )  continue;

                //블럭 색깔이 같을때
                if(map[nx][ny] == map[x][y] && !visited[nx][ny]){
                    q.add(new Node(nx, ny, 0, 0));
                    visited[nx][ny] = true;
                    max++;

                    //기준 블록 설정
                    //행 작은게 우선
                    if(x > nx){
                        x = nx;
                        y = ny;
                    }
                    //행 같은게 여러개 있을시 열 작은게 우선
                    else if(x == nx){
                        if(y > ny){
                            x = nx;
                            y = ny;
                        }
                    }
                }
                //무지개 블럭일 때
                if(map[nx][ny] == 0 && !visited[nx][ny]){
                    q.add(new Node(nx, ny, 0, 0));
                    visited[nx][ny] = true;
                    max++;
                    rainbow++;
                }

                
                    
                
            }
        }
        if(max >= 2)
            blockq.add(new Node(x, y, max, rainbow));

    }    

    //돌리고돌리고
    public static void rotate(int x, int y, int nL){        
        for(int i = 0; i < nL; i++){
            for(int j = 0; j < nL; j++){
                tempmap[x+nL-1-j][y+i] = map[x+i][y+j];
            }
        }        
    }

    public static boolean sharkmiddleschool(){
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], false);
        }
        int max = 0, nx = 0, ny = 0, rainbow = 0;

        //가장 큰 블럭 찾기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == -1 || map[i][j] == 0 || map[i][j] == -2)
                    continue;
                blockexplore(i, j);
                
            }
        }
        //크기 같은 블록 우선순위 설정
        while(!blockq.isEmpty()){
            Node now = blockq.poll();

            if(max < now.max){
                max = now.max;
                nx = now.x;
                ny = now.y;
                rainbow = now.rainbow;
            }
            else if(max == now.max){
                if(rainbow < now.rainbow){
                    max = now.max;
                    nx = now.x;
                    ny = now.y;
                    rainbow = now.rainbow;
                    
                }
                else if(rainbow == now.rainbow){
                    if(nx < now.x){
                        max = now.max;
                        nx = now.x;
                        ny = now.y;
                        rainbow = now.rainbow;
                    }
                    else if(nx == now.x){
                        if(ny < now.y){
                            max = now.max;
                            nx = now.x;
                            ny = now.y;
                            rainbow = now.rainbow;
                        }
                    }
                }
            }
            
        }
        //블록 개수가 2보다 크거나 같아야 한다
        if(max < 2)
            return false;
        //가장 큰 블럭 제거
        blockremove(nx, ny);
        //스코어 더하기
        score += max*max;

        //중력
        gravity();
        
        //돌리기
        for(int i = 0; i < N; i++){
            tempmap[i] = map[i].clone();
        }
        rotate(0, 0, N);

        for(int i = 0; i < N; i++){
            map[i] = tempmap[i].clone();
        }
        //중력
        gravity();

        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);

        map = new int[N][N];
        tempmap = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();
        blockq = new LinkedList<>();

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
        boolean flag = true;
        do{
        flag = sharkmiddleschool();
        }while(flag);

        System.out.println(score);


    }    
    static class Node{
        int x, y, max, rainbow;
        public Node(int x, int y, int max, int rainbow){
            this.x = x;
            this.y = y;
            this.max = max;
            this.rainbow = rainbow;
        }
    }
}

//https://www.acmicpc.net/problem/21609