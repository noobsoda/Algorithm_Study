package 자바공부.비트마스킹순열;

import java.util.Arrays;

public class BitTest {
    static int [] arr,sel;
    public static void main(String[] args) {
        int i = 982323;
        arr = new int[] {1,3,5};
        sel = new int[3];

        System.out.println(Integer.toBinaryString(i));
        int flag = 1 <<4;

        if((i & flag) > 0){
            System.out.println("i의 flag 번째 자리수의 값은 1입니다");
            System.out.println(i&flag);
        }

        perm(0, 0);
        
    }
    public static void perm(int idx, int bit){
        if(idx == arr.length){
            System.out.println(Arrays.toString(sel));
            return;
        }   
        
        for(int i = 0; i < arr.length; i++){
            if((bit & 1 << i) != 0)    continue;
            
            sel[idx] = arr[i];
            perm(idx+1, bit | 1 << i);
        }
            
        
    }

}
