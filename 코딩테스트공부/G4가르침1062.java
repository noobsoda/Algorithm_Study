package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G4가르침1062 {
    static int N, K, res = 0;
    static HashMap<Character, Integer> hmap;
    static HashMap<Character, Integer> tokenhmap;
    static String words[];
    static String tokenWords[];
    static char c[];

    public static void dfs(int depth, int start){
        if(depth == K-5){    
            int cnt = 0;        
            for(int i = 0; i < N; i++){
                boolean read = true;
                for(int j = 0; j < tokenWords[i].length(); j++){
                    if(!tokenhmap.containsKey(tokenWords[i].charAt(j))){
                        read = false;
                        break;
                    }
                }                
                if(read)
                    cnt++;
                
            }
            res = Math.max(cnt, res);
            return;
        }
        for(int i = start; i < c.length; i++){
            tokenhmap.put(c[i], 0);
            dfs(depth+1, i+1);
            tokenhmap.remove(c[i], 0);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        words = new String[N];
        tokenWords = new String[N];
        hmap = new HashMap<>();
        tokenhmap = new HashMap<>();

        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
            tokenWords[i] = words[i].replaceAll("[antci]", "");
            if(tokenWords[i].isEmpty()){
                res++;
            }
        }
        
        if(K < 5){
            System.out.println(0);
            return;
        }     

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < tokenWords[i].length(); j++){       
                if(hmap.containsKey(tokenWords[i].charAt(j))){
                    continue;
                }
                hmap.put(tokenWords[i].charAt(j), cnt++);

            }
        }
        
        c = new char[hmap.size()];

        for(char a : hmap.keySet()){
            c[hmap.get(a)] = a;
        }
        if(c.length <= K-5){
            System.out.println(N);
            return;
        }
        else{
            dfs(0, 0);
        }

        System.out.println(res);

    }
    
}
//https://www.acmicpc.net/problem/1062
//replaceAll은 시간이 많이 걸린다