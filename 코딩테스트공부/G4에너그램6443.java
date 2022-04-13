package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G4에너그램6443 {
    static BufferedWriter bw;
    static char cmap[], comchar[], mx[];
    static boolean visited[];
    static int N, length;

    public static void combi(int depth) throws IOException{
        if(depth == length){                        
            bw.write(comchar);
            bw.newLine();            
            return;
        }
        mx[depth] = 0;
        for(int i = 0; i < length; i++){
            if(visited[i])  continue;
            if(mx[depth] >= cmap[i]) continue;

            mx[depth] = cmap[i];
            
            visited[i] = true;
            comchar[depth] = cmap[i];
            combi(depth+1);            
            visited[i] = false;
        }


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            length = s.length();  

            cmap = new char[length];
            comchar = new char[length];
            mx = new char[length];
            visited = new boolean[length];
                      

            for(int j = 0; j < length; j++){
                cmap[j] = s.charAt(j);                
            }
           
            Arrays.sort(cmap);
            combi(0);

        }
        bw.flush();
        bw.close();

    }
}
//https://www.acmicpc.net/problem/6443
//큰거 다음에 작은거 못오게 할수 있게하면 풀리는 문제였음