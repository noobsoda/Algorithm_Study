import java.io.*;
import java.util.*;

public class G3원판돌리기17822 {
    static int N, M, T;
    static int map[][];
    static int dn[] = {-1, 1};
    static Queue<Node> q = new LinkedList<>();

    public static void averaging(){
        int count = 0;
        double average = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0){
                    average += map[i][j];
                    count++;
                }
            }
        }
        average /= count;

        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0) continue;
                if(map[i][j] < average){
                    map[i][j]++;
                }
                else if(map[i][j] == average);
                 
                else{
                    map[i][j]--;
                }
            }
        }
        
        
    }
    public static boolean removecheck(){
        boolean check = false;
        //위 아래는 2~N-1만 하면 됨 <- N이 2일때 문제 해결해야 했음 
        //문제점 가로로 하고나면 세로가 안됨
        //가로 세로 겹쳤을 때 문제를 해결 해야 함

        boolean temp[][] = new boolean[N+1][M];
        //내부 인접 찾기
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < 2; k++){
                    int n = j+dn[k];
                    if(n < 0) n += M;
                    else if(n > M-1) n -= M;

                    if(map[i][j] == 0) continue;
                    if(map[i][j] == map[i][n]){
                        temp[i][j]= true;
                        temp[i][n]= true;
                    }
                }                              
            }         
        }
        //위 아래 인접 찾기
        for(int i = 0; i < M; i++){
            for(int j = 1; j < N; j++){
                for(int k = 0; k < 2; k++){
                    int n = j + dn[k];                    
                    if(map[j][i] == 0 || n < 0) continue;
                    if(map[j][i] == map[n][i]){
                        temp[j][i] = true;
                        temp[n][i] = true;
                    }
                }                
            }
            
        }
        //0으로 바뀐값 넣어주기
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                if(temp[i][j]){
                    map[i][j] = 0;
                    check = true;
                }
            }
        }
        
        if(check) return true;
        return false;
    }
    public static void rotation(){
        int result = 0;
        while(!q.isEmpty()){
            Node now = q.poll();
            //x의 배수 회전 
            for(int i = 1; i <= N/now.x; i++){
                int temp[] = new int[M];
                for(int j = 0; j < M; j++){
                    //시계 방향 
                    if(now.d == 0){
                        int n = j+now.k;
                        while(n > M-1) n -= M;   
                        temp[n] = map[now.x*i][j];
                        
                    }
                    //반시계 방향
                    else{
                        int n = j-now.k;
                        while(n < 0) n += M;
                        temp[n] = map[now.x*i][j];

                    }                    
                }
                //복사한거 값 넣기
                for(int j = 0; j < M; j++){
                    map[now.x*i][j] = temp[j];
                }
            }
            
            if(!removecheck()) averaging();

            
        }
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < M; j++){
                    result += map[i][j];
            }
        }
        System.out.println(result);
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        T = Integer.parseInt(nv[2]);
        

        map = new int[N+1][M];

        for(int i = 1; i <= N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
        for(int i = 0; i < T; i++){
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int d = Integer.parseInt(nv[1]);
            int k = Integer.parseInt(nv[2]);
            q.add(new Node(x, d, k));
        }
        rotation();

        
    }

    static class Node{
        int x;
        int d;
        int k;
        public Node(int x, int d, int k){
            this.x = x;
            this.d = d;
            this.k = k;
        }
    }
}
// 트랩 평균값이 같은 때의 경우
// N이 2일 경우

//https://www.acmicpc.net/problem/17822
//gd