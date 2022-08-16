package 자바공부.비트마스킹순열;

public class BitTest {
    static int N;
    static int [] numbers,sel;
    public static void main(String[] args) {
        int i = 982323;
        N = 3;
        numbers = new int[] {1,3,5};
        sel = new int[3];

        System.out.println(Integer.toBinaryString(i));
        int flag = 1 <<4;

        if((i & flag) > 0){
            System.out.println("i의 flag 번째 자리수의 값은 1입니다");
            System.out.println(i&flag);
        }
        GenerateSubset();
        
    }
    public static void GenerateSubset(){
         for(int flag = 0, caseCnt = 1 << N; flag < caseCnt; flag++){
            for(int i = 0; i < N; i++){
                if((flag & (1 << i)) != 0){
                    System.out.print(numbers[i] + " ");
                }
            }
            System.out.println();
         }
        
    }

}
