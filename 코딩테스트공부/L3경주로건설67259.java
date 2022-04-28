package 코딩테스트공부;

import java.util.*;
public class L3경주로건설67259 {
    //실행시간 초과하면 dp로 
    static int N, M, answer = Integer.MAX_VALUE;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int dp[][];
    
    static boolean visited[][];
    public static void dfs(int x, int y, int d, int price, int[][] board){
        if(price >= answer){
            return;
        }
        if(x == N-1 && y == M-1){
            answer = Math.min(answer, price);
        }
        
        
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nprice = price+100;
            
            if(nx < 0 || ny < 0 || nx >= N || ny >= M)  continue;            
            if(visited[nx][ny] || board[nx][ny] == 1) continue;
            
            //dp에서 직선 곡선 구분 해줘야 할듯
            //시작 지점이 아니고 직선이 아니면 500원 플러스
            if(d != i && d != -1) nprice += 500;   
            if(dp[nx][ny] < nprice) continue;
            
            visited[nx][ny] = true;
            //곡선 가격 고려해서 시작 지점에 가격 저장
            dp[x][y] = nprice;
            dfs(nx, ny, i, nprice, board);
            visited[nx][ny] = false;
        }        
        
    }
    public int solution(int[][] board) {
        
        N = board.length;
        M = board[0].length;
                
        visited = new boolean[N][M];
        dp = new int[N][M];
        for(int i = 0; i < N; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        visited[0][0] = true;
        dfs(0, 0, -1, 0, board);
        
        return answer;
    }
   
}
//https://programmers.co.kr/learn/courses/30/lessons/67259