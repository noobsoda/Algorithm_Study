package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L3셔틀버스17678 {
    static PriorityQueue<Node> pq;

    public static String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        pq = new PriorityQueue<>();

        for (String s : timetable) {
            pq.add(new Node(s));
        }

        int nowtime = 900;

        for (int i = 0; i < n; i++) {
            int cnt = 0;

            while (!pq.isEmpty()) {
                String crew = pq.poll().time;

                String match = "[^0-9]";
                String crewtime = crew.replaceAll(match, "");

                int crewn = Integer.parseInt(crewtime);

                if (crewn <= nowtime) {
                    cnt++;

                } else {
                    // 차 이미 떠났슈
                    pq.add(new Node(crew));
                    break;
                }

                if (cnt == m) {
                    // 딱 너까지만 타

                    // 막차인데 cnt가 m에 도달하면 마지막놈보다 1분 빨리와
                    if (i == n - 1) {
                        // -1됬을때 99되면 시간 줄이는것도 포함시키셈
                        if ((crewn - 1) % 100 >= 60) {
                            crewn -= 41;
                        } else {
                            crewn -= 1;
                        }

                        answer = Integer.toString(crewn);
                    }
                    break;
                }

            }
            // 만약 막차고 cnt가 m에 아직 도달 못했으면 딱 맞춰서 오라고
            if (i == n - 1 && cnt < m) {
                answer = Integer.toString(nowtime);
            }
            // 다음 차까지 시간 보내기
            nowtime += t;

            // 60분 이상이면 40 더하셈
            if (nowtime % 100 >= 60) {
                nowtime += 40;
            }

        }
        StringBuilder sb = new StringBuilder();
        if (answer.length() < 4) {
            for (int i = 0; i < 4 - answer.length(); i++) {
                sb.append(0);
            }
        }
        for (int i = 0; i < answer.length(); i++) {
            sb.append(answer.charAt(i));
        }
        sb.insert(2, ":");
        answer = sb.toString();

        return answer;
    }

    static class Node implements Comparable<Node> {
        String time;

        public Node(String time) {
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            String match = "[^0-9]";
            String o1time = time.replaceAll(match, "");
            String o2time = o.time.replaceAll(match, "");

            int o1n = Integer.parseInt(o1time);
            int o2n = Integer.parseInt(o2time);

            return o1n - o2n;

        }

    }
}