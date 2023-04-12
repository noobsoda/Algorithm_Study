package 코딩테스트공부.코테;

import java.util.*;

public class LG코드몬스터1 {
    static boolean visited[];
    static int N, diff = Integer.MAX_VALUE, weight;
    static int[] answer;

    public static void Check(int[] sel, int end, int sum) {
        // 구슬 없는거 기준
        for (int i = 1; i < end - 1; i++) {
            // 왼쪽 합
            int left = 0, right = 0;
            int nowdiff = 0;
            for (int j = 0; j < i; j++) {
                left += sel[j];
                nowdiff--;
            }
            // 오른쪽 합
            for (int j = i; j < end; j++) {
                right += sel[j];
                nowdiff++;
            }
            nowdiff = Math.abs(nowdiff);
            if (left == right) {
                if (nowdiff < diff) {
                    answer = sel.clone();
                    diff = nowdiff;
                    weight = sum;
                } else if (nowdiff == diff) {
                    if (weight < sum) {
                        answer = sel.clone();
                        weight = sum;
                    }
                }

            }

        }

        // 구슬 기준
        for (int i = 0; i < end; i++) {
            // 왼쪽 합
            int left = 0, right = 0;
            int nowdiff = 0;
            for (int j = 0; j < i; j++) {
                left += sel[j];
                nowdiff--;
            }
            // 오른쪽 합
            for (int j = i + 1; j < end; j++) {
                right += sel[j];
                nowdiff++;
            }
            nowdiff = Math.abs(nowdiff);

            // System.out.println(left + " " + right);
            if (left == right) {
                if (nowdiff < diff) {
                    answer = sel.clone();
                    diff = nowdiff;
                    weight = sum;
                } else if (nowdiff == diff) {
                    if (weight < sum) {
                        answer = sel.clone();
                        weight = sum;
                    }
                }

            }

        }

        // 무게 합, 좌우 구슬 차이

        // 좌우 구슬 중앙을 어디에 배치하냐 따라서 최종 선택지

    }

    public static void permu(int depth, int end, int[] sel, int[] marbles, int sum) {

        // 무게합 백트래킹
        if (depth == end) {
            Check(sel, end, sum);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;
            sel[depth] = marbles[i];
            visited[i] = true;
            permu(depth + 1, end, sel, marbles, sum + sel[depth]);
            visited[i] = false;

        }
    }

    public int[] solution(int[] marbles) {
        N = marbles.length;
        answer = new int[N];
        visited = new boolean[N];
        Arrays.sort(marbles);

        for (int i = N; i >= 1; i--) {
            permu(0, i, new int[N], marbles, 0);

        }

        // int test[] = new int[]{3, 9, 7, 5};
        // Check(test, 4, 24);

        int last = N;
        for (int i = 0; i < N; i++) {
            if (answer[i] == 0) {
                last = i;
                break;
            }

        }
        int res[] = new int[last];
        for (int i = 0; i < last; i++) {
            res[i] = answer[i];
        }

        return res;
    }
}