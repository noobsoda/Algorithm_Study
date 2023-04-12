package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2수식최대화67257 {
    static boolean visited[];
    static char oper[] = { '*', '+', '-' };
    static long max = 0;
    static char map[];

    public static long check(long n, long v, int depth) {
        if (map[depth] == '*')
            n *= v;
        else if (map[depth] == '-')
            n -= v;
        else if (map[depth] == '+')
            n += v;

        return n;
    }

    public static void main(String[] args) {

        System.out.println(solution("0*2+1"));
    }

    public static void combi(int depth, String expression) {
        if (depth == 3) {
            long res = -1;

            StringTokenizer a = new StringTokenizer(expression, Character.toString(map[0]));

            while (a.hasMoreTokens()) {

                StringTokenizer b = new StringTokenizer(a.nextToken(), Character.toString(map[1]));
                long two = -1;
                while (b.hasMoreTokens()) {
                    long three = -1;
                    StringTokenizer c = new StringTokenizer(b.nextToken(), Character.toString(map[2]));

                    while (c.hasMoreTokens()) {
                        if (three == -1) {
                            three = Integer.parseInt(c.nextToken());
                            continue;
                        }
                        three = check(three, Long.parseLong(c.nextToken()), 2);
                    }
                    if (two == -1) {
                        two = three;
                        continue;
                    }
                    two = check(two, three, 1);

                }
                if (res == -1) {
                    res = two;
                    continue;
                }
                res = check(res, two, 0);

            }
            res = Math.abs(res);
            max = Math.max(res, max);

            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            map[depth] = oper[i];
            combi(depth + 1, expression);
            visited[i] = false;
        }

    }

    public static long solution(String expression) {
        long answer = 0;
        visited = new boolean[3];
        map = new char[3];

        combi(0, expression);
        answer = max;
        return answer;
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/67257