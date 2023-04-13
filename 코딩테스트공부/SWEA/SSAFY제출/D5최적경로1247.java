package 코딩테스트공부.SWEA.SSAFY제출;

import java.util.*;
import java.io.*;

class D5최적경로1247 {
    static int N, min;
    static int sx, sy, ex, ey;
    static List<Node> list;
    static boolean visited[];

    public static void dfs(int depth, int lx, int ly, int sum) {

        if (sum > min)
            return;

        if (depth == N) {
            sum += Math.abs(ex - lx) + Math.abs(ey - ly);
            min = Math.min(sum, min);
            return;
        }
        int cnt = 0;
        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            Node now = it.next();

            if (visited[cnt]) {
                cnt++;
                continue;
            }
            int ns = sum + Math.abs(now.x - lx) + Math.abs(now.y - ly);
            visited[cnt] = true;
            dfs(depth + 1, now.x, now.y, ns);
            visited[cnt] = false;

            cnt++;
        }

    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            list = new LinkedList<>();
            visited = new boolean[N];

            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Node(x, y));
            }

            dfs(0, sx, sy, 0);

            System.out.println("#" + test_case + " " + min);
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE&problemTitle=1247&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&