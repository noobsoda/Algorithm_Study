package 코딩테스트공부;

import java.util.*;

public class 카모2 {
    static HashMap<String, Integer> hmap;
    static HashSet<String> hset;

    public static void main(String[] args) {
        String[] id_list = { "A B C D", "A D", "A B D", "B D" };
        int k = 2;
        solution(id_list, k);
    }

    public static int solution(String[] id_list, int k) {
        int answer = 0;

        hmap = new HashMap<>();

        StringTokenizer st;
        for (String s : id_list) {
            st = new StringTokenizer(s);
            hset = new HashSet<>();

            while (st.hasMoreTokens()) {
                String now = st.nextToken();

                if (hset.contains(now)) {
                    continue;
                }
                hset.add(now);

                if (hmap.containsKey(now)) {
                    int n = hmap.get(now);
                    if (n >= k) {
                        continue;
                    }
                    hmap.put(now, n + 1);
                    answer++;
                } else {
                    hmap.put(now, 1);
                    answer++;
                }

            }

        }
        System.out.println(answer);
        return answer;
    }
}
