package 코딩테스트공부;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;

public class G2코드트리빵 {
    static int N, M;
    static int dx[] = { -1, 0, 1, 0 };
    static int dy[] = { 0, -1, 0, 1 };
    static int map[][];
    static boolean visited[][];
    static List<Store> storeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];
        storeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            storeList.add(new Store(x, y));

            simul();

        }

    }

    private static void bfs(int x, int y) {

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        Queue<Node> q = new ArrayDeque<>();
        
        q.add(new Node(x, y, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == -1)
                    continue;

                if(map[nx][ny] == 1){
                    
                }
                q.add(new Node(nx, ny, node.w+1));
                visited[nx][ny] = true;

            }
        }
    }

    private static void simul() {

    }

    static class Node {
        int x, y, w;

        public Node(int x, int y, int w) {
            this.w = w;
            this.y = y;
            this.w = w;
        }
    }

    static class Store {
        int x, y;

        public Store(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
