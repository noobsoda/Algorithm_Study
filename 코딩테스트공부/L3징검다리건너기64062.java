package 코딩테스트공부;
public class L3징검다리건너기64062 {
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        int temp[] = new int[stones.length];
        int min = 200000000;
        int max = 0;
        
        for(int i : stones){
            max = Math.max(i, max);
            min = Math.min(i, min);
        }        
        answer = min;
        
        while(min < max){
            int middle = (min + max) / 2;
            //시간초과 나면 여기 다듬어서 한꺼번에 진행
            for(int i = 0; i < stones.length; i++){
                temp[i] = stones[i] - middle;
            }
            boolean flag = false;
            int cnt = 0;
            for(int i = 0; i < temp.length; i++){
                if(temp[i] <= 0)
                    cnt++;
                else
                    cnt = 0;
                    
                if(cnt >= k){
                    flag = true;                
                    break;
                }
            }            
            if(flag){
                max = middle;
            }
            else{
                min = middle+1;
            }
            answer = min;
            
        }
        return answer;
    }
}
//이분탐색
//https://programmers.co.kr/learn/courses/30/lessons/64062#