import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G3줄세우기2252 {
    static int N, M;
    static int parents[];
    static ArrayList<Integer> arr[];
    static Queue<Integer> q;
    static StringBuilder sb;

    public static void bfs(){
        
        while(!q.isEmpty()){
            int n = q.poll();

            for(int now : arr[n]){
                parents[now] -= 1;
                if(parents[now] == 0){
                    q.add(now);
                    sb.append(now + " ");
                }
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
        arr = new ArrayList[N+1];
        parents = new int[N+1];
        q = new ArrayDeque<>();

        for(int i = 0; i <= N; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            parents[b]++;
            arr[a].add(b);
        }
        for(int i = 1; i <= N; i++){
            if(parents[i] == 0){
                q.add(i);
                sb.append(i + " ");
            }
        }

        bfs();

        System.out.println(sb.toString());

    }
    
}
//https://www.acmicpc.net/problem/2252