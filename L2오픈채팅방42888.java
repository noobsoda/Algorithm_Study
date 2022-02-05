import java.util.*;

public class L2오픈채팅방42888 {

    public static void main(String[] args){
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234"
        ,"Enter uid1234 Prodo","Change uid4567 Ryan"};
        solution(record);
    }


    
    public static String[] solution(String[] record) {              
        HashMap<String, String> map = new HashMap<String, String>();
        StringTokenizer st;
        int cnt = 0;
    
        for(int i=0; i<record.length; i++) {
            st = new StringTokenizer(record[i]);
            String first = st.nextToken();
            String id = st.nextToken();
        
            if(first.equals("Leave")){
                cnt++;                
            }             
            else if(first.equals("Enter")){
                cnt++;  
                map.put(id, st.nextToken());
            }
            else{
                map.put(id, st.nextToken());
            }
        }
    
        String[] answer = new String[cnt];
        cnt = 0;
    
        for(int i=0; i<record.length; i++) { 
            st = new StringTokenizer(record[i]);   
            String first = st.nextToken();
            if(first.equals("Enter")) {
                answer[cnt++] = map.get(st.nextToken()) + "님이 들어왔습니다.";
            }else if(first.equals("Leave")) {
                answer[cnt++] = map.get(st.nextToken()) + "님이 나갔습니다.";
            }
        }        
    
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/42888