package 코딩테스트공부;
import java.util.*;
import java.io.*;

public class 모역_줄기세포배양5653
{

    static int N, M, K;    
    static int cellmap[][];
    static int tempmap[][];
    static int visited[][];
    static int activevisited[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    
    public static void stemcells(){       


        //-2 비접촉 상태, -1 죽은 상태, 0 활성 상태, 그 이상 비활성 상태
        for(int i = 0; i < N+2*K; i++){      
        	for(int j = 0; j < M + 2*K; j++){                
                //세포가 아직 살아있고 활성상태일때
                if(cellmap[i][j] > 0 && visited[i][j] == 0){   
                    activevisited[i][j]++;
                    if(cellmap[i][j] == activevisited[i][j])
                        visited[i][j] = -1;
                    for(int k = 0; k < 4; k++){                        
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        //죽은 상태가 아니고 접촉하지 못했을 때
                        if(cellmap[nx][ny] == 0 && visited[nx][ny] != -1){
                            //생명력이 더 높은걸로 갈아 끼워야 함
                            if(tempmap[nx][ny] < cellmap[i][j]){  
                                tempmap[nx][ny] = cellmap[i][j];
                                visited[nx][ny] = cellmap[i][j];                              
                            }
                             
                        }                        
                    }
                }
                //세포가 아직 비활성 상태 일때 대기시간 1 줄인다
                else if(cellmap[i][j] > 0 && visited[i][j] > 0){
                    visited[i][j]--;
                }
            }
        }
        
    }
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());       
            cellmap = new int[N + 2*K][M + 2*K];
            tempmap = new int[N + 2*K][M + 2*K];
            visited = new int[N + 2*K][M + 2*K];
            activevisited = new int[N + 2*K][M + 2*K];
            
            for(int i = 0; i < N+2*K; i++){                
                Arrays.fill(visited[i], -2);
            }
            
            for(int i = 0; i < N; i++){                
                st = new StringTokenizer(br.readLine());
             	for(int j = 0; j < M; j++){
                    int n = Integer.parseInt(st.nextToken());                          
                    cellmap[K+N/2+i-1][K+M/2+j-1] = n;
                    tempmap[K+N/2+i-1][K+M/2+j-1] = n;
                    if(n == 0)
                        continue;
                        
                    visited[K+N/2+i-1][K+M/2+j-1] = n;
                    
                }                
            }
            for(int i = 0; i < K; i++){
                stemcells();
                for(int j = 0; j < N +2*K; j++){                    
                    System.arraycopy(tempmap[j], 0, cellmap[j], 0, tempmap[i].length);
                }
                
            }
            
            
            int cnt = 0;
            for(int i = 0; i < N+2*K; i++){
                for(int j = 0; j < M+2*K; j++){                    
                    if(visited[i][j] >= 0)  
                        cnt++;             	   
                }    
            }
            System.out.println("#" + test_case + " " +cnt);
            
            
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo&
// 1
// 2 2 10
// 1 1
// 0 2