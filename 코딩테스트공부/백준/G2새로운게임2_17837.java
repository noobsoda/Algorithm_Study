package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G2새로운게임2_17837 {
    static int N, K, time;
    static int dx[] = { 0, 0, -1, 1 };
    static int dy[] = { 1, -1, 0, 0 };
    static int map[][];
    static int visitmap[][];
    static List<Node> list;

    public static void withmove(int x, int y, int d, int floor, int nfloor) {
        Node node;

        int cnt = 0;
        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            node = it.next();
            if (node.x == x && node.y == y && node.floor < floor)
                cnt++;
        }

        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            node = it.next();
            if (node.x != x || node.y != y || node.floor <= floor)
                continue;

            int nx = node.x + dx[d];
            int ny = node.y + dy[d];

            visitmap[node.x][node.y]--;
            visitmap[nx][ny]++;
            node.x = nx;
            node.y = ny;
            node.floor += nfloor;
            node.floor -= cnt;
            // 5가 3 > 1 넘어갈 때 3층 + 1층 되서 4가 된거임
        }
    }

    public static void reversemove(int x, int y, int nfloor, int reversefloor) {
        Node node;
        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            node = it.next();
            if (node.x != x || node.y != y || node.floor <= nfloor)
                continue;
            for (int i = 0; i < reversefloor; i++) {
                if (node.floor == i + nfloor + 1) {
                    node.floor = visitmap[x][y] - i;
                    break;
                }
            }

        }
    }

    public static boolean newgame() {
        Node node;
        for (Iterator<Node> it = list.iterator(); it.hasNext();) {
            node = it.next();

            int nx = node.x + dx[node.d];
            int ny = node.y + dy[node.d];
            // 벽에 도달했을 시
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) {
                if (node.d == 0 || node.d == 1)
                    node.d = (node.d + 1) % 2;
                else if (++node.d % 4 == 0)
                    node.d = 2;

                nx = node.x + dx[node.d];
                ny = node.y + dy[node.d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2)
                    continue;
                // 반대쪽 벽도 못가거나 파란벽이면 컨티뉴
                // 반대쪽으로 바라보게 하고 이동시키기
            }

            visitmap[node.x][node.y]--;
            // 현재 쌓여있는 층인 nfloor와 초기층 할당하기 위한 nvisit
            int nfloor = visitmap[nx][ny];
            int nvisit = ++visitmap[nx][ny];

            withmove(node.x, node.y, node.d, node.floor, nfloor);

            node.floor = nvisit;

            node.x = nx;
            node.y = ny;
            if (map[nx][ny] == 1) {
                int reversefloor = visitmap[nx][ny] - nfloor;
                if (reversefloor == 1) {
                    if (visitmap[nx][ny] >= 4)
                        return true;

                    continue;
                }
                reversemove(nx, ny, nfloor, reversefloor);
            }

            if (visitmap[nx][ny] >= 4) {
                return true;
            }

            // 이동시켰는데 만약에 빨간벽이면 floor 반전시키기

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visitmap[i][j] >= 4) {
                    return true;
                }

            }
        }

        return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new LinkedList<>();

        map = new int[N][N];
        visitmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Node(x - 1, y - 1, d - 1, 1));
            visitmap[x - 1][y - 1]++;
        }

        while (time <= 1000) {
            time++;
            if (newgame())
                break;

            // Print();
        }
        System.out.println((time > 1000) ? -1 : time);

    }

    // private static void Print() {
    // for(int i = 0; i < N; i++){
    // System.out.println(Arrays.toString(visitmap[i]));
    // }
    // System.out.println();
    // }
    static class Node {
        int x, y, d, floor;

        public Node(int x, int y, int d, int floor) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.floor = floor;
        }
    }

}

// https://www.acmicpc.net/problem/17837
// 들어가서 역전이 아니라
// 들어가기전 역전이 일어나야 함
// 62퍼 탈락