import java.io.*;
import java.util.*;
public class G2_2048_Easy{
    static int N, max = 0;
    static int map[][][];
    static int tempmap[][][];
    static boolean changed[][];

    public static void up(int depth){
        //i 두번째 열부터 탐색 첫번째 열은 탐색 안해도 됨
        for(int i = 1; i < N; i++){
            for(int j = 0; j < N; j++){
                int tempk = i;
                //k의 이전 값 k가 0, 1, 0, 2 1 0이 될 때
                //tempk는 i 초기값, i초기값, k이전값, i초기값 k이전값 2개로 구성
                //i초기값은 현재 탐색하는 대상을 뜻 함
                //k이전값은 탐색하는 대상이 k로 넘어갔을 경우 k이전값을 넣어 따라가서 탐색하는 것을 뜻함
                for(int k = i-1; k >= 0; k--){
                    if(map[depth][tempk][j] == 0)
                        continue;
                    if(map[depth][k][j] == map[depth][tempk][j] && !changed[k][j] && !changed[tempk][j]){
                        map[depth][k][j] *= 2;
                        if(max < map[depth][k][j])
                            max = map[depth][k][j];
                        map[depth][tempk][j] = 0;
                        changed[k][j] = true;
                    }
                    else if(map[depth][k][j] == 0){
                        map[depth][k][j] += map[depth][tempk][j];
                        map[depth][tempk][j] = 0;
                    }
                    tempk = k;
                }
                
            }
        }
        for(boolean a[] : changed){
            Arrays.fill(a, false);
        }
    }
    public static void left(int depth){
        for(int i = 1; i < N; i++){
            for(int j = 0; j < N; j++){
                int tempk = i;
                for(int k = i-1; k >= 0; k--){
                    if(map[depth][j][tempk] == 0)
                        continue;
                    if(map[depth][j][k] == map[depth][j][tempk] && !changed[j][k] && !changed[j][tempk]){
                        map[depth][j][k] *= 2;
                        if(max < map[depth][k][j])
                            max = map[depth][k][j];
                        map[depth][j][tempk] = 0;
                        changed[j][k] = true;
                    }
                    else if(map[depth][j][k] == 0){
                        map[depth][j][k] += map[depth][j][tempk];
                        map[depth][j][tempk] = 0;
                    }
                    tempk = k;
                }
                
            }
        }
        for(boolean a[] : changed){
            Arrays.fill(a, false);
        }
    }
    public static void right(int depth){
        for(int i = N-2; i >= 0; i--){
            for(int j = N-1; j >= 0; j--){
                int tempk = i;
                for(int k = i+1; k < N; k++){
                    if(map[depth][j][tempk] == 0)
                        continue;
                    if(map[depth][j][k] == map[depth][j][tempk] && !changed[j][k] && !changed[j][tempk]){
                        map[depth][j][k] *= 2;
                        if(max < map[depth][j][k])
                            max = map[depth][j][k]; //문제의 코드 depth 추가를 안 했음
                        map[depth][j][tempk] = 0;
                        changed[j][k] = true;
                    }
                    else if(map[depth][j][k] == 0){
                        map[depth][j][k] += map[depth][j][tempk];
                        map[depth][j][tempk] = 0;
                    }
                    tempk = k;
                }
                
            }
        }    
        for(boolean a[] : changed){
            Arrays.fill(a, false);
        }
    }
    public static void down(int depth){
        for(int i = N-2; i >= 0; i--){
            for(int j = N-1; j >= 0; j--){
                int tempk = i;
                for(int k = i+1; k < N; k++){
                    if(map[depth][tempk][j] == 0)
                        continue;
                    if(map[depth][k][j] == map[depth][tempk][j] && !changed[k][j] && !changed[tempk][j]){
                        map[depth][k][j] *= 2;
                        if(max < map[depth][k][j])
                            max = map[depth][k][j];
                        map[depth][tempk][j] = 0;
                        changed[k][j] = true;
                    }
                    else if(map[depth][k][j] == 0){
                        map[depth][k][j] += map[depth][tempk][j];
                        map[depth][tempk][j] = 0;
                    }
                    tempk = k;
                }
                
            }
        }    
        for(boolean a[] : changed){
            Arrays.fill(a, false);
        }
    }

    public static void rollback(int depth){
        //이전값들을 저장한곳으로 롤백
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[depth][i][j] = tempmap[depth][i][j];   
                if(max < map[depth][i][j])
                    max = map[depth][i][j]; 
                           
            }
        }
    }
    public static void g2048(int depth){
        if(depth == 5){   
            return;
        }
        else{
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    //이전 깊이에서 바뀐값들을 복사해서 데려오고 하는김에 해당 깊이의 세이브포인트도 만듬
                    if(depth > 0)
                        map[depth][i][j] = map[depth-1][i][j];                    
                    tempmap[depth][i][j] = map[depth][i][j];
                }
            }            
            for(int i = 0; i < 4; i++){                             

                if(i == 0){
                    up(depth);                    
                }
                else if(i == 1){
                    down(depth);                    
                }
                else if(i == 2){
                    left(depth);                    
                }
                else{
                    right(depth);                    
                }
                g2048(depth+1);
                rollback(depth);
            }        
            return;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[5][N+1][N+1];
        tempmap = new int[5][N+1][N+1];
        changed = new boolean[N+1][N+1];
        String[] nv;
        for(int i = 0; i < N; i++){
        nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[0][i][j] = Integer.parseInt(nv[j]);
            }
        }
        g2048(0);       
        System.out.println(max);



    }
}
//복사 붙여넣기 때문에 내부 소스코드가 안 바뀐줄 모르고 시간보냈음
//https://www.acmicpc.net/problem/12100
