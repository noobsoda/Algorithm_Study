package 코딩테스트공부.코테;

import java.util.*;

class KT저울추 {
    static int arr[], max;
    static boolean visit[];

    public int[] solution(int n, String[] comparison) {
        int[] answer = {};
        List<Integer> res = new ArrayList<>();
        arr = new int[n + 1];
        visit = new boolean[n + 1];
        for (String s : comparison) {
            if (s.contains("=")) {
                equalsValue(s);
            } else if (s.contains("<")) {
                biggerValue(s, false);
            } else {
                biggerValue(s, true);
            }

        }
        for (int i = 1; i <= n; i++) {
            if (visit[i])
                continue;
            max = Math.max(Math.abs(arr[i]), max);
        }
        for (int i = 1; i <= n; i++) {
            if (visit[i] || Math.abs(arr[i]) != max)
                continue;
            res.add(i);
        }
        answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }

    public void equalsValue(String s) {
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            if (Character.isDigit(tmp.charAt(0))) {
                int n = Integer.parseInt(tmp);
                visit[n] = true;
            }

        }
    }

    public void biggerValue(String s, boolean flag) {
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            String tmp = st.nextToken();
            if (!Character.isDigit(tmp.charAt(0))) {
                flag = !flag;
                continue;
            }
            int n = Integer.parseInt(tmp);
            // 큰 애들 더하기
            if (!flag) {
                arr[n]++;
            }
            // 작은 애들 빼기
            else {
                arr[n]--;
            }

        }
    }
}