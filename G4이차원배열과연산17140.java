import java.io.*;
import java.util.*;
public class G4이차원배열과연산17140 {
    static int R, C, K, time;
    //nR 세로줄 nC 가로줄
    static int nC = 3, nR = 3;
    static int map[][];
    static int tempmap[];
    static PriorityQueue<Node> pq = new PriorityQueue<>();


    public static int sort(){
        while(!(map[R][C] == K)){
            int max;
            tempmap = new int[101];            
            
            if(time > 100){
                time = -1;
                return time;
            }
            if(nR >= nC){
                max = 0;
                //nR 세로줄 nC 가로줄
                for(int i = 1; i <= nR; i++){
                    //가로 탐색
                    for(int j = 1; j <= nC; j++){
                        tempmap[map[i][j]]++;
                    }
                    for(int j = 1; j <= nC; j++){
                        //그 다음 노드가 0일때와 노드가 가르키는 값이 0일때
                        if(tempmap[map[i][j]] == 0 || map[i][j] == 0){
                            map[i][j] = 0;
                            continue;
                        }
    
                        pq.add(new Node(map[i][j], tempmap[map[i][j]]));
                        tempmap[map[i][j]] = 0;
                        map[i][j] = 0;
                    }
                    int k = 0;
                    while(!pq.isEmpty()){
                        
                        Node now = pq.poll();
                        map[i][++k] = now.x;
                        map[i][++k] = now.y;
                        if(max < k)
                            max = k;
                        if(k == 100){
                            while(!pq.isEmpty())    pq.poll();
                            break;
                        }
                    }
                    
                }
                nC = max;
                time++;
            }
            else{
                max = 0;
                for(int i = 1; i <= nC; i++){
                    //세로 탐색
                    for(int j = 1; j <= nR; j++){
                        tempmap[map[j][i]]++;
                    }
                    for(int j = 1; j <= nR; j++){
                        if(tempmap[map[j][i]] == 0 || map[j][i] == 0){
                            map[j][i] = 0;
                            continue;
                        }
                        pq.add(new Node(map[j][i], tempmap[map[j][i]]));
                        tempmap[map[j][i]] = 0;
                        map[j][i] = 0;
                    }
                    int k = 0;
                    while(!pq.isEmpty()){
                        
                        Node now = pq.poll();
                        map[++k][i] = now.x;
                        map[++k][i] = now.y;
                        if(max < k)
                            max = k;
                        if(k == 100){
                            while(!pq.isEmpty())    pq.poll();
                            break;
                        }
                    }
                }
                nR = max;
                time++;
            }
            
        }
        return time;


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        R = Integer.parseInt(nv[0]);
        C = Integer.parseInt(nv[1]);
        K = Integer.parseInt(nv[2]);

        map = new int[101][101];
        for(int i = 1; i <= 3; i++){
            nv = br.readLine().split(" ");
            for(int j = 1; j <= 3; j++){
                map[i][j] = Integer.parseInt(nv[j-1]);
            }
        }        
        System.out.println(sort());

    }
    static class Node implements Comparable<Node>{
        int x,y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Node o){
            if(y == o.y){
                return x - o.x;
            }
            return y - o.y;
        } 
    }
    
}
//nC, nR 헷갈려서 오래걸림
//https://www.acmicpc.net/problem/17140

