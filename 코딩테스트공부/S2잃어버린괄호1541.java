package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class S2잃어버린괄호1541 {
    static int numbers[];
    static char sik[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        int sum = 0;
        int res = 0;

        boolean flag = false;

        while(st.hasMoreTokens()){

            String s = st.nextToken();
            StringTokenizer st2 = new StringTokenizer(s, "+");
            
            sum = 0;
            while(st2.hasMoreTokens()){

                sum += Integer.parseInt(st2.nextToken());
            }
            if(!flag){
                res += sum;
                flag = true;
            }
            else
                res -= sum;

        }

        System.out.println(res);

            

        
    }
}
//더하기 먼저 후 빼기
//https://www.acmicpc.net/problem/1541