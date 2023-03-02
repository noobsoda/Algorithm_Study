package 코딩테스트공부.B형공부.Pro섬지키기14596;

import java.util.*;

class UserSolution {
    static int size;
    static int islandMap[][];
    static int tempMap[][];
    static boolean visited[][];
    static Map<String, List<Node>> hMap;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };

    public void init(int N, int mMap[][]) {
        islandMap = new int[N][N];
        tempMap = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                islandMap[i][j] = mMap[i][j];
                tempMap[i][j] = mMap[i][j];
            }
        }
        size = N;
        hMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                for (int k = 0; k < 4; k++) {
                    StringBuilder sb = new StringBuilder();
                    int temp = -1;
                    int mx = i;
                    int my = j;

                    while (true) {
                        if (mx < 0 || my < 0 || mx >= size || my >= size)
                            break;
                        if (temp == -1)
                            temp = mMap[mx][my];
                        else {
                            sb.append(temp - mMap[mx][my] + 5);
                            temp = mMap[mx][my];
                            if (hMap.containsKey(sb.toString())) {
                                List<Node> list = hMap.get(sb.toString());
                                list.add(new Node(mx, my, k));
                                hMap.put(sb.toString(), list);
                            } else {
                                List<Node> list = new ArrayList<>();
                                list.add(new Node(mx, my, k));
                                hMap.put(sb.toString(), list);
                            }
                        }
                        mx += dx[k];
                        my += dy[k];
                    }

                }

            }

        }

    }

    public int numberOfCandidate(int M, int mStructure[]) {
        int res = 0;
        int temp = -1;
        StringBuilder sb = new StringBuilder();

        if (M == 1) {
            return size * size;
        }
        for (int i = 0; i < M; i++) {
            if (i == 0)
                temp = mStructure[i];
            else {
                sb.append(temp - mStructure[i] + 5);
                temp = mStructure[i];
            }
        }

        String s = sb.reverse().toString();
        if (hMap.containsKey(s)) {
            res += hMap.get(s).size();
        }
        sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(mStructure[i]);
        }
        if (sb.toString().equals(sb.reverse().toString()))
            res /= 2;

        return res;
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        int max = -1;
        int temp = -1;
        Queue<Node> resQ = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        // 한개일 때
        if (M == 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    resQ.add(new Node(i, j, 0));
                }
            }
        } else { // 한개 이상일 때
            for (int i = 0; i < M; i++) {
                if (i == 0)
                    temp = mStructure[i];
                else {
                    sb.append(temp - mStructure[i] + 5);
                    temp = mStructure[i];
                }
            }
            String s = sb.reverse().toString();
            if (hMap.containsKey(s)) {
                resQ.addAll(hMap.get(s));
            }
        }
        // 모서리 bfs 돌리기

        while (!resQ.isEmpty()) {
            int res = 0;
            Node resNow = resQ.poll();

            for (int i = 0; i < size; i++) {
                islandMap[i] = tempMap[i].clone();
                Arrays.fill(visited[i], false);
            }
            int resNX = resNow.x;
            int resNY = resNow.y;
            for (int i = 0; i < M; i++) {
                islandMap[resNX][resNY] += mStructure[i];
                resNX += dx[(resNow.k + 2) % 4];
                resNY += dy[(resNow.k + 2) % 4];
            }
            List<Node> list = addIsland(mSeaLevel);

            Queue<Node> q = new ArrayDeque<>();
            q.addAll(list);

            while (!q.isEmpty()) {
                Node now = q.poll();
                if (visited[now.x][now.y])
                    continue;
                visited[now.x][now.y] = true;

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= size || ny >= size || visited[nx][ny]
                            || islandMap[nx][ny] >= mSeaLevel)
                        continue;

                    q.add(new Node(nx, ny));

                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!visited[i][j])
                        res++;

                }
            }
            max = Math.max(max, res);

        }

        return max;
    }

    private static List<Node> addIsland(int mSeaLevel) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (islandMap[i][j] >= mSeaLevel)
                    continue;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || ny < 0 || nx >= size || ny >= size) {
                        list.add(new Node(i, j));
                        break;
                    }

                }
            }
        }
        return list;

    }

    static class Node {
        int x;
        int y;
        int k;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}