package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3수강신청13414 {
    static int N, M;
    static Map<String, Integer> hMap;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        hMap = new HashMap<>();

        // 해시가 중복 알아서 삭제
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            hMap.put(s, i);

        }
        // 리스트에 넣은 후 정렬
        for (String s : hMap.keySet()) {
            list.add(new Node(hMap.get(s), s));
        }
        Collections.sort(list, (o1, o2) -> o1.idx - o2.idx);
        // N명을 뽑거나 자리가 남으면 다 뽑기
        for (int i = 0; i < N; i++) {
            if (list.size() == i)
                break;
            bw.write(list.get(i).sNum + "\n");
        }
        bw.flush();
    }

    static class Node {
        int idx;
        String sNum;

        public Node(int idx, String sNum) {
            this.idx = idx;
            this.sNum = sNum;
        }

    }

}
// https://www.acmicpc.net/problem/13414
