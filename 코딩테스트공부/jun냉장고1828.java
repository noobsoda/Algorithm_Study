package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class jun냉장고1828 {
    static int N, res;
    static ArrayList<Node> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
       
        arr = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Node(x, y));

        }

        Collections.sort(arr, ((o1, o2) -> {return o1.y - o2.y;}));
     
        int end = Integer.MIN_VALUE;
        for(Iterator<Node> it = arr.iterator(); it.hasNext();){
            Node now = it.next();
            if(end == Integer.MIN_VALUE){
                end = now.y;
                res++;
                continue;
            }
            if(now.x <= end)
                continue;
            else{
                end = now.y;
                res++;
            }
            
                
        }

        System.out.println(res);
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
