package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class D3한빈이와SpotMart9229 {
    static int N, M, max = -1;
    static int map[];

    public static void combi(int start, int depth, int sum){
        
        if(depth == 2){
            if(sum > M)
                return;
            max = Math.max(sum, max);
            return;
        }
        for(int i = start; i < N; i++){
            sum += map[i];
            combi(i+1, depth+1, sum);
            sum -= map[i];
        }
    }
    public static void main(String args[]) throws Exception
	{        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));        
		StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++)
		{
            max = -1;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                map[i] = Integer.parseInt(st.nextToken());
            }

            combi(0, 0, 0);

            
            bw.write("#" + test_case + " " + max + "\n");
		}
        bw.flush();
	}

}
