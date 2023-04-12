package 코딩테스트공부.백준;
import java.io.*;

public class S5영화감독숌1436 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int cnt = 0;
        int a = 666;
        
        String s;
        while(cnt < n){
            s = String.valueOf(a);
            for(int i = 0; i < s.length()-2; i++){
                if(s.charAt(i) == '6' && s.charAt(i+1) == '6' && s.charAt(i+2) == '6'){
                    cnt++;
                    break;
                }
            }
            a++;
        }
        System.out.println(a-1);
    }    
}
