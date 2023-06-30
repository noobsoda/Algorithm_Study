package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S5회사에있는사람7785 {
    static int N;
    static Map<String, String> hMap;
    static List<String> sList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        hMap = new HashMap<>();
        sList = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String s1 = st.nextToken();
            String s2 = st.nextToken();

            hMap.put(s1, s2);
        }

        for (String name : hMap.keySet()) {
            if (hMap.get(name).charAt(0) == 'e') {
                sList.add(name);
            }
        }
        Collections.sort(sList, Comparator.reverseOrder());

        for (String name : sList) {
            bw.write(name + "\n");
        }
        bw.flush();

    }
}
// https://www.acmicpc.net/problem/7785