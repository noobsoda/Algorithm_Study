package 코딩테스트공부;

public class L2큐 {
    static int N, min = Integer.MAX_VALUE;
    static long sum1, sum2, ave;

    public static void main(String[] args){
        System.out.println(solution(new int[]{1,2,1,2}, new int[]{1,10,1,2}));
    }
    public static void dfs(int depth, long q1, long q2, int[] queue1, int[] queue2){

        if(depth == N || min <= depth){
            return;
        }
        long nq1 = q1;
        long nq2 = q2;
        for(int i = 0; i < N; i++){
            if(min < depth + i + 1)
                break;

            nq1 += queue2[i];
            nq2 -= queue2[i];
            if(nq1 == ave && nq2 == ave)                
                min = Math.min(depth + i+1, min);
        }
        for(int i = 0; i < depth; i++){
            if(min < depth + i + 1 + N)
                break;
            nq1 += queue1[i];
            nq2 -= queue1[i];
            if(nq1 == ave && nq2 == ave)                
                min = Math.min(depth + i+1 + N, min);
        }
        q1 -= queue1[depth];
        q2 += queue1[depth];

        dfs(depth+1, q1, q2, queue1, queue2);
    }
    public static long solution(int[] queue1, int[] queue2) {
        long answer = -1;
        N = queue1.length;
        for(int i = 0; i < N; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        ave = (sum1 + sum2) / 2;
        if((sum1 + sum2) % 2 == 1)
            return -1;

        dfs(0, sum1, sum2, queue1, queue2);
        dfs(0, sum2, sum1, queue2, queue1);

        if(min != Integer.MAX_VALUE)
            answer = min;
    






        
        return answer;
    }
}