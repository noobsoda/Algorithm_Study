package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G2청소년상어19236 {
    static int map[][];
    static int dmap[][];

    static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};


    public static void movefish(int nmap[][], int ndmap[][]){

    }


    public static void youngshark(int x, int y, int temp[][], int dtemp[][]){
        int nmap[][] = new int[4][4];
        int ndmap[][] = new int[4][4];
        for(int i = 0; i < 4; i++){
            System.arraycopy(temp[i], 0, nmap[i], 0, temp[i].length);
            System.arraycopy(dtemp[i], 0, ndmap[i], 0, dtemp[i].length);
        }
        //물고기 먹기
        nmap[x][y] = 0;
        ndmap[x][y] = 0;

        //물고기 이동
        for(int i = 1; i <= 16; i++){
            boolean flag = false;
            for(int j = 0; j < 4; j++){
                for(int k = 0; j < 4; k++){
                    if(nmap[j][k] == i){
                        //방향 돌리기
                        for(int z = 0; z < 8; z++){
                            int nx = j + dx[(ndmap[j][k] + z) % 8];
                            int ny = k + dy[(ndmap[j][k] + z) % 8];

                            if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;

                            //교체하고 break
                            int n = nmap[j][k];
                            nmap[j][k] = nmap[nx][ny];
                            nmap[nx][ny] = n;

                            int d = ndmap[j][k];
                            ndmap[j][k] = ndmap[nx][ny];
                            ndmap[nx][ny] = d;

                            break;

                            //이동할수 없는 칸??

                        }
                    }
                    if(flag) break;
                }
                if(flag) break;
            }
        }

        //선택지 for문 돌려서 멀티버스 샤크


    }
    public static void main(String[] args) throws IOException{ 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dmap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        

    }   
    
}
