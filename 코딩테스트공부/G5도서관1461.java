package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G5도서관1461 {
    static int N, M, result = 0;
    static int left = 0, right;
    static int nowl, nowr;
    static int map[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            map[i] = Integer.parseInt(st.nextToken());
            if(map[i] < 0)
                left++;
        }
        map[N] = 0;
        right = N - left;
        Arrays.sort(map);

        nowl = left;
        nowr = left;
        //왼쪽 오른쪽 우선순위 정해야 할까?
        if(left % M != 0){
            result += Math.abs(map[left - (left % M)])*2;
            nowl -= left % M;
        }
        if(right % M != 0){
            result += map[(left) + (right % M)]*2;
            nowr += right % M;
        }        

        //while문 써서 왼쪽 오른쪽 더 작은곳 가기
        while(nowl != 0 || nowr != N){
            int l, r;
            if(nowl - M < 0)    l = 10001;
            else{
                l = Math.abs(map[nowl - M]);                
            }
            if(nowr + M > N)    r = 10001;
            else{
                r = map[nowr + M];                
            }

            if(l < r){
                nowl -= M;
                result += l*2;
            }
            else{
                nowr += M;
                result += r*2;
            }            
        }
        //다시 0으로 돌아올 필요 없으나 제일 큰 값을 마지막에 가니 제일 큰 값 빼기

        int max = Math.max(Math.abs(map[0]), map[N]);
        result -= max;


        System.out.println(result);
        
    }
}
//https://www.acmicpc.net/problem/1461
