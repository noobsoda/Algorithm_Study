package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class D4추억의2048게임6109 {
    static int N;
    static char C;
    static int map[][];
    static boolean visited[][];
    //위 오른쪽 아래 왼쪽
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = st.nextToken().charAt(0);

            map = new int[N][N];
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            switch(C){
                case 'u':            
                    for(int i = 0; i < N; i++)
                        for(int j = 0; j < N; j++)
                            dfs(i, j, 0);
                    break;
                case 'r':            
                    for(int i = 0; i < N; i++)
                        for(int j = 0; j < N; j++)
                            dfs(j, N-1-i, 1);
                    break;
                case 'd':            
                    for(int i = 0; i < N; i++)
                        for(int j = 0; j < N; j++)
                            dfs(N-1-i, j, 2);
                    break;
                case 'l':            
                    for(int i = 0; i < N; i++)
                        for(int j = 0; j < N; j++)
                            dfs(j, i, 3);
                    break;


            }
            bw.write("#" + test_case + "\n");
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++)
                    bw.write(map[i][j] + " ");

                bw.write("\n");
            }

            bw.flush();
        }
    }

    public static void dfs(int x, int y, int d) {

        int nx = x + dx[d];
        int ny = y + dy[d];
        
        if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[x][y] == 0)
            return;
            
        if(map[nx][ny] == map[x][y] && !visited[nx][ny] && !visited[x][y]){
            visited[nx][ny] = true;
            map[nx][ny] *= 2;
            map[x][y] = 0;
        }
        if(map[nx][ny] == 0){
            map[nx][ny] = map[x][y];
            map[x][y] = 0;
            if(visited[x][y]){
                visited[nx][ny] = true;
                visited[x][y] = false;
            }


        }
        dfs(nx, ny, d);
            
        
    }
    
}

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWbrg9uabZsDFAWQ