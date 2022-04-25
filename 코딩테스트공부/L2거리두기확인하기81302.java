package 코딩테스트공부;
import java.util.*;

public class L2거리두기확인하기81302 {
    static Queue<Node> q;
    static boolean visited[][];
    static char map[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};

    public static void main(String[] args){
        String places[][] = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
    {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution(places)));
    }
    
    public static boolean bfs(int x, int y){
        visited = new boolean[5][5];
        q.add(new Node(x, y, 0));
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            Node now = q.poll();
            
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5)  continue;
                if(visited[nx][ny])  continue;
                if(now.w == 2)  return true;
                
                if(map[nx][ny] == 'O') {                    
                    q.add(new Node(nx, ny, now.w+1));
                    visited[nx][ny] = true;                    
                        
                }
                if(map[nx][ny] == 'P'){
                    return false;
                }
            }
            
            
        }
        return true;
    }
    public static int[] solution(String[][] places) {
        q = new LinkedList<>();
        int[] answer = new int[places.length];
        map = new char[5][5];
        
        
        int cnt = 0;
        for(String[] s : places){
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    map[i][j] = s[i].charAt(j);
                }        
            }
            boolean flag = true;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(map[i][j] == 'P'){
                        flag = bfs(i, j);
                        q.clear();
                    }
                    if(!flag) break;
                }
                if(!flag) break;
            }
            
            if(flag){
                answer[cnt] = 1;
            }
            else{
                answer[cnt] = 0;
            }
            cnt++;
            
            
        }
        
        return answer;
    }
    static class Node{
        int x, y, w;
        public Node(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}