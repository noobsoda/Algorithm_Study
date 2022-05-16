package 코딩테스트공부;

public class L3_2xn타일링12900 {
    static long dp[];
    public static long fibonaci(int n){
        if(dp[n] != 0)
            return dp[n];        
        else if(n == 1 || n == 0)
            return 1;
        else if(n == 2)
            return 2;
        
        dp[n] = fibonaci(n-1) + fibonaci(n-2);
        dp[n] %= 1000000007;
        return dp[n];
    }
    public long solution(int n) {        
        dp = new long[n+1];
        
        
        fibonaci(n);        
        return dp[n];
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/12900#