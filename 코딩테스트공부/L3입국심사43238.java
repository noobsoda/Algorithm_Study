package 코딩테스트공부;

public class L3입국심사43238 {
    public static void main(String[] args){
        solution(6, new int[]{7,10});
    }
    public static long solution(int n, int[] times) {
        long answer = 0;
        
        long start = 1;
        long end = 100000000000000L;        
        
        while(start < end){
            long half = (start + end) / 2;
            
            long cnt = 0;
            for(int i = 0; i < times.length; i++){
                cnt += half / times[i];
            }
            if(cnt >= n){
                end = half;
            }
            else{
                start = half+1;
            }
        }
        answer = end;
        return answer;
    }
}