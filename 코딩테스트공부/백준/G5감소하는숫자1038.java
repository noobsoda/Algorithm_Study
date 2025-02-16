package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5감소하는숫자1038 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if (N > 1022) { // 감소하는 수의 최대 개수는 1023개
            System.out.println(-1);
            return;
        }

        ArrayList<Long> list = new ArrayList<>();
        Queue<Long> queue = new LinkedList<>();

        // 한 자리 감소하는 수 (0부터 9까지)
        for (int i = 0; i <= 9; i++) {
            queue.add((long) i);
            list.add((long) i);
        }

        while (!queue.isEmpty()) {
            long num = queue.poll();
            long lastDigit = num % 10;

            for (long i = 0; i < lastDigit; i++) {
                long nextNum = num * 10 + i;
                queue.add(nextNum);
                list.add(nextNum);
            }
        }

        System.out.println(list.get(N));
    }
}

// https://www.acmicpc.net/problem/10282