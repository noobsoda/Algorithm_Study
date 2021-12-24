import java.io.*;
import java.util.*;

public class G5톱니바퀴14891 {
    static int K, d[]; 
    static int dx[] = {-1, 1};
    static int map[][];
    static Queue<Node> q;
    
    //회전 회오리!
    public static void rotation(){
        for(int n = 0; n < 4; n++){
            if(d[n] == 1){
                int temp = map[n][7];
                for(int i = 6; i >= 0; i--){
                    map[n][i+1] = map[n][i];
                }
                map[n][0] = temp;
            }
            else if(d[n] == -1){
                int temp = map[n][0];
                for(int i = 1; i < 8; i++){
                    map[n][i-1] = map[n][i];
                }   
                map[n][7] = temp;
                
            }
        }
    }

    public static void Gear(){
        while(!q.isEmpty()){
            Node now = q.poll();
            d[now.n-1] = now.d;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    for(int k = 0; k < 2; k++){
                        if(d[j] == -1 || d[j] == 1){
                            int nx = j + dx[k];
                            if(nx < 0 || nx >= 4 || d[nx] != 0 || d[j] == 0)   continue;
                            //여기 수정
                            if(k == 0){
                                if(map[nx][2] == map[j][6])
                                    d[nx] = 0;
                                else
                                    d[nx] = d[j] * -1;
                            }
                            if(k == 1){
                                if(map[j][2] == map[nx][6])
                                    d[nx] = 0;
                                else
                                    d[nx] = d[j] * -1;
                            }
                        }
                    }
                }
            }
            rotation();
            Arrays.fill(d, 0);
            
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[4][8];
        q = new LinkedList<>();
        d = new int[4];
        String nv[];


        for(int i = 0; i < 4; i++){
            nv = br.readLine().split("");
            for(int j = 0; j < 8; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
        K = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < K; i++){
            nv = br.readLine().split(" ");

            int n = Integer.parseInt(nv[0]);
            int d = Integer.parseInt(nv[1]);
            q.add(new Node(n, d));
        }

        Gear();
        int res = 0;
        for(int i = 0; i < 4; i++){
            res += map[i][0] << i;
        }
        
        System.out.println(res);
           
        
        
    }    
    static class Node{
        int n;
        int d;
        public Node(int n, int d){
            this.n = n;
            this.d = d;
        }
    }
}
//https://www.acmicpc.net/problem/14891