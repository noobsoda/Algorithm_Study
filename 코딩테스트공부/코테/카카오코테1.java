package 코딩테스트공부.코테;

import java.io.IOException;
import java.util.*;

public class 카카오코테1 {
    public static void main(String[] args) throws IOException {

    }

    class Solution {
        HashMap<Character, Integer> hmap;
        HashMap<Integer, Integer> cnthmap;

        public int[] solution(String today, String[] terms, String[] privacies) {
            hmap = new HashMap<>();
            cnthmap = new HashMap<>();
            StringTokenizer st;
            StringTokenizer st2;
            int[] answer;

            for (String s : terms) {
                st = new StringTokenizer(s);
                char c = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());
                hmap.put(c, n);
            }

            int cnt = 0;
            for (String s : privacies) {
                cnt++;
                st = new StringTokenizer(s);
                String now = st.nextToken();

                st2 = new StringTokenizer(now, ".");
                int y = Integer.parseInt(st2.nextToken());
                int m = Integer.parseInt(st2.nextToken());
                int d = Integer.parseInt(st2.nextToken());

                char c = st.nextToken().charAt(0);
                int n = hmap.get(c);

                m += n;
                while (m > 12) {
                    y += 1;
                    m -= 12;
                }
                if (d - 1 == 0) {
                    d = 28;
                    if (m - 1 == 0) {
                        y -= 1;
                        m = 12;
                    }
                    m -= 1;
                } else {
                    d -= 1;
                }
                st2 = new StringTokenizer(today, ".");
                int ty = Integer.parseInt(st2.nextToken());
                int tm = Integer.parseInt(st2.nextToken());
                int td = Integer.parseInt(st2.nextToken());

                if (y < ty) {
                    cnthmap.put(cnt, cnt);
                } else if (y == ty) {
                    if (m < tm) {
                        cnthmap.put(cnt, cnt);
                    } else if (m == tm) {
                        if (d < td) {
                            cnthmap.put(cnt, cnt);
                        }
                    }

                }

            }
            answer = new int[cnthmap.size()];
            cnt = 0;
            for (int n : cnthmap.keySet()) {
                int n1 = cnthmap.get(n);
                answer[cnt++] = n1;
            }
            Arrays.sort(answer);

            return answer;
        }
    }
}
