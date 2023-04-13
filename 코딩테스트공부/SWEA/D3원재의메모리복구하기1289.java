package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

class D3원재의메모리복구하기1289 {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            int res = 0;
            String nv = br.readLine();
            char c[] = new char[nv.length()];
            Arrays.fill(c, '0');

            for (int i = 0; i < nv.length(); i++) {
                if (nv.charAt(i) != c[i]) {
                    for (int j = i; j < nv.length(); j++) {
                        c[j] = nv.charAt(i);
                    }
                    res++;
                }
            }

            System.out.println("#" + test_case + " " + res);
        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN