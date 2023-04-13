package 코딩테스트공부.SWEA;

import java.util.*;
import java.io.*;

public class 모역_보물상자비밀번호5658 {
    static HashMap<String, Integer> map;
    static int N, K;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            map = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            String treasure = st.nextToken();

            // 문자열 끝 길이 d만큼 늘려주기
            StringBuilder sb = new StringBuilder();
            sb.append(treasure);

            int d = N / 4;
            for (int i = 0; i < d; i++) {
                sb.append(treasure.charAt(i));
            }
            treasure = sb.toString();

            // 한 칸씩 옆으로 이동하면서 N/4칸씩 자르기
            sb = new StringBuilder();
            for (int n = 0; n < d; n++) {
                int cnt = 0;
                for (int i = n; i < N + n; i++) {
                    cnt++;
                    sb.append(treasure.charAt(i));
                    if (cnt % d == 0) {
                        String s = sb.toString();
                        map.put(s, 1);
                        sb = new StringBuilder();
                    }
                }
            }

            String list[] = new String[map.size()];
            int cnt = 0;
            for (String s : map.keySet()) {
                list[cnt] = s;
                cnt++;
            }
            Arrays.sort(list, Collections.reverseOrder());

            System.out.println("#" + test_case + " " + Integer.parseInt(list[K - 1], 16));

        }
    }
}
// https://swexpertacademy.com/main/solvingProblem/solvingProblem.do