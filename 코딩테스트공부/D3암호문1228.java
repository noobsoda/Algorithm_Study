package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class D3암호문1228 {
    static List<String> list;
    public static void main(String args[]) throws Exception
	{        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));        
		StringTokenizer st;

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            list = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                list.add(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < S; i++){
                List<String> temp = new LinkedList<>();
                st.nextToken();
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                for(int j = 0; j < y; j++)
                    temp.add(st.nextToken());

                list.addAll(x, temp);
            }

            bw.write("#" + test_case + " ");
            for(int i = 0; i < 10; i++)
                bw.write(list.get(i) + " ");
			
            bw.write("\n");
		}
        bw.flush();
	}


}
