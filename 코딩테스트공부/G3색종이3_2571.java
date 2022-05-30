package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G3색종이3_2571 {
    static int N, max = 0;
    static int map[][];    

      
    public static void acc() {
		for(int i=0;i<99;i++) {
			for(int j=0;j<100;j++) {
				if(map[i][j]!=0 && map[i+1][j]!=0) {
					map[i+1][j] = map[i][j] +1;
				}
			}
		}
	}
	
	public static void sum() {
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				int h = 100;
				
				for(int k=j;k<100;k++) {
					h= Math.min(map[i][k], h);
					if(h==0) break;
					max = Math.max(max, h*(k-j+1));
					
				}
			}
		}
	}
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[101][101];
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int down = Integer.parseInt(st.nextToken());

            for(int a=left;a<left+10;a++)
				for(int b=down;b<down+10;b++) {
					map[a][b] = 1;
				}

        }
        
        acc();
        sum();

        System.out.println(max);

        
        
    }
}
//https://www.acmicpc.net/problem/2571