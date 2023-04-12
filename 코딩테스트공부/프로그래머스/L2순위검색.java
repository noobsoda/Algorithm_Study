package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L2순위검색 {
    static int num[];
    static boolean visited[];
    static String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
            "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
            "python backend senior chicken 50" };
    static String[] query = { "java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution()));
    }

    public static int[] solution() {

        num = new int[info.length];
        int[] answer = new int[query.length];
        visited = new boolean[info.length];

        Arrays.fill(answer, info.length);

        for (int i = 0; i < info.length; i++) {
            String nv[] = info[i].split(" ");
            num[i] = Integer.parseInt(nv[4]);
        }

        for (int i = 0; i < query.length; i++) {
            String nv[] = query[i].split(" ");
            int cnt = 0;
            Arrays.fill(visited, true);

            for (int j = 0; j < nv.length; j++) {
                if (nv[j].equals("and") || nv[j].equals("-")) {
                    continue;
                }
                if (j == nv.length - 1) {
                    for (int k = 0; k < info.length; k++) {
                        if (Integer.parseInt(nv[j]) <= num[k])
                            continue;
                        visited[k] = false;
                    }
                    continue;
                }
                for (int k = 0; k < info.length; k++) {
                    if (info[k].contains(nv[j]))
                        continue;
                    visited[k] = false;
                }

            }
            for (int j = 0; j < visited.length; j++) {

                if (visited[j])
                    cnt++;
            }
            answer[i] = cnt;
        }

        return answer;
    }
}
// https://programmers.co.kr/learn/courses/30/lessons/72412