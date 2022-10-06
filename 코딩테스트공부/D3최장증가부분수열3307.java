package 코딩테스트공부;

import java.util.*;
import java.io.*;

public class D3최장증가부분수열3307 {
    static int N;
    static List<Integer> list;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            list = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                if (list.isEmpty()) {
                    list.add(n);
                } else {
                    if (list.get(list.size() - 1) < n) {
                        list.add(n);
                    } else if (list.get(list.size() - 1) > n) {
                        boolean flag = false;
                        for (int j = list.size() - 1; j >= 0; j--) {
                            if (list.get(j) < n) {
                                list.set(j + 1, n);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            list.set(0, n);
                        }
                    }
                }

            }

            System.out.println("#" + test_case + " " + list.size());

        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBOKg-a6l0DFAWr