package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class 역량_헌터15172 {
    static int N, monCount, min;
    static int map[][];
    static boolean visited[][];
    static boolean[] monvisited, clientvisited;
    static Node[] monster, client;
    static Node hunter;
    static Queue<Node> q;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int[] test1, test2;

    public static int bfs(Node target, Node temphunter){

        q = new ArrayDeque<>();

        for(int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);

        q.add(new Node(temphunter.x, temphunter.y, 0));
        visited[temphunter.x][temphunter.y] = true;
        
        if(target.x == temphunter.x && target.y == temphunter.y)
            return 0;

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])  continue;

                if(nx == target.x && ny == target.y){
                    temphunter.x = nx;
                    temphunter.y = ny;
                    return now.n+1;
                }

                q.add(new Node(nx, ny, now.n+1));
                visited[nx][ny] = true;
                    
                

                
            }


        }

        return 0;
    }

    
    public static void permu(int depth, int sum, boolean flag, Node temphunter){
        if(min <= sum)
            return;

        if(depth == monCount*2){    
            min = Math.min(sum, min);
            // System.out.println(Arrays.toString(test1));
            // System.out.println(Arrays.toString(test2));
            // System.out.println();
            
            return;
        }
        int x = temphunter.x;
        int y = temphunter.y;

        
        
        //몬스터에게 가는 경우
        
        if(flag){
            for(int i = 1; i <= monCount; i++){
                if(monvisited[i])    continue;
                

                monvisited[i] = true;
                test1[depth] = i;
                
                //true일 때는 몬스터 잡으러 false일 때는 고객 잡으러
                temphunter = new Node(x, y);
                permu(depth+1, sum + bfs(monster[i], temphunter), true, temphunter);
                temphunter = new Node(x, y);
                permu(depth+1, sum + bfs(monster[i], temphunter), false, temphunter);
                //고객에게 갈 수도 있고, 추가로 몬스터 잡으러 갈 수도 있음
                
                test1[depth] = 0;  
                monvisited[i] = false;
                
            }
        }
        //고객에게 가는 경우
        if(!flag){
            for(int j = 1; j <= monCount; j++){   
                //해당 몬스터를 잡아야 해당 고객에게 갈 수 있음             
                //해당 고객을 이미 방문 했으면 갈 필요가 없음
                if(!monvisited[j] || clientvisited[j]) continue;
                clientvisited[j] = true;  
                test2[depth] = j*-1;              
                temphunter = new Node(x, y);
                permu(depth+1, sum + bfs(client[j], temphunter), true, temphunter);
                temphunter = new Node(x, y);
                permu(depth+1, sum + bfs(client[j], temphunter), false, temphunter);
                test2[depth] = 0;
                clientvisited[j] = false;


            }  
        }

        return;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++) {
            monCount = 0;
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visited = new boolean[N][N];
            q = new ArrayDeque<>();
            monvisited = new boolean[5];
            clientvisited = new boolean[5];
            monster = new Node[5];
            client = new Node[5];
            hunter = new Node(0, 0);
            test1 = new int[5*2];
            test2 = new int[5*2];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    monCount = Math.max(map[i][j], monCount);
                    if(map[i][j] > 0){
                        monster[map[i][j]] = new Node(i, j);
                    }
                    else if(map[i][j] < 0){
                        client[map[i][j]*-1] = new Node(i, j);

                    }
                }
            }

            permu(0, 0, true, hunter);

            System.out.println("#" + test_case + " " + min);

            



        }
    }

    static class Node{
        int x, y, n;
        public Node(int x, int y, int n){
            this.x = x;
            this.y = y;
            this.n = n;
        }
        public Node(int x, int y){
            this.x = x;
            this.y = y;           
        }
    }
}
//시간초과 26개
// 5
// 8
// 3 0 0 0 -2 0 0 0
// 0 0 0 0 -4 0 0 0
// 0 0 0 0 0 0 0 0
// 0 0 -1 0 0 0 0 0
// 0 -3 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0
// 0 0 2 4 0 1 0 0
// 0 0 0 0 0 0 0 0
// 3
// 0 0 0
// 0 1 -1
// 0 0 0
// 4
// -3 -1 1 0
// -2 0 0 3
// 0 0 0 0
// 0 0 2 0
// 5
// 0 0 -3 0 0
// 0 0 0 3 0
// 0 0 0 0 2
// 0 0 1 0 0
// -1 0 0 -2 0
// 6
// -1 0 0 0 0 -4
// 0 0 0 0 2 0
// -3 -2 0 4 0 0
// 3 0 0 0 0 1
// 0 0 0 0 0 0
// 0 0 0 0 0 0
