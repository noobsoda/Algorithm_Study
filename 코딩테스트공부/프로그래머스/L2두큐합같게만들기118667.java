package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2두큐합같게만들기118667 {
    static Queue<Integer> q1, q2;

    public int solution(int[] queue1, int[] queue2) {
        int cnt = 0;
        long sum1 = 0, sum2 = 0;

        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();

        for (int i : queue1) {
            q1.add(i);
            sum1 += i;
        }
        for (int i : queue2) {
            q2.add(i);
            sum2 += i;
        }
        int size = q1.size() * 3;
        while (cnt < size) {
            if (sum1 > sum2) {
                int n = q1.poll();
                q2.add(n);
                sum1 -= n;
                sum2 += n;
            } else if (sum2 > sum1) {
                int n = q2.poll();
                q1.add(n);
                sum2 -= n;
                sum1 += n;

            } else {
                break;
            }
            cnt++;

        }
        if (cnt >= size)
            cnt = -1;

        return cnt;
    }
}
