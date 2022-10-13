package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class SW조합5607 {
    static int N, R;
    static final int MAX = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            // 분자 N!
            long numer = factorial(N);

            // 분모 (K! * (N-K)!) mod p
            long denom = factorial(R) * factorial(N - R) % MAX;

            System.out.println("#" + test_case + " " + numer * pow(denom, MAX - 2) % MAX);

        }

    }

    // 팩토리얼 함수
    public static long factorial(long N) {
        long fac = 1L;

        while (N > 1) {
            fac = (fac * N) % MAX;
            N--;
        }
        return fac;
    }

    /*
     * 역원 구하는 함수
     * 
     * base : 밑, expo = 지수 (exponent)
     * 
     * 거듭 제곱을 분할 정복 방식으로 푼다.
     * 
     */
    public static long pow(long base, long expo) {
        long result = 1;

        while (expo > 0) {

            // 지수가 홀 수면 반환하고자 하는 result에 곱해주도록 한다.
            // 지수가 모두 짝수라면 expo가 1이 될 떄까지 base의 값이 제곱되다가 최종적으로 result에 담긴다.
            if (expo % 2 == 1) {
                result *= base;
                result %= MAX;
            }
            base = (base * base) % MAX;
            expo /= 2;
        }
        return result;
    }
}
