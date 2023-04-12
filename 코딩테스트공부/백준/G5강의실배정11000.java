package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5강의실배정11000 {
    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nv;

        N = Integer.parseInt(br.readLine());
        Node[] lecture = new Node[N];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            nv = br.readLine().split(" ");
            int s = Integer.parseInt(nv[0]);
            int t = Integer.parseInt(nv[1]);

            lecture[i] = new Node(s, t);
        }

        Arrays.sort(lecture);

        // 맨 처음 수업 끝나는 시간 삽입
        pq.add(lecture[0].t);

        for (int i = 1; i < N; i++) {
            // pq최상위부분과 그 다음수업 시작지점 비교해서 정답이면 폭
            if (pq.peek() <= lecture[i].s) {
                pq.poll();
            }
            pq.add(lecture[i].t);
        }

        System.out.println(pq.size());

    }

    static class Node implements Comparable<Node> {
        int s;
        int t;

        public Node(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            if (s == o.s)
                return t - o.t;

            return s - o.s;
        }
    }

}

// 시작지점부터 정렬하는걸 몰랐음
// https://www.acmicpc.net/problem/11000