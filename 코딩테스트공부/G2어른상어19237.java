package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G2어른상어19237 {
    static int N, M, K;
    static int map[][];
    static int smellmap[][];
    static int startd[];
    static int shark[][][];
    static List<Node> list;
    static int dx[] = {0, -1, 1, 0, 0};
    static int dy[] = {0, 0, 0, -1, 1};
    public static void Print(){
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();

            System.out.println(now.x + " " + now.y + " " + now.n + " " + now.d);
        }

        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
    public static void smelldown(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(smellmap[i][j] > 1)
                    smellmap[i][j]--;                    
                else if(smellmap[i][j] == 1){
                    smellmap[i][j] = 0;
                    map[i][j] = 0;
                }
            }
        }
    }
    public static void moveshark(){
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();

            boolean flag = false;   
            //인접한 칸에 냄새가 비어있을시         
            for(int i = 1; i <= 4; i++){
                int nx = now.x + dx[shark[now.n][now.d][i]];
                int ny = now.y + dy[shark[now.n][now.d][i]];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)  continue;   
                if(smellmap[nx][ny] != 0) continue;

                //상어 이동
                now.x = nx;
                now.y = ny;
                now.d = shark[now.n][now.d][i];
                
                flag = true;
                break;
            }

            //주변에 냄새가 꽉 찼을 때 자기 냄새나는 쪽으로
            if(!flag){
                for(int i = 1; i <= 4; i++){
                    int nx = now.x + dx[shark[now.n][now.d][i]];
                    int ny = now.y + dy[shark[now.n][now.d][i]];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)  continue;  
                    if(map[nx][ny] != now.n) continue;

                    now.x = nx;
                    now.y = ny;
                    now.d = shark[now.n][now.d][i];
                                    
                    break;                    

                }
            }
        }
        smelldown();
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();

            //자기가 더 큰 상어면 삭제
            if(map[now.x][now.y] != 0 && map[now.x][now.y] < now.n){
                it.remove();
                continue;
            }  
            
            map[now.x][now.y] = now.n;
            smellmap[now.x][now.y] = K;

        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        smellmap = new int[N][N];
        startd = new int[M];
        shark = new int[M+1][5][5];
        list = new LinkedList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    list.add(new Node(i, j, map[i][j], 0));
                    smellmap[i][j] = K;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            startd[i] = Integer.parseInt(st.nextToken());
        }
        Collections.sort(list);

        //방향 부여받기

        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();

            now.d = startd[now.n-1];
        }

        //우선순위 부여
        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= 4; j++){
                st = new StringTokenizer(br.readLine());  
                for(int k = 1; k <= 4; k++){
                    shark[i][j][k] = Integer.parseInt(st.nextToken());
                }              
            }
        }



        int result = 0;
        while(list.size() > 1){
            //샤크무브
            moveshark();
            result++;

            //Print();
            if(result > 1000)
                break;
        }


        System.out.println((result > 1000) ? -1 : result);
    }    

    static class Node implements Comparable<Node>{
        int x, y, n, d;
        public Node(int x, int y, int n, int d){
            this.x = x;
            this.y = y;
            this.n = n;
            this.d = d;
        }
        @Override
        public int compareTo(Node o){
            return n - o.n;
        }
    }
}
//https://www.acmicpc.net/problem/19237