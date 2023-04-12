package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5감소하는숫자1038 {
    static int N;
    static long count = 1;

    public static boolean ischeck(long num) {

        ArrayList<Long> arr = new ArrayList<>();
        while (num > 0) {
            arr.add(num % 10);
            num /= 10;
        }
        if (arr.size() == 1)
            return true;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) >= arr.get(i + 1))
                return false;
        }

        return true;

    }

    public static boolean dfs(int n) {
        if (n == N) {
            return true;
        } else {
            while (n < 1000000) {
                if (ischeck(count)) {
                    count++;
                    if (dfs(n + 1))
                        return true;
                    else
                        return false;
                } else {
                    count++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dfs(0);
        System.out.println(count - 1);

    }
}

// https://www.acmicpc.net/problem/10282