package 코딩테스트공부;

import java.util.*;

class LG코드몬스터3 {
    static HashMap<String, Integer> hmap;
    static int max = 0;

    public static void dfs(int start, String reference, String track, int min) {
        if (start == track.length()) {
            max = Math.max(min, max);
            return;
        }

        for (int i = reference.length() - 1; i >= 1; i--) {
            if (start + i > track.length())
                continue;

            String s = track.substring(start, start + i);
            if (hmap.containsKey(s)) {
                if (max > i)
                    continue;

                dfs(start + i, reference, track, Math.min(min, i));
            }
        }
    }

    public int solution(String reference, String track) {

        StringBuilder sb = new StringBuilder();
        hmap = new HashMap<>();

        for (int i = 0; i < reference.length(); i++) {
            sb = new StringBuilder();
            for (int j = i; j < reference.length(); j++) {
                sb.append(reference.charAt(j));

                hmap.put(sb.toString(), sb.toString().length());

            }
        }
        dfs(0, reference, track, Integer.MAX_VALUE);

        return max;
    }
}