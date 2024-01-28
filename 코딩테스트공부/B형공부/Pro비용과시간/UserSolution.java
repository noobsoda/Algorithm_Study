package 코딩테스트공부.B형공부.Pro비용과시간;
import java.util.*;
public class UserSolution {
    static PriorityQueue<Node> pq;
    static List<Node> list[];
    public void init(int N, int K, int[] sCity, int[] eCity, int[] mCost, int[] mTime) {
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();            
        }
        for (int i = 0; i < K; i++) {
            list[sCity[i]].add(new Node(eCity[i], mCost[i], mTime[i]));           
        }
        return;
    }

    public void add(int sCity, int eCity, int mCost, int mTime) {
        list[sCity].add(new Node(eCity, mCost, mTime));
    }

    public int cost(int M, int sCity, int eCity) {
        pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.time == o2.time)
                return o1.cost - o2.cost;
            return o1.time - o2.time;
        });
        pq.add(new Node(sCity, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(now.end == eCity)
                return now.time;

            for(Node node : list[now.end]){
                if(now.cost + node.cost > M)
                    continue;
                pq.add(new Node(node.end, now.cost + node.cost, now.time + node.time));
            }
        }
        return -1;
    }
    static class Node{
        int end, cost, time;
        public Node(int end, int cost, int time){
            this.end = end;
            this.cost = cost;
            this.time = time;
        }
    }
    
}
