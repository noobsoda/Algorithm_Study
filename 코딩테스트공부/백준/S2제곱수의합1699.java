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

        // 2단계: Gauss의 세 제곱수의 추측의 반례에 따라서 어떤 수를 8로 나눴을 때 나머지가 7인 경우는 4제곱이다
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

        // 4단계: 위의 검사가 실패한 경우, 세 제곱으로 나누어 떨어진다.
        return 3;
    }
}