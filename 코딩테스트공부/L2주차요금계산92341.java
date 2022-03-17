package 코딩테스트공부;
import java.util.*;

public class L2주차요금계산92341 {
    static int basetime, baserate, unittime, unitrate;

    public static void main(String[] args){
        int n[] = {180, 5000, 10, 600};
        String s[] = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", 
        "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution(n, s)));
    }
    public static int[] solution(int[] fees, String[] records) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        HashMap<Integer, Integer> hres = new HashMap<>();
        
        int[] answer;        
        basetime = fees[0];
        baserate = fees[1];
        unittime = fees[2];
        unitrate = fees[3];        
               
        for(String s : records){              
            StringTokenizer st = new StringTokenizer(s, " :");
            int h = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            int id = Integer.parseInt(st.nextToken());
            String io = st.nextToken();
            int time = h * 60 + m;
            if(io.equals("IN")){
                hmap.put(id, time);
            }
            else{
                int htime = time - hmap.get(id);
                
                if(hres.containsKey(id)){
                    int rtime = hres.get(id);
                    hres.put(id, rtime+htime);
                }
                else{
                    hres.put(id, htime);
                    
                }
                hmap.remove(id);
            }        
        }
        //입차만 하고 출하 하지 않는 경우 최대치 계산
        for(int id : hmap.keySet()){
            int htime = 23*60 + 59 - hmap.get(id);
            
            if(hres.containsKey(id)){
                int rtime = hres.get(id);
                hres.put(id, rtime+htime);
            }
            else{
                hres.put(id, htime);
            }
        }

        answer = new int[hres.size()];
        int cnt = 0;
                
        int seq[] = new int[hres.size()];
        //차량 번호 작은만큼 정렬
        for(int n : hres.keySet()){
            seq[cnt] = n;  
            cnt++;
        }
        
        Arrays.sort(seq);
        
        cnt = 0;
        for(int n : seq){            
            double d = hres.get(n);
            if(d <= basetime){
                answer[cnt] = baserate; 
            }
            else{                
                answer[cnt] = baserate + (int)Math.ceil((d - basetime)/unittime) * unitrate;
            }
            cnt++;
        }
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/92341#