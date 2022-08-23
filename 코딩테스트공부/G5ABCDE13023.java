import java.io.*;
import java.util.*;

public class G5ABCDE13023 {
    static int N, M;
    static boolean visited[];
    static ArrayList<Integer> arr[];

    
    
    public static boolean dfs(int start, int depth){
        if(depth == 5)
            return true;

        for(int now : arr[start]){
            if(visited[now])    continue;

            visited[now] = true;
            if(dfs(now, depth+1))   return true;
            visited[now] = false;

        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        arr = new ArrayList[N];

        for(int i = 0; i < N; i++){
            arr[i] = new ArrayList<>();
        }




        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);

        }
        boolean flag = false;
        for(int i = 0; i < N; i++){
            visited[i] = true;
            if(dfs(i, 1)){
                flag = true;
                break;
            }
            visited[i] = false;
        }
        System.out.println(flag ? 1 : 0);



        //한붓그리기 5개 이상 가능?

    }
    
}
