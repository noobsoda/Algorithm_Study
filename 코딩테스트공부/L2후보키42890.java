package 코딩테스트공부;

import java.util.*;

class L2후보키42890 {
    static int N, M;
    static HashSet<String> hres;
    static boolean visited[];

    public static boolean Check(String[] relation) {
        HashSet<String> hset = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (hset.contains(relation[i])) {
                return false;
            }
            hset.add(relation[i]);
        }

        return true;
    }

    public static void dfs(String[][] relation, int start, int depth, int end) {
        if (depth == end) {
            StringBuilder sb;
            String r[] = new String[N];

            // 문자열 더해서 hash로 비교
            for (int i = 0; i < N; i++) {
                sb = new StringBuilder();
                for (int j = 0; j < M; j++) {
                    if (visited[j]) {
                        sb.append(relation[i][j]);
                    }
                }

                r[i] = sb.toString();
            }

            // 유일성 체크
            if (Check(r)) {
                StringBuilder string = new StringBuilder();
                for (int i = 0; i < M; i++) {
                    // 방문한 곳 인덱스 문자열로 만들기
                    if (visited[i])
                        string.append(i + 1);
                }

                // 최소성 체크
                for (String s : hres) {
                    int cnt = 0;
                    for (int i = 0; i < s.length(); i++) {
                        // hres에 저장된 문자 하나씩 인덱스 문자열과 비교해서 전부 안에 들어있으면 최소성 만족 못 함
                        if (string.toString().contains(String.valueOf(s.charAt(i))))
                            cnt++;
                    }
                    // s의 부분 집합이 hres 안에 있으면 fail
                    if (cnt == s.length())
                        return;
                }
                // 유일성 최소성 만족
                hres.add(string.toString());

            }

        }

        for (int i = start; i < M; i++) {

            visited[i] = true;
            dfs(relation, i + 1, depth + 1, end);
            visited[i] = false;
        }

    }

    public int solution(String[][] relation) {
        N = relation.length;
        M = relation[0].length;
        visited = new boolean[M];
        hres = new HashSet<>();

        // 하나 다 완성 하나는 상관없음
        // 2개 조합해서 2개 최대치 visit
        // ...8개 조합

        for (int i = 1; i <= M; i++) {
            dfs(relation, 0, 0, i);
        }

        return hres.size();
    }
}