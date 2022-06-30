package SSAFY제출;

import java.util.*;
import java.io.*;

class D2스도쿠검증1974
{
    static int N = 9;
    static int map[][];
    public static boolean ischeck(int x, int y, int num){
        //x, y축
        for(int i = 0; i < N; i++){
            if(map[x][i] == num) return false;
            if(map[i][y] == num) return false;
        }      
        //3*3
        for(int i = x / 3 * 3; i < x / 3 * 3 + 3; i++){
            for(int j = y / 3 * 3; j < y / 3 * 3 + 3; j++){
                if(map[i][j] == num)
                    return false;
            }
        }  
        return true;

    }
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            boolean res = true;
            map = new int[N][N];
		
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
             	for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int  temp = map[i][j];
                    map[i][j] = 0;
                    if(!ischeck(i, j, temp)){
                        res = false;                        
                    }
                    map[i][j] = temp;
                }
                
            }
			
	
            System.out.print("#" + test_case + " " );
            System.out.println((res) ? 1 : 0);
            
		}
	}
}
//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Psz16AYEDFAUq&categoryId=AV5Psz16AYEDFAUq&categoryType=CODE&problemTitle=1974&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1