package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4전화번호목록5052 {
    static int T, N;
    static HashSet<String> hset;
    static String lines[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            lines = new String[N];
            hset = new HashSet<>();

            for (int i = 0; i < N; i++) {
                String s = br.readLine();

                lines[i] = s;

            }

            Arrays.sort(lines);

            boolean flag = false;
            for (int i = 0; i < N - 1; i++) {
                int l = lines[i].length();
                if (l < lines[i + 1].length()) {
                    String str = lines[i + 1].substring(0, l);
                    if (str.equals(lines[i])) {
                        flag = true;
                        break;
                    }
                }

            }
            String s = "";
            if (flag)
                s = "NO";
            else
                s = "YES";

            System.out.println(s);
        }

    }
}
// https://www.acmicpc.net/problem/5052