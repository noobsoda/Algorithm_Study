package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2캐시17680 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> q = new ArrayDeque<>();
        for (String s : cities) {
            boolean flag = false;
            s = s.toUpperCase();

            for (String qs : q) {
                if (qs.equals(s)) {
                    flag = true;
                }
            }
            if (!flag) {
                answer += 5;
                if (q.size() >= cacheSize) {
                    q.poll();
                }

            } else {
                answer++;
                q.remove(s);
            }
            if (q.size() < cacheSize) {
                q.add(s);
            }
        }
        System.out.println(q);

        return answer;
    }
}