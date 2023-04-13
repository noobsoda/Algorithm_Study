package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S2DNA비밀번호12891 {
    static int P, S, res;
    static int acgt[];
    static char map[];
    static char acgtmap[] = { 'A', 'C', 'G', 'T' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        map = new char[P];
        acgt = new int[4];
        String nv = br.readLine();
        for (int i = 0; i < P; i++) {
            map[i] = nv.charAt(i);
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            acgt[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 슬라이딩 윈도우 파악
        int left = 0;
        int right = S - 1;

        int cnt = 0;
        for (int i = right; i >= left; i--) {
            for (int j = 0; j < 4; j++) {
                if (map[i] == acgtmap[j]) {
                    cnt = j;
                    break;
                }

            }
            acgt[cnt]--;
        }

        if (acgt[0] <= 0 && acgt[1] <= 0 && acgt[2] <= 0 && acgt[3] <= 0)
            res++;

        // 오른쪽 한칸 늘리고 왼쪽 한칸 줄여서 탐색
        while (right < P) {
            right++;
            left++;

            if (right >= P)
                break;

            acgt[cnt]++;
            for (int i = 0; i < 4; i++) {
                if (map[right] == acgtmap[i]) {
                    acgt[i]--;
                }
                if (map[left] == acgtmap[i]) {
                    cnt = i;
                }
            }
            if (acgt[0] <= 0 && acgt[1] <= 0 && acgt[2] <= 0 && acgt[3] <= 0)
                res++;

        }

        System.out.println(res);
    }
}
// https://www.acmicpc.net/problem/12891