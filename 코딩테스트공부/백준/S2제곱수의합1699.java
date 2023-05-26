package 코딩테스트공부.백준;

import java.io.*;

public class S2제곱수의합1699 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        System.out.println(lagrange(N));

    }

    public static int lagrange(int n) {
        // 0단계: n이 완전제곱수인 경우, 1을 반환합니다.
        if (Math.floor(Math.sqrt(n)) == Math.sqrt(n)) {
            return 1;
        }

        // 1단계: n이 4의 배수인 경우 4로 나눕니다.
        while (n % 4 == 0) {
            n = n / 4;
        }

        // 2단계: n mod 8이 7인 경우, n은 3개의 제곱수의 합으로 표현됩니다.
        if (n % 8 == 7) {
            return 4;
        }

        // 3단계: n이 두 제곱수의 합으로 표현될 수 있는지 확인합니다.
        for (int i = 0; i <= Math.sqrt(n); i++) {
            int j = (int) Math.sqrt(n - i * i);
            if (j * j == n - i * i) { // j가 완전제곱수인 경우
                return 2; // n이 두 제곱수의 합으로 표현되므로 2를 반환합니다.
            }
        }

        // 4단계: 위의 검사가 실패한 경우, n은 3 또는 4개의 제곱수의 합으로 표현될 수 있습니다.
        return 3;
    }
}