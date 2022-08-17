package 코딩테스트공부;
import java.io.*;
import java.util.*;
public class S1_Z_1074 {
    static int N, R, C, cnt;

    public static void merge(int r, int c, int n){

        if(n == 1){            
            System.out.println(cnt);    
            return;
        }
        
        if(R < r + n/2 && C < c + n/2){
            merge(r,c,n/2);
        }
        if(R < r + n/2 && C >= c + n/2){
            cnt +=(n*n)/4;
            merge(r, c+n/2, n/2);
        }
        if(R >= r + n/2 && C < c + n/2){
            cnt +=(n*n)/4*2;
            merge(r+n/2, c, n/2);
        }
        if(R >= r + n/2 && C >= c + n/2){
            cnt +=(n*n)/4*3;
            merge(r+n/2, c+n/2, n/2);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        N = 1 << N;

        merge(0, 0, N);
    }
}
//https://www.acmicpc.net/problem/1074