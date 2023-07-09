package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5무한수열1351 {
    static Map<Long, Long> hMap = new HashMap<>();
    static int P, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        System.out.println(solve(n));

    }

    static long solve(long num) {

        if (num == 0)
            return 1;
        if (hMap.containsKey(num))
            return hMap.get(num);

        long a = (long) Math.floor(num / P);
        long b = (long) Math.floor(num / Q);

        hMap.put(num, solve(a) + solve(b));
        return hMap.get(num);
    }
}
// https://www.acmicpc.net/problem/1351