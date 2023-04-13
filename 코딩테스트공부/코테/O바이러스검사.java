package 코딩테스트공부.코테;

import java.util.*;

@SuppressWarnings("unchecked")
public class O바이러스검사 {
    static int size = 1;
    static List<Integer> list[];
    static Map<String, Integer> hMap;
    static Map<Integer, String> reverseHMap;
    static Map<String, Integer> fileMap;
    static Map<String, Integer> exceptMap;
    static boolean visited[];
    static int answer[];

    public int[] solution(String[][] folders, String[][] files, String selected[], String excepted[]) {
        answer = new int[2];
        size += folders.length + files.length;
        list = new ArrayList[size];
        visited = new boolean[size];

        hMap = new HashMap<>();
        reverseHMap = new HashMap<>();
        fileMap = new HashMap<>();
        exceptMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            list[i] = new ArrayList<>();
        }
        int cnt = 0;
        // 이름 변환
        for (String[] folder : folders) {
            if (!hMap.containsKey(folder[0])) {
                hMap.put(folder[0], cnt++);
                reverseHMap.put(cnt - 1, folder[0]);
            }
            if (!hMap.containsKey(folder[1])) {
                hMap.put(folder[1], cnt++);
                reverseHMap.put(cnt - 1, folder[1]);
            }
        }
        // 파일 이름 변환, 용량 넣기
        for (String[] file : files) {
            if (!hMap.containsKey(file[0])) {
                hMap.put(file[0], cnt++);
                reverseHMap.put(cnt - 1, file[0]);
            }
            int res = 0;
            if (file[1].endsWith("KB")) {
                res = Integer.parseInt(file[1].replace("KB", ""));
                res *= 1024;
            } else if (file[1].endsWith("B")) {
                res = Integer.parseInt(file[1].replace("B", ""));
            }
            fileMap.put(file[0], res);
        }

        // except 추가
        for (String except : excepted) {
            exceptMap.put(except, 1);
        }
        for (String[] folder : folders) {
            int end = hMap.get(folder[0]);
            int start = hMap.get(folder[1]);

            list[start].add(end);
        }
        for (String[] file : files) {
            int end = hMap.get(file[0]);
            int start = hMap.get(file[2]);

            list[start].add(end);
        }
        // bfs
        for (String select : selected) {
            bfs(hMap.get(select));
        }

        return answer;
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int temp : list[now]) {
                String s = reverseHMap.get(temp);

                // 제외 폴더나 방문한 곳이면 건들지 않기
                if (exceptMap.containsKey(s) || visited[temp])
                    visited[temp] = true;

                if (fileMap.containsKey(s)) {
                    answer[0] += fileMap.get(s);
                    answer[1]++;
                } else {
                    q.add(temp);
                }
            }
        }
    }

}
