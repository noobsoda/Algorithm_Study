import java.io.*;
import java.util.*;

public class S1상어초등학교21608 {
    static int N, res;
    static int stulove[][];
    static int map[][];
    static int lovemap[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int sx, sy;
    static LinkedList<Integer> list;

    public static int search(int x, int y){
        int n = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N)  continue;
            if(map[nx][ny] == 0)
                n++;
        }

        return n;
    }

    public static void sharkschool(int n){

        for(int i = 0; i < N; i++){
            Arrays.fill(lovemap[i], 0);
        }
        //1번조건
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < 4; k++){
                    if(map[i][j] == stulove[n][k]){
                        for(int z = 0; z < 4; z++){
                            int nx = i + dx[z];
                            int ny = j + dy[z];

                            if(nx < 0 || ny < 0 || nx >= N || ny >= N)  continue;
                            if(map[nx][ny] == 0)
                                lovemap[nx][ny]++;
                        }
                    }

                }
            }
        }
        int lovemax = 0;
        //좋아하는 학생자리 탐색
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(lovemap[i][j] > lovemax){
                    lovemax = lovemap[i][j];
                }
            }
        }


        //2,3번 조건 수행
        int max = -1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] != 0)
                    continue;
                if(lovemap[i][j] == lovemax){
                    int a = search(i, j);
                    //비어있는 칸이 가장 많은 칸
                    //최종 자리 정하기
                    if(max < a){
                        max = a;
                        sx = i;
                        sy = j;
                    }   
                }
            }
        }

        map[sx][sy] = n;


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stulove = new int[N*N+1][4];
        map = new int[N][N];
        lovemap = new int[N][N];
        list = new LinkedList<>();
        String nv[];

        for(int i = 0; i < N*N; i++){
            nv = br.readLine().split(" ");
            int n = Integer.parseInt(nv[0]);
            for(int j = 0; j < 4; j++){
                int v = Integer.parseInt(nv[j+1]);
                stulove[n][j] = v;
                
            }
            list.add(n);
        }
        for(Iterator<Integer> it = list.iterator(); it.hasNext();){
            int n = it.next();
            sharkschool(n);
        }
        //호감도 구하기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int n = 0;
                for(int k = 0; k < 4; k++){
                    for(int z = 0; z < 4; z++){
                        int nx = i + dx[z];
                        int ny = j + dy[z];

                        if(nx < 0 || ny < 0 || nx >= N || ny >= N)  continue;
                        if(stulove[map[i][j]][k] == map[nx][ny])
                            n++;
                    }
                }
                res += (int)Math.pow(10, n) / 10;

            }
        }
        // for(int i = 0; i < N; i++){
        //     System.out.println(Arrays.toString(map[i]));
        // }        

        System.out.println(res);


    }
}
    