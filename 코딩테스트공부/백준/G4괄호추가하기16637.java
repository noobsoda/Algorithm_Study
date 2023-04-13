package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4괄호추가하기16637 {
    static int N, max = Integer.MIN_VALUE;
    // static int order[];
    static char oper[];
    static int numbers[];
    static boolean visited[];

    public static int Check(int n1, int n2, char c) {
        int res = 0;
        switch (c) {
            case '+':
                res = n1 + n2;
                break;

            case '-':
                res = n1 - n2;
                break;

            case '*':
                res = n1 * n2;
                break;

        }

        return res;
    }

    public static void Dfs(int start, int depth, int end) {
        if (depth == end) {
            // System.out.println(Arrays.toString(order));
            int tempnum[] = new int[N / 2 + 1];
            System.arraycopy(numbers, 0, tempnum, 0, N / 2 + 1);

            // 괄호 부터 연산
            for (int i = 0; i < N / 2; i++) {
                if (visited[i]) {
                    int n = Check(tempnum[i], tempnum[i + 1], oper[i]);
                    tempnum[i] = n;
                    tempnum[i + 1] = n;
                }
            }

            // 차례대로 연산
            int sum = 0;
            boolean flag = false;
            for (int i = 0; i < N / 2; i++) {
                if (visited[i])
                    continue;
                if (!flag) {
                    sum = Check(tempnum[i], tempnum[i + 1], oper[i]);
                    flag = true;
                    continue;
                }
                sum = Check(sum, tempnum[i + 1], oper[i]);
            }
            max = Math.max(sum, max);
            return;
        }

        for (int i = start; i < N / 2; i++) {
            // order[depth] = i+1;
            visited[i] = true;
            Dfs(i + 2, depth + 1, end);
            visited[i] = false;

        }
        if (start >= N / 2) {
            // order[depth] = 0;
            Dfs(start, depth + 1, end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        oper = new char[N / 2];
        // order = new int[N/2];
        numbers = new int[N / 2 + 1];
        visited = new boolean[N / 2];

        String nv = br.readLine();
        int ncnt = 0;

        // 짝수면 숫자, 홀수면 연산자
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0)
                numbers[ncnt++] = nv.charAt(i) - '0';
            else
                oper[ncnt - 1] = nv.charAt(i);

        }

        // Power Set
        for (int i = 0; i < N / 2; i++)
            Dfs(0, 0, i);

        // N이 1일 때 고려
        if (N == 1)
            max = numbers[0];

        System.out.println(max);

    }
}
// https://www.acmicpc.net/problem/16637
// 파워셋 문제에 약간의 변형 추가
// 히든 테스트 케이스 N이 1일 때