import java.util.*;

public class L2뉴스클러스터링17677 {

    public static void main(String[] args){
        String str1 = "FRANCE";
        String str2 = "french";
        solution(str1, str2);
    }
    public static int solution(String str1, String str2) {
        double x = 0, u = 0;
        int answer = 0;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
                         
        
        for(int i = 0; i < str1.length()-1; i++){
            
            StringBuilder sb = new StringBuilder();
            
            sb.append(str1.charAt(i));
            sb.append(str1.charAt(i+1));
            
            String s = sb.toString();
                                    
            if(!s.matches("^[a-zA-Z]*$"))    continue;            
            
            if(map1.containsKey(s)){
                int n = map1.get(s);
                map1.put(s, n+1);
            }
            else{
                map1.put(s, 1);
            } 
        }
        
        for(int i = 0; i < str2.length()-1; i++){            
            StringBuilder sb = new StringBuilder();
            
            sb.append(str2.charAt(i));
            sb.append(str2.charAt(i+1));            
            String s = sb.toString();
                                    
            if(!s.matches("^[a-zA-Z]*$"))    continue;
                        
            if(map2.containsKey(s)){
                int n = map2.get(s);
                map2.put(s, n+1);
            }
            else{
                map2.put(s, 1);
            } 
        }        
        
        for(String s : map1.keySet()){
            int n1 = map1.get(s);
            //교집함
            if(map2.containsKey(s)){
                int n2 = map2.get(s);
                
                int min = Math.min(n1, n2);
                int max = Math.max(n1, n2);
                x += min;
                u += max;
                map2.put(s, 0);
            }   
            //왼쪽
            else{
                u += n1;
            }
        }
        //오른쪽
        for(String s : map2.keySet()){
            int n1 = map2.get(s);
            u += n1;
        }       
        
        if(x == 0 && u == 0){
            answer = 65536;    
        }
        else{
            double res = (x / u) * 65536;
            answer = (int)res;
        }
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/17677