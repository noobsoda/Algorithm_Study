package 코딩테스트공부;
import java.io.*;
import java.util.*;
public class G4단어수학1339 {
    static int[] alpha = new int[26];
    static int N;

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());            
            String s = st.nextToken();            
            int size = s.length();

            int base = (int) Math.pow(10, size -1);

            for(int j = 0; j < size; j++){
                alpha[s.charAt(j) - 'A'] += base;
                base /= 10;
            }
            
        }
        Arrays.sort(alpha);

        int ans = 0;
        for(int i = 25; i >= 17; i--){
            ans += alpha[i] * (i-16);            
        }
        
        
        System.out.println(ans);

    }
}
//https://www.acmicpc.net/problem/1339