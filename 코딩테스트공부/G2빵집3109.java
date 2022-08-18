package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G2빵집3109 {
    static int R, C, res;
    static char map[][];
    static boolean visited[][];
    // 오른쪽 위, 오른쪽, 오른쪽 아래
    static int dx[] = {-1, 0, 1};
    static int dy[] = {1, 1, 1};


    public static boolean dfs(int x, int y){

        

        if(y == C-1)
            return true;

        for(int i = 0; i < 3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == 'x' || visited[nx][ny])  continue;

            visited[nx][ny] = true;
            if(dfs(nx, ny)){
                return true;
            }

        }

        return false;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++){
            map[i] = br.readLine().toCharArray();
        }
        for(int i = 0; i < R; i++){
            if(dfs(i, 0))
                res++;
        }

        System.out.println(res);

    }
}
//https://www.acmicpc.net/problem/3109