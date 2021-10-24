import java.io.*;
import java.util.*;
public class G5뱀3190 {
    static int N, K, L, time = 0;
    //오른쪽 아래 왼쪽 위 순서
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};
    static ArrayList<Node> apple;
    static Queue<direction> q;
    static Queue<Node> nq;
    static int map[][];
    //게임 클리어가 아니라 몇초에 뱀이 죽는지 확인 

    
    public static int snake(){
        nq = new LinkedList<>();
        int a = 0;
        int nx = 1, ny = 1;
        nq.add(new Node(nx, ny));

        while(!q.isEmpty()){
            direction d = q.poll();
            int t = d.t - time;
            for(int i = 0; i < t; i++){                              

                nx += dx[a];
                ny += dy[a];
                if(nx < 1 || ny < 1 || nx > N || ny > N) return time+1;

                // 1 3 1 4
                // 자기 몸이랑 부딪혔을 때
                if(nq.contains(new Node(nx, ny))) return time+1;
                nq.add(new Node(nx, ny));
                

                //사과를 먹었을 경우 사과 삭제
                //안 먹었을 경우 꼬리 이동 X
                if(apple.contains(new Node(nx, ny))){
                    apple.remove(new Node(nx, ny));
                }
                else    nq.poll();
                time++;

            }
            //방향꺽기
            if(d.d.equals("D")){
                a++;
                if(a > 3) a -= 4;
                
            }
            else if(d.d.equals("L")){
                a--;
                if(a < 0) a += 4;
            }
            
            
            

        }        
        
        return time;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nv;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        K = Integer.parseInt(br.readLine());
        apple = new ArrayList<>();        
        

        for(int i = 0; i < K; i++){
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int y = Integer.parseInt(nv[1]);
            apple.add(new Node(x, y));
        }

        L = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        for(int i = 0; i < L; i++){
            nv = br.readLine().split(" ");
            int t = Integer.parseInt(nv[0]);            
            q.add(new direction(t, nv[1]));
        }
        q.add(new direction(10000, null));
        
        System.out.println(snake());


    }
    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object object){
            Node node = (Node)object;

            if(node.x == this.x && node.y == this.y)
                return true;
            return false;
        }
    }
    static class direction{
        int t;
        String d;
        public direction(int t, String d){
            this.t = t;
            this.d = d;
        }
    }
    
}

//https://www.acmicpc.net/problem/3190