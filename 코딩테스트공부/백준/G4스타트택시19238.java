package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4스타트택시19238 {
    static int N, M, nowfuel;
    static int map[][];
    static int taxix, taxiy, minx, miny;
    static List<Node> list;
    static Queue<Node> q;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    // 최소거리 시민 찾기
    public static int find() {
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        boolean visit[][] = new boolean[N + 1][N + 1];

        q.add(new Node(taxix, taxiy, 0, 0, 0));
        visit[taxix][taxiy] = true;

        int minfuel = Integer.MAX_VALUE;

        // 출발지와 택시의 위치가 똑같을 때
        if (map[taxix][taxiy] == 2) {
            map[taxix][taxiy] = 0;
            minx = taxix;
            miny = taxiy;
            q.clear();
            return 0;
        }

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 1)
                    continue;
                if (!visit[nx][ny] && map[nx][ny] == 0) {
                    q.add(new Node(nx, ny, 0, 0, now.fuel + 1));
                    visit[nx][ny] = true;
                } else if (!visit[nx][ny] && map[nx][ny] == 2) {
                    visit[nx][ny] = true;
                    // 최소 연료비를 찾았을 시
                    if (minfuel >= now.fuel + 1) {
                        minfuel = now.fuel + 1;
                        // 행 우선
                        if (minx > nx) {
                            minx = nx;
                            miny = ny;
                        }
                        // 행 똑같을 때 열 우선
                        else if (minx == nx) {
                            if (miny > ny) {
                                minx = nx;
                                miny = ny;
                            }
                        }
                    }
                }
            }

        }

        return minfuel;
    }

    public static void starttaxi() {
        // 가장 가까운 승객 검색해서 태우고 연료 제거
        nowfuel -= find();

        // 승객에게 갔을때 연료가 0이하일시 -1반환
        if (nowfuel <= 0) {
            nowfuel = -1;
            return;
        }
        // 승객 태운곳 제거
        map[minx][miny] = 0;

        boolean visit[][] = new boolean[N + 1][N + 1];
        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            Node now = it.next();

            if (now.x == minx && now.y == miny) {
                // 출발지와 도착지가 똑같을 때
                if (now.x == now.endx && now.y == now.endy) {
                    it.remove();
                    break;
                }

                q.add(new Node(now.x, now.y, now.endx, now.endy, 0));

                while (!q.isEmpty()) {
                    Node n = q.poll();

                    for (int i = 0; i < 4; i++) {
                        int nx = n.x + dx[i];
                        int ny = n.y + dy[i];

                        if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 1)
                            continue;
                        if (!visit[nx][ny]) {
                            q.add(new Node(nx, ny, n.endx, n.endy, n.fuel + 1));
                            visit[nx][ny] = true;
                        }
                        if (nx == n.endx && ny == n.endy) {
                            q.clear();
                            // 거리만큼 연료 제거
                            nowfuel -= n.fuel + 1;
                            // 택시 이동
                            taxix = nx;
                            taxiy = ny;
                            // 도착했지만 택시 연료가 0보다 아래일때
                            if (nowfuel < 0) {
                                nowfuel = -1;
                                return;
                            }
                            // 도착한 택시가 0이상일 때
                            else {
                                nowfuel += (n.fuel + 1) * 2;
                            }
                            break;
                        }
                    }

                }
                // 택시가 도착지에 도달하기 못했을시
                if (taxix != now.endx || taxiy != now.endy) {
                    nowfuel = -1;
                    return;
                }
                it.remove();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        nowfuel = Integer.parseInt(nv[2]);

        map = new int[N + 1][N + 1];
        list = new LinkedList<>();
        q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            nv = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(nv[j - 1]);
            }
        }
        nv = br.readLine().split(" ");
        taxix = Integer.parseInt(nv[0]);
        taxiy = Integer.parseInt(nv[1]);

        for (int i = 0; i < M; i++) {
            nv = br.readLine().split(" ");
            int x = Integer.parseInt(nv[0]);
            int y = Integer.parseInt(nv[1]);
            int endx = Integer.parseInt(nv[2]);
            int endy = Integer.parseInt(nv[3]);

            list.add(new Node(x, y, endx, endy, 0));
            map[x][y] = 2;

        }

        for (int i = 0; i < M; i++) {
            if (nowfuel > 0)
                starttaxi();
        }
        System.out.println(nowfuel);

    }

    static class Node {
        int x, y, endx, endy, fuel;

        public Node(int x, int y, int endx, int endy, int fuel) {
            this.x = x;
            this.y = y;
            this.endx = endx;
            this.endy = endy;
            this.fuel = fuel;

        }
    }

}
