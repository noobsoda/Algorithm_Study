package 코딩테스트공부;
import java.util.*;
 
public class L3표편집81303 {

    public static void main(String[] args){
        String cmd[] = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        solution(8, 2, cmd);
    }
    public static String solution(int n, int k, String[] cmd) {
        int pre[] = new int[n];
        int next[] = new int[n];
        Stack<Node> stack = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));
        
        for(int i = 0; i < n; i++){
            pre[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;
                
        for(int i = 0; i < cmd.length; i++){
            char c = cmd[i].charAt(0);
            if(c == 'D'){
                int num = Integer.valueOf(cmd[i].substring(2));
                while(num-- > 0){
                    k = next[k];                    
                }                
            }
            else if(c == 'U'){
                int num = Integer.valueOf(cmd[i].substring(2));
                while(num-- > 0){
                    k = pre[k];                   
                }                
            }
            else if(c == 'C'){
                stack.add(new Node(pre[k], k, next[k]));
                if(pre[k] != -1) next[pre[k]] = next[k];
                if(next[k] != -1) pre[next[k]] = pre[k];
                sb.setCharAt(k, 'X');
                
                if(next[k] != -1) k = next[k];
                else    k = pre[k];
                
            }
            else{
                Node now = stack.pop();
                if(now.pre != -1) next[now.pre] = now.cur;
                if(now.nxt != -1) pre[now.nxt] = now.cur;
                sb.setCharAt(now.cur, 'O');
            }
            
            
        }
        
        //C하면 스택 하나 추가해서 연결
        //Z하면 POP해서 연결한거 다시 정상화
        return sb.toString();
        
    }
    
    static class Node{
        int pre, cur, nxt;
        
        public Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/81303