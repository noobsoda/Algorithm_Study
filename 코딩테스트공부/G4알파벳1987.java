package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G4알파벳1987 {
    static int R, C, max;
    static char cmap[][];
    static boolean alphabet[];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    public static void dfs(int x, int y, int cnt){
        max = Math.max(max, cnt);

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= R || ny >= C || alphabet[cmap[nx][ny]-65])  continue;

            alphabet[cmap[nx][ny] - 65] = true;
            dfs(nx, ny, cnt+1);
            alphabet[cmap[nx][ny] - 65] = false;


        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cmap = new char[R][C];
        alphabet = new boolean[27];

        for(int i = 0; i < R; i++){
            cmap[i] = br.readLine().toCharArray();
        }

        alphabet[cmap[0][0]-65] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }
}
