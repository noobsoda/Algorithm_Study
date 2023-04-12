package 코딩테스트공부.SWEA.SSAFY제출;

import java.util.*;
import java.io.*;

class D2두개의숫자열1959 {
    static int A, B, max = 0;
    static int aMap[], bMap[];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            max = 0;
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            aMap = new int[A];
            bMap = new int[B];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < A; i++) {
                aMap[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < B; i++) {
                bMap[i] = Integer.parseInt(st.nextToken());
            }

            int diff = Math.abs(A - B);

            if (A > B) {
                for (int i = 0; i <= diff; i++) {
                    int sum = 0;
                    for (int j = i; j < B + i; j++) {
                        sum += aMap[j] * bMap[j - i];
                    }
                    max = Math.max(max, sum);
                }
            } else if (A < B) {
                for (int i = 0; i <= diff; i++) {
                    int sum = 0;
                    for (int j = i; j < A + i; j++) {
                        sum += bMap[j] * aMap[j - i];
                    }
                    max = Math.max(max, sum);
                }
            } else {
                int sum = 0;
                for (int i = 0; i < A; i++) {
                    sum += aMap[i] * bMap[i];

                }
                max = Math.max(max, sum);
            }

            System.out.println("#" + test_case + " " + max);
        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpoFaAS4DFAUq&categoryId=AV5PpoFaAS4DFAUq&categoryType=CODE&problemTitle=1959&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1