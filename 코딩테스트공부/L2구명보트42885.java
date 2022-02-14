package 코딩테스트공부;
import java.util.*;

public class L2구명보트42885 {

    public static void main(String[] args) {
        solution(new int[]{20, 50, 50, 80}, 100);

    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        Deque<Integer> dq = new LinkedList<>();
       
        for(int i = 0; i < people.length; i++){
            dq.addLast(people[i]);
        }
        // int min = 0;
        // int max = people.length - 1;

        // while (min <= max) {
        //     if (people[min] + people[max] <= limit) ++min;
        //     --max;
        //     ++answer;
        // }
    
        while(!dq.isEmpty()){            
            if(dq.size() == 1){
                answer++;
                break;
            }
            
            int last = dq.removeLast();
            int first = dq.peek();            
            
            if(last + first <= limit){
                dq.removeFirst();
                answer++;
                System.out.println("test");
            }
            else{
                answer++;
            }
        }
        
        
        
        return answer;
    }
}