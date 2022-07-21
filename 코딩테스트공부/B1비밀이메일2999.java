package 코딩테스트공부;
import java.io.*;

public class B1비밀이메일2999 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char map[][];
        
        int N = s.length();
        int R = 0, C = 0;
        for(int i = 1; i < N; i++){            
            if(N % i == 0){
                if(i > N/i)
                    break;

                R = i;
                C = N/i;
                
            }
        }
        map = new char[R][C];
        int cnt = 0;
        for(int i = 0; i < N; i++){
            map[i % R][cnt] += s.charAt(i);
            if(i % R == R-1)  cnt++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++){
            sb.append(map[i]);
        }
        System.out.println(sb.toString());




    }
}
//https://www.acmicpc.net/problem/2999