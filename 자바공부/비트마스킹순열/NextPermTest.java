package 자바공부.비트마스킹순열;

import java.util.Arrays;
import java.util.Scanner;

public class NextPermTest {
    static int N;
    static int input[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] input = new int[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        //전처리 : 순열에 쓰일 수들을 오름차순 정렬
        Arrays.sort(input);
        do{
            System.out.println(Arrays.toString(input));

        }while(np(input));
        sc.close();

    }

    public static boolean np(int[] numbers){// numbers 배열의 상태를 근거로 다음 순열 생성, 다음 순열 존재하면 true, 아니면 false
        // 1. 꼭대기 찾기
        int N = numbers.length;
        int i = N-1;
        while(i>0 &&numbers[i-1] >= numbers[i]) --i;

        if(i == 0)  return false;
        // 2. 꼭대기의 바로 앞자리(i-1)의 값을 크게 만들 교환 값 뒤쪽에서 찾기
        int j = N-1;
        while(numbers[i-1] >= numbers[j]) --j;

        // 3. i-1의 위치값과 j 위치값 교환
        swap(numbers, i-1, j);

        // 4. i위치부터 맨뒤까지의 수열을 가장 작은 형태의 오름차순으로 변경
        int k = N-1;
        while(i < k)    swap(numbers, i++, k--);

        return true;
    }

    public static void swap(int numbers[], int i, int j){
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;

    }
}
//넥퍼1 : nPn 순열 10P10정도로 기존재귀 순열 시간 얼마나 걸리는지 넥퍼순열 시간차이 확인

//숙제2 : 넥퍼 활용해서 조합 해봐라