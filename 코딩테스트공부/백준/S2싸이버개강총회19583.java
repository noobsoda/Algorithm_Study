package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class S2싸이버개강총회19583 {
    static int S, E, Q, res;
    static Map<String, Node> hMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hMap = new HashMap<>();

        S = calcNum(st.nextToken());
        E = calcNum(st.nextToken());
        Q = calcNum(st.nextToken());
        String input;
        while ((input = br.readLine()) != null && input.length() != 0) {
            st = new StringTokenizer(input);
            int time = calcNum(st.nextToken());
            String name = st.nextToken();
            calcTime(time, name);

        }

        for (String s : hMap.keySet()) {
            Node node = hMap.get(s);
            if (node.start != -1 && node.end != -1) {
                res++;
            }
        }
        System.out.println(res);

    }

    public static void calcTime(int time, String name) {
        Node node = new Node();
        if (time <= S) {
            node.start = time;
            hMap.put(name, node);
        } else if (time >= E && time <= Q) {
            if (hMap.containsKey(name)) {
                node = hMap.get(name);
                node.end = time;
                hMap.put(name, node);
            }
            // 시작시간에 채팅 안 친경우는 배제
        }
    }

    public static int calcNum(String s) {
        StringTokenizer st = new StringTokenizer(s, ":");
        int time = Integer.parseInt(st.nextToken()) * 60;
        time += Integer.parseInt(st.nextToken());

        return time;
    }

    static class Node {
        int start, end;

        public Node() {
            start = -1;
            end = -1;
        }

    }
}
// https://www.acmicpc.net/problem/19583