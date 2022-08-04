package 코딩테스트공부;

import java.util.*;
import java.io.*;

public class D4괄호짝짓기1218
{
    static int N;
    static char sc[] = {'(', '[', '{', '<'};
    static char ec[] = {')', ']', '}', '>'};
    static int c[];
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int test_case = 1; test_case <= 1; test_case++)
		{
            int res = 1;
			st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String nv = br.readLine();
            c = new int[4];
            
            exit_label:
            for(int i = 0; i < nv.length(); i++){
                boolean flag = false;
                for(int j = 0; j < 4; j++){                    
                    if(nv.charAt(i) == sc[j]){                        
                        c[j]++;
                        flag = true;
                    }
                }
                if(flag)    continue;

                //시작 괄호가 아닐 때
                for(int j = 0; j < 4; j++){
                    if(nv.charAt(i) == ec[j]){                                                
                        c[j]--;

                        if(c[j] < 0){
                            res = 0;
                            break exit_label;
                        }
                        
                    }
                }
            }
            for(int i : c){
                if(i != 0)
                    res = 0;
            }
			
            System.out.println("#" + test_case + " " + res);
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD