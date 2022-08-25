import java.io.*;
import java.util.*;

public class 정올_종교1863 {
    static int N, M, res = 1;
    static int parents[];
    static HashSet<Integer> hset;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hset = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        parents = new int[N+1];
        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Union(a, b);

        }

        for(int i = 1; i <= N; i++)
            hset.add(Find(parents[i]));
        System.out.println(hset.size());

    }
    public static boolean Union(int x, int y){
        int xroot = Find(x);
        int yroot = Find(y);

        if(xroot != yroot){
            parents[xroot] = yroot;
            return true;
        }

        return false;
    }

    public static int Find(int n){
        if(parents[n] == n) return n;
        return parents[n] = Find(parents[n]);
    }
}
