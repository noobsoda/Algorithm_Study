package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class P4돌던지기3025 {
    static int R, C, N;
    static char map[][];

    public static boolean leftCheck(int r, int start){
        if(start - 1 < 0)
            return false;
        if(map[r][start-1] == '.' && map[r-1][start-1] == '.')
            return true;

        return false;
    }
    public static boolean rightCheck(int r, int start){
        if(start + 1 >= C)
            return false;
        if(map[r][start+1] == '.' && map[r-1][start+1] == '.')
            return true;

        return false;
    }

    public static void fireball(int start){
        // 2. 화산탄이 아래로 떨어진다
        for(int i = 1; i < R; i++){
            // 1. 화산탄의 아래칸이 장애물로 막혀 있으면 굳는다
            if(map[i][start] == 'X'){
                map[i-1][start] = 'O';
                break;
            }
            
            // 3. 굳은 화산탄이 있다면
            else if(map[i][start] == 'O'){
                //왼쪽 체크
                if(leftCheck(i, start)){
                    start--;
                    if(i == R-1){
                        map[i][start] = 'O';
                        break;
                    }
                }
                //오른쪽 체크
                else if(rightCheck(i, start)){
                    start++;
                    if(i == R-1){
                        map[i][start] = 'O';
                        break;
                    }
                }
                //전부 못가면 굳는다
                else{
                    map[i-1][start] = 'O';
                    break;
                }
                
            }
            
            //만약 땅이라면 그 자리에서 굳는다
            if(i == R-1){
                map[i][start] = 'O';
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++){
            String nv = br.readLine();
            map[i] = nv.toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            fireball(n-1);
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                bw.write(map[i][j] + "");
            }
            bw.write("\n");
        }
        bw.flush();





    }
}
//https://www.acmicpc.net/problem/3025