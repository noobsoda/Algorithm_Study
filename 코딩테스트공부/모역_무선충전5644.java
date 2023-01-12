package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class 모역_무선충전5644 {
    static int M, A, max;
    static int[] HA, HB;
    static int[] resA, resB;
    static ArrayList<Node> arr;
    static int dx[] = { 0, -1, 0, 1, 0 };
    static int dy[] = { 0, 0, 1, 0, -1 };

    public static void simulate(int index, int Asum, int Bsum, int ax, int ay, int bx, int by) {

        if (index > 0) {
            resA[index - 1] = Asum;
            resB[index - 1] = Bsum;
        }
        if (index == M + 1) {
            // 최대값 체크
            if (max < Asum + Bsum) {
                max = Asum + Bsum;
                // System.out.println(Arrays.toString(resA));
                // System.out.println(Arrays.toString(resB));
            }
            return;
        }

        List<Node> listA = new ArrayList<>();
        List<Node> listB = new ArrayList<>();
        // 다음 좌표
        int nax = ax;
        int nay = ay;

        int nbx = bx;
        int nby = by;

        if (index < M) {
            nax += dx[HA[index]];
            nay += dy[HA[index]];

            nbx += dx[HB[index]];
            nby += dy[HB[index]];
        }

        // 현재 A한테 충전할 수 있는 충전소
        for (Node now : arr) {
            if (Math.abs(now.x - ax) + Math.abs(now.y - ay) <= now.c) {
                listA.add(now);
            }
        }
        // 현재 B한테 충전할 수 있는 충전소
        for (Node now : arr) {
            if (Math.abs(now.x - bx) + Math.abs(now.y - by) <= now.c) {
                listB.add(now);
            }
        }

        // 둘다 비어있다면
        if (listA.isEmpty() && listB.isEmpty()) {
            simulate(index + 1, Asum, Bsum, nax, nay, nbx, nby);
        }
        // A가 비어있다면
        else if (listA.isEmpty()) {
            int sum = 0;
            for (Node lb : listB) {
                sum = Math.max(sum, lb.p);
            }
            simulate(index + 1, Asum, Bsum + sum, nax, nay, nbx, nby);
        } // B가 비어있다면
        else if (listB.isEmpty()) {
            int sum = 0;
            for (Node la : listA) {
                sum = Math.max(sum, la.p);
            }
            simulate(index + 1, Asum + sum, Bsum, nax, nay, nbx, nby);

        } else {
            int sum = 0;
            int idxA = -1;
            int idxB = -1;
            boolean flag = false;
            // 서로 다른 경로가 있다면
            for (int i = 0; i < listA.size(); i++) {
                for (int j = 0; j < listB.size(); j++) {
                    Node la = listA.get(i);
                    Node lb = listB.get(j);
                    if (la.x == lb.x && la.y == lb.y) {
                        if (sum < la.p) {
                            sum = la.p;
                            idxA = i;
                            idxB = j;
                            flag = true;
                        }
                    } else {
                        if (sum < la.p + lb.p) {
                            sum = la.p + lb.p;
                            idxA = i;
                            idxB = j;
                            flag = false;
                        }

                    }
                }

            }
            Node la = listA.get(idxA);
            Node lb = listB.get(idxB);
            // 충전소를 나눠쓰는게 더 크다면
            if (flag)
                simulate(index + 1, Asum + sum / 2, Bsum + sum / 2, nax, nay, nbx, nby);
            else
                simulate(index + 1, Asum + la.p, Bsum + lb.p, nax, nay, nbx, nby);

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            max = 0;
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            HA = new int[M];
            HB = new int[M];
            resA = new int[M + 1];
            resB = new int[M + 1];
            arr = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                HA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                HB[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                arr.add(new Node(y, x, c, p));
            }

            simulate(0, 0, 0, 1, 1, 10, 10);

            System.out.println("#" + test_case + " " + max);
        }
    }

    static class Node {
        int x, y, c, p;

        public Node(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }
}
// 똑같은곳에서 동시에 범위 받는게 있을 때
// 조건에 해당하면 조합

// 1
// 20 3
// 2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3
// 4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3
// 4 4 1 100
// 7 10 3 40
// 6 3 2 70

// 1
// 60 4
// 0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3
// 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4
// 1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2
// 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1
// 6 9 1 180
// 9 3 4 260
// 1 4 1 500
// 1 3 1 230
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo