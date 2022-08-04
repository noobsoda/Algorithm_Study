package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S4카드2_2164 {
    static int N;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();

        for(int i = 1; i <= N; i++){
            q.add(i);
        }
        

        while(!q.isEmpty()){
            if(q.size() == 1){
                System.out.println(q.poll());    
                break;        
            }
            
            q.poll();            
            if(q.size() == 1){
                System.out.println(q.poll());
                break;
            }
            int n = q.poll();
            q.add(n);
        }

        

    }   
}
//https://www.acmicpc.net/problem/2164