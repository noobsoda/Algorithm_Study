package 코딩테스트공부;

import java.util.*;

public class L2압축17684 {
    static HashMap<String, Integer> hmap;
    static int start = 27;

    public int[] solution(String msg) {
        hmap = new HashMap<>();
        Queue<Integer> q = new ArrayDeque<>();

        char c = 'A';
        for (int i = 1; i <= 26; i++) {
            hmap.put(Character.toString(c++), i);
        }

        for (int i = 0; i < msg.length(); i++) {
            StringBuilder sb = new StringBuilder();
            String temp = "";
            for (int j = i; j < msg.length(); j++) {
                sb.append(msg.charAt(j));
                if (!hmap.containsKey(sb.toString())) {

                    break;
                } else {
                    temp = sb.toString();
                    i = j;
                }

            }
            // temp에는 마지막 출력이 저장되있고
            q.add(hmap.get(temp));

            // sb에는 사전 추가가 저장되있다.

            hmap.put(sb.toString(), start++);

        }
        System.out.println(q);

        int[] answer = new int[q.size()];
        int cnt = 0;
        while (!q.isEmpty()) {
            answer[cnt++] = q.poll();
        }
        return answer;
    }
}