package 코딩테스트공부;
import java.util.*;

public class L3가장먼노드49189 {   
    static ArrayList<Integer> arr[];
    static Queue<Node> q;   
    static int max = 0;
    static int visited[];
    
    public static void bfs(int start){
        q.add(new Node(start, 1));
        visited[start] = 1;
        
        while(!q.isEmpty()){
            Node now = q.poll();
            max = Math.max(now.w, max);
            
            for(int narr : arr[now.n]){
                if(visited[narr] != 0)  continue;
                
                visited[narr] = now.w+1;
                q.add(new Node(narr, now.w+1));
            }
        }
        
        
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        arr = new ArrayList[n+1];
        q = new LinkedList<>();    
        visited = new int[n+1];
        
        for(int i = 0; i <= n; i++){
            arr[i] = new ArrayList<>();            
        }
        
        for(int i[] : edge){
            arr[i[0]].add(i[1]);
            arr[i[1]].add(i[0]);
        }
        bfs(1);
        
        for(int i : visited){
            if(i == max)
                answer++;
        }
       
        
        return answer;
    }
    static class Node{
        int n, w;
        public Node(int n, int w){
            this.n = n;
            this.w = w;
        }       
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/49189