import java.io.*;
public class G5테트로미노14500 {
    static int N, M, max = 0;
    static int map[][];
    //기다란거
    static int Ix[][] = {{1, 2, 3},{0, 0, 0}};
    static int Iy[][] = {{0, 0, 0},{1, 2, 3}};
    //네모
    static int Ox[] = {1, 0, 1};
    static int Oy[] = {0, 1, 1};
    //J자
    static int Jx[][] = {{0, -1, -2},{1, 0, 0}, {0, 1, 2}, {-1, 0, 0}, {0, -1, -2},{-1, 0, 0}, {0, 1, 2}, {1, 0, 0}};
    static int Jy[][] = {{1, 0, 0},{0, 1, 2}, {-1, 0, 0}, {0, -1, -2}, {-1, 0, 0},{0, 1, 2}, {1, 0, 0}, {0, -1, -2}};
    //T자
    static int Tx[][] = {{0, 1, 0},{1, 0, -1}, {0, -1, 0}, {-1, 0, 1}};
    static int Ty[][] = {{1, 0, -1},{0, -1, 0}, {-1, 0, 1}, {0, 1, 0}};
    //Z자
    static int Zx[][] = {{-1, 0, 1},{0, 1, 1}, {-1, 0, 1},{0, 1, 1}};
    static int Zy[][] = {{0, 1, 1},{1, 0, -1}, {1, 1, 0},{-1, 0, 1}};

    public static void I(int x, int y){
        int sum = map[x][y];

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                int nx = x + Ix[i][j];
                int ny = y + Iy[i][j];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    sum = 0;
                    break;
                }

                sum += map[nx][ny];
            }

            max = Math.max(sum, max);
            sum = map[x][y];
        }
    }
    public static void O(int x, int y){
        int sum = map[x][y];

        for(int i = 0; i < 3; i++){
            int nx = x + Ox[i];
            int ny = y + Oy[i];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                sum = 0;
                break;
            }

            sum += map[nx][ny];
        }

        max = Math.max(sum, max);        
    }
    public static void Z(int x, int y){
        int sum = map[x][y];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                int nx = x + Zx[i][j];
                int ny = y + Zy[i][j];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    sum = 0;
                    break;
                }

                sum += map[nx][ny];
            }

            max = Math.max(sum, max);
            sum = map[x][y];
        }
    }
    
    public static void T(int x, int y){
        int sum = map[x][y];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                int nx = x + Tx[i][j];
                int ny = y + Ty[i][j];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    sum = 0;
                    break;
                }

                sum += map[nx][ny];
            }

            max = Math.max(sum, max);
            sum = map[x][y];
        }
    }

    public static void J(int x, int y){
        int sum = map[x][y];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++){
                int nx = x + Jx[i][j];
                int ny = y + Jy[i][j];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    sum = 0;
                    break;
                }

                sum += map[nx][ny];
            }

            max = Math.max(sum, max);
            sum = map[x][y];
        }
    }

    public static void tetromino(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                I(i, j);
                O(i, j);
                J(i, j);
                T(i, j);
                Z(i, j);

            }
        }
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
        tetromino();
        System.out.println(max);

    }
}
//https://www.acmicpc.net/problem/14500
