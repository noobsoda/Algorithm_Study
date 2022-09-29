package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class 정올_회전초밥2577 {
    static int N, D, K, C, max;
    static int map[];
    static HashMap<Integer, Integer> hmap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hmap = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N];

        int sum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = Integer.parseInt(st.nextToken());

            if (i < K) {

                if (hmap.containsKey(map[i])) {
                    int n = hmap.get(map[i]);
                    hmap.put(map[i], n + 1);
                } else {
                    hmap.put(map[i], 1);
                }

            }
        }
        if (!hmap.containsKey(C))
            sum++;

        sum += hmap.size();
        max = Math.max(sum, max);

        // 슬라이딩 윈도우
        for (int i = 0; i < N; i++) {
            sum = 0;

            // 첫번째 요소 빼기
            int startcnt = hmap.get(map[i]);
            if (startcnt > 1) {
                hmap.put(map[i], startcnt - 1);
            } else {
                hmap.remove(map[i]);
            }

            // 마지막 요소 넣기
            if (hmap.containsKey(map[(i + K) % N])) {
                int endcnt = hmap.get(map[(i + K) % N]);
                hmap.put(map[(i + K) % N], endcnt + 1);
            } else {
                hmap.put(map[(i + K) % N], 1);
            }
            sum = hmap.size();

            if (!hmap.containsKey(C))
                sum++;

            // 최댓종류 수 구하기
            max = Math.max(sum, max);
        }
        System.out.println(max);

    }
}
// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1838&sca=99&sfl=wr_hit&stx=2577
// 8 30 4 30
// 2
// 3
// 4
// 4
// 2
// 3
// 4
// 4
