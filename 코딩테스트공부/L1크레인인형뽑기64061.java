package 코딩테스트공부;
import java.util.*;
public class L1크레인인형뽑기64061 {
    static Stack<Integer> stack;
    static int N, M;

    public static void main(String[] args){
        solution(new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, new int[]{1,5,3,5,1,2,1,4});
    }
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        stack = new Stack<>();
        
        
        
        for(int i : moves){
            int a = 0;
            for(int j = 0; j < N; j++){
                if(board[j][i-1] == 0)
                    continue;
                a = board[j][i-1];
                board[j][i-1] = 0;
                break;            
            }            
            if(a == 0)
                continue;                
            
            if(stack.isEmpty()){
                stack.add(a);
            }
            else{
                int n = stack.peek();                
                if(a == n){
                    stack.pop();
                    answer += 2;
                }
                else{
                    stack.add(a);
                }
            }
            
        }
                
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/64061