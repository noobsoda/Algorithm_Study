package 코딩테스트공부;
import java.util.*;

public class L3불량사용자64064 {
    static boolean visited[];
    static HashMap<String, Integer> hmap;
    static int N, M, answer;
    public static void dfs(int depth, String[] user_id, String[] banned_id){
        if(depth == M){            
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < visited.length; i++){
                if(visited[i])
                    sb.append('O');
                else
                    sb.append('X');                
            }
            String s = sb.toString();
            if(hmap.containsKey(s))
                return;
                
            hmap.put(s, 1);            
            answer++;
            return;
        }
        for(int i = 0; i < N; i++){            
            //일치할때
            if(banned_id[depth].length() != user_id[i].length())
                continue;
            boolean flag = false;
            for(int j = 0; j < banned_id[depth].length(); j++){
                if(banned_id[depth].charAt(j) == '*')
                    continue;
                if(banned_id[depth].charAt(j) != user_id[i].charAt(j)){
                    flag = true;
                    break;
                }
            }
            if(!flag && !visited[i]){
                visited[i] = true;
                dfs(depth+1, user_id, banned_id);
                visited[i] = false;
            }
            
        }
    }
    public int solution(String[] user_id, String[] banned_id) {
        
        answer = 0;        
        N = user_id.length;
        M = banned_id.length;
        visited = new boolean[N];
        hmap = new HashMap<>();
        dfs(0, user_id, banned_id);
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/64064/