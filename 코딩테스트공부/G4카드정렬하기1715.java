package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G4카드정렬하기1715 {
    static int N;
    static long sum = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(Long.parseLong(st.nextToken()));
        }

        while(pq.size() > 1){
            long temp1 = pq.poll();
            long temp2 = pq.poll();
            sum += temp1 + temp2;
            pq.add(temp1 + temp2);
        }

        System.out.println(sum);
        //30 60 100
        //



    }
    
}
//https://www.acmicpc.net/problem/1715