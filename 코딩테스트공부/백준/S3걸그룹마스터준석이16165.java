package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S3걸그룹마스터준석이16165 {
    static int N, M;
    static Map<String, String> nameMap;
    static Map<String, List<String>> groupMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nameMap = new HashMap<>();
        groupMap = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String groupName = br.readLine();
            List<String> group = new ArrayList<>();
            int groupCount = Integer.parseInt(br.readLine());
            for (int j = 0; j < groupCount; j++) {
                String name = br.readLine();
                group.add(name);
                nameMap.put(name, groupName);
            }
            groupMap.put(groupName, group);
        }
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            int quiz = Integer.parseInt(br.readLine());

            switch (quiz) {
                case 0:
                    List<String> group = groupMap.get(name);
                    Collections.sort(group);
                    for (String girlName : group) {
                        bw.write(girlName + "\n");
                    }
                    break;
                case 1:
                    bw.write(nameMap.get(name) + "\n");
                    break;
            }
        }
        bw.flush();

    }

}
// https://www.acmicpc.net/problem/16165