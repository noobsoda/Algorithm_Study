package 코딩테스트공부;

import java.util.*;
public class L2튜플64065 {
    
    public int[] solution(String s) {        
        int[] answer;
        
        HashMap<Integer, String> hmap = new HashMap<>();
        
        String[] strs = s.replaceAll("[{}]", " ").trim().split(" ,");
        answer = new int[strs.length];
        Arrays.sort(strs, (a,b) -> (a.length()-b.length()));
         
        int i = 0;
        for(String str : strs){
            for(String st : str.split(",")){
                int a = Integer.parseInt(st.trim());
                                
                if(hmap.containsKey(a)) continue;
                hmap.put(a, "d");
                answer[i++] = a;                
            }
        }
    
        return answer;
    }
}