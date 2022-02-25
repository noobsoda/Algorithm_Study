package 코딩테스트공부;
import java.util.Arrays;

public class L2삼각달팽이68645 {
    static int map[][];
    static int dx[] = {1, 0, -1};
    static int dy[] = {0, 1, -1};

    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(4)));
        System.out.println(Arrays.toString(solution(5)));
        System.out.println(Arrays.toString(solution(6)));

    }

    public static int[] solution(int n) {
        int v = 0;
        for(int i = 1; i <= n; i++){
            v += i;
        }
        int[] answer = new int[v];
        map = new int[n][n];
        
        int x = -1;
        int y = 0;
        int d = 0;
        int cnt = 0;
        int max = n;
        
        while(max > 0){
            for(int i = 0; i < max; i++){
                x += dx[d];
                y += dy[d];
                map[x][y] = ++cnt;
            }
            max--;
            d++;
            d %= 3;
            
        }
        cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 0) continue;
                
                answer[cnt] = map[i][j];
                cnt++;
            }       
        }
        return answer;
        //대각선 삼각형으로 생각할것
    }
}