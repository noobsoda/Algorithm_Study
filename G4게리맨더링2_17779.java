import java.io.*;
import java.util.*;

public class G4게리맨더링2_17779 {
    static int N, res = Integer.MAX_VALUE;
    static int map[][];
    static int colormap[][];
    static int dist[];   

    public static void division(int x, int y, int d1, int d2){
        for(int i = 0; i <= N; i++){
            Arrays.fill(colormap[i], 0);
        }
        //경계선 긋기

        //1번구
        int nx = x;
        int ny = y;
        while(nx <= x + d1 && ny >= y - d1){            
            colormap[nx][ny] = 5;
            nx++;
            ny--;
        }
        
        //2번구
        nx = x;
        ny = y;
        while(nx <= x + d2 && ny <= y + d2){            
            colormap[nx][ny] = 5;
            nx++;
            ny++;
        }
        

        //3번구
        nx = x+d1;
        ny = y-d1;
        while(nx <= x + d1 + d2 && ny <= y - d1 + d2){            
            colormap[nx][ny] = 5;
            nx++;
            ny++;
        }        

        //4번구
        nx = x+d2;
        ny = y+d2;
        while(nx <= x + d2 + d1 && ny >= y + d2 - d1){            
            colormap[nx][ny] = 5;
            nx++;
            ny--;
        }        
        
        //색칠하기

        //5번 칠하기
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                boolean flag = false;
                if(colormap[i][j] == 5){                    
                    for(int k = j+1; k <= N; k++){
                        if(colormap[i][k] == 5)
                            flag = true;
                        
                    }
                    if(flag){
                        for(int k = j+1; k <= N; k++){
                            colormap[i][k] = 5;
                            if(colormap[i][k+1] == 5)
                                break;                           
                        }
                    }
                }
                if(flag) break;
            }
        }

        //1번구
        for(int i = 1; i < x + d1; i++){
            for(int j = 1; j <= y; j++){
                if(colormap[i][j] == 5)
                continue;

                colormap[i][j] = 1;
            }
        }

        //2번구
        for(int i = 1; i <= x + d2; i++){
            for(int j = y+1; j <= N; j++){
                if(colormap[i][j] == 5)
                    continue;

                colormap[i][j] = 2;
            }
        }

        //3번구
        for(int i = x+ d1; i <= N; i++){
            for(int j = 1; j < y-d1+d2; j++){
                if(colormap[i][j] == 5)
                    continue;

                colormap[i][j] = 3;
            }
        }

        //4번구
        for(int i = x + d2+1; i <= N; i++){
            for(int j = y-d1+d2; j <= N; j++){
                if(colormap[i][j] == 5)
                    continue;

                colormap[i][j] = 4;
            }
        }

    }

   

    public static void gerrymandering2(int x, int y){        

        //d1, d2 정하는 코드
        for(int d1 = 1; y-d1 >= 1; d1++){
            for(int d2 = 1; y+d2 <= N; d2++){
                if(x + d1 + d2 > N) continue;

                //경계선 나누고 색칠하기
                division(x, y, d1, d2);

                //dist 초기화
                Arrays.fill(dist, 0);

                //해당된 색깔에 맞게 인구수 더하기
                for(int i = 1; i <= N; i++){
                    for(int j = 1; j <= N; j++){
                        dist[colormap[i][j]] += map[i][j];                        
                    }
                }
                int max = 0, min = Integer.MAX_VALUE;
                
                for(int i = 1; i <= 5; i++){
                    max = Math.max(max, dist[i]);
                    min = Math.min(min, dist[i]);                    
                }
                res = Math.min(res, max-min);
                
            }
        }


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        colormap = new int[N+1][N+1];
        dist = new int[6];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());            
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x = 1; x <= N; x++){
            for(int y = 1; y <= N; y++){
                
                gerrymandering2(x, y);
            }
        }    

        System.out.println(res);

       
    }    
}
//https://www.acmicpc.net/problem/17779