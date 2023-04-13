package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2양궁대회92342 {

    static int[] answer;
    static int[] list;
    static int maxscore = 0;

    public static void main(String[] args) {
        solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 });
        solution(1, new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 });
        solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 });
    }

    public static void dfs(int n, int[] info, int state, int score) {

        if (score == n) {
            int cnt = 10;
            int nowscore = 0;
            int apechscore = 0;
            for (int i = 0; i < list.length; i++) {
                // 라이언 스코어
                if (list[i] > info[i]) {
                    nowscore += cnt;
                }
                // 어피치 스코어
                else {
                    if (info[i] != 0)
                        apechscore += cnt;
                }
                cnt--;
            }

            // 점수차이
            int minusscore = nowscore - apechscore;

            // 점수차이가 더 큰 경우
            if (maxscore < minusscore) {
                System.arraycopy(list, 0, answer, 0, list.length);
                maxscore = minusscore;
            }
            // 점수차이가 같을 때
            else if (maxscore == minusscore) {
                // 가장 낮은 점수를 더 많이 맞힌 경우
                boolean flag = false;
                for (int i = 10; i >= 0; i--) {
                    if (list[i] > answer[i]) {
                        flag = true;
                        break;
                    } else if (list[i] < answer[i])
                        break;
                }
                if (flag)
                    System.arraycopy(list, 0, answer, 0, list.length);
            }
            return;
        }

        if (state > 10)
            return;

        for (int i = info[state] + 1; i >= 0; i--) {
            int nowscore = score + i;
            if (nowscore > n)
                continue;

            list[state] = i;
            dfs(n, info, state + 1, nowscore);
        }
    }

    public static int[] solution(int n, int[] info) {
        answer = new int[info.length];
        list = new int[info.length];

        maxscore = 0;

        dfs(n, info, 0, 0);
        // 점수차이가 없다면
        if (maxscore == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/92342#