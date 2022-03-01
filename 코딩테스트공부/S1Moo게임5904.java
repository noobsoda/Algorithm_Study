package 코딩테스트공부;
import java.io.*;

public class S1Moo게임5904 {
    static int N;
    public static char answer;

    public static Integer check(int n){
        if(n == 0){
            return 3;
        }
        int v = check(n-1) * 2 + n + 3;
        return v;
    }

    public static void Moo(int num){
         int size=3; 
         int index=0; 
         if(num==1){
              answer='m'; 
            }
        else if(num<=3) 
        answer='o';

        else{
             while(size<num){
                  size=size*2+index+4; 
                  index++; 
                } 
            int front_back=(size-index-3)/2; 
            if(size-front_back+1<=num){
                 Moo(num-size+front_back); 
            }
            else if(num==front_back+1) 
                answer='m'; 
            else answer='o'; 
        } 
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        Moo(N);
        
        System.out.println(answer);


    }    
}
//https://www.acmicpc.net/problem/5904