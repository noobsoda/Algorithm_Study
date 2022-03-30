package 코딩테스트공부;

import java.util.Arrays;

public class L2행렬테두리회전하기77485 {
    static int map[][];
    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1 ,6, 3}})));

    }
    public static int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        int[] answer = new int[queries.length];
        int cnt = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = ++cnt;                
            }
        }
        cnt = 0;
        for(int n[] : queries){
            int min;
            int x1 = n[0]-1;
            int y1 = n[1]-1;
            int x2 = n[2]-1;
            int y2 = n[3]-1;
         
            
            int temp = map[x1][y2];     
            min = temp;
            
            //위
            for(int i = 0; i < y2 - y1; i++){
                map[x1][y2-i] = map[x1][y2-i-1];
                min = Math.min(map[x1][y2-i-1], min);
            }
            //왼쪽
            for(int i = 0; i < x2 - x1; i++){
                map[x1+i][y1] = map[x1+i+1][y1];
                min = Math.min(map[x1+i+1][y1], min);
            }
            //아래
            for(int i = 0; i < y2 - y1; i++){
                map[x2][y1+i] = map[x2][y1+i+1];
                min = Math.min(map[x2][y1+i+1], min);
            }
            //오른쪽
            for(int i = 0; i < x2 - x1; i++){
                map[x2-i][y2] = map[x2-i-1][y2];
                min = Math.min(map[x2-i-1][y2], min);
            }
            
            map[x1+1][y2] = temp;            
            
            answer[cnt] = min;
            cnt++;
        }
        
        
        
        
        return answer;
    }
}