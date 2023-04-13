package 코딩테스트공부.코테;

import java.util.*;

public class O주식종목맞추기 {
    static Map<String, Integer> kMap;
    static Map<String, Integer> uMap;
    static Map<String, Integer> hMap;

    public int solution(String[] kor, String[] usa, String[] incs) {
        int max = 0;

        kMap = new HashMap<>();
        uMap = new HashMap<>();
        hMap = new HashMap<>();

        for (String k : kor) {
            kMap.put(k, 1);
        }
        for (String u : usa) {
            uMap.put(u, 2);
        }
        for (int i = 0; i < incs.length; i++) {
            makePair(incs[i]);
        }
        for (String key : hMap.keySet()) {
            max = Math.max(max, hMap.get(key));
        }
        return max;
    }

    private static void makePair(String input) {
        String s[] = input.split(" ");

        for (int i = 0; i < s.length; i++) {
            for (int j = i + 1; j < s.length; j++) {
                String kor = "";
                String usa = "";

                // 둘다 kor이거나 usa이면 X
                if (kMap.containsKey(s[i])) {
                    if (kMap.containsKey(s[j])) {
                        continue;
                    }
                }
                if (uMap.containsKey(s[i])) {
                    if (uMap.containsKey(s[j])) {
                        continue;
                    }
                }
                // kor과 usa 값 불러오기
                if (kMap.containsKey(s[i])) {
                    kor = s[i];
                } else {
                    kor = s[j];
                }
                if (uMap.containsKey(s[i])) {
                    usa = s[i];
                } else {
                    usa = s[j];
                }
                StringBuilder sb = new StringBuilder();
                sb.append(kor + " " + usa);
                String res = sb.toString();
                if (hMap.containsKey(res)) {
                    hMap.put(res, hMap.get(res) + 1);
                } else {
                    hMap.put(res, 1);
                }
            }
        }
    }
}
