package 자바공부.비트마스킹순열;

import java.util.Arrays;

public class Bit {
    static int N;
    static int input[];
    static int numbers[];

    //flag isSelected를 대신하는 정수
    public static void perm(int cnt, int flag){
        if(cnt == N){
            System.out.println(Arrays.toString(numbers));
        }
        else{
            for(int i = 0; i < N; i++){
                if((flag & 1 << i) != 0)    continue;
                
                numbers[cnt] = input[i];
                perm(cnt+1, flag | 1 << i);
            }
        }
    }
    public static void main(String[] args) {
        N = 3;
        input = new int[3];
        numbers = new int[3];

        input[0] = 1;
        input[0] = 2;
        input[0] = 3;
        perm(0, 0);
    }
}
