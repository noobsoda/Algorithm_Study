package 코딩테스트공부;
import java.util.*;

public class L3단속카메라42884 {
    static PriorityQueue<Node> pq;

    public static void main(String[] args){
        System.out.println(solution(new int[][]{{-20, -15}, {-14, 5}, {-18, -13}, {-5, -3}}));

    }

    
    public static int solution(int[][] routes) {
        int answer = 0;
        
        pq = new PriorityQueue<>();
        
        for(int i = 0; i < routes.length; i++){
            pq.add(new Node(routes[i][0], routes[i][1]));
        }
        
        int v = -30001;
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(now.start <= v && v <= now.end){
                continue;
            }
            answer++;
            v = now.end;
            
        }
        //end에서 끝 부분만 들어낼것
        
        
        return answer;
    }
    static class Node implements Comparable<Node>{
        int start, end;
        
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Node o){
            return end - o.end;
        }
        
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/42884