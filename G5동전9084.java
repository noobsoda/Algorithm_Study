import java.io.*;

public class G5동전9084 {
    static int T, N, M;
    static int a[];
    static int dp[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++){
            N = Integer.parseInt(br.readLine());
            a = new int[N+1];
            String nv[] = br.readLine().split(" ");
            for(int j = 1; j <= N; j++){
                a[j] = Integer.parseInt(nv[j-1]);                
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[M+1];           
            
            
            dp[0] = 1;
            
            for(int j = 1; j <= N; j++){
                //a[j]부터 M까지 경우의 수 더하기
                for(int k = a[j]; k <= M; k++){
                    dp[k] += dp[k-a[j]];
                }
            }
            System.out.println(dp[M]);

        }


        
    }
}


//https://www.acmicpc.net/problem/9084