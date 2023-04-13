package 코딩테스트공부.SWEA.SSAFY제출;

import java.util.*;
import java.io.*;

class D2숫자배열회전1961 {
    static int N;
    static int map[][];
    static int temp90map[][];
    static int temp180map[][];
    static int temp270map[][];

    public static void rotate(int tempmap[][], int map[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempmap[j][N - 1 - i] = map[i][j];
            }
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            temp90map = new int[N][N];
            temp180map = new int[N][N];
            temp270map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            rotate(temp90map, map);
            rotate(temp180map, temp90map);
            rotate(temp270map, temp180map);

            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(temp90map[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(temp180map[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(temp270map[i][j]);
                }
                System.out.println();
            }
        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq&categoryId=AV5Pq-OKAVYDFAUq&categoryType=CODE&problemTitle=1961&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1