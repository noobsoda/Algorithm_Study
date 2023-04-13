package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G3트리의지름1167 {
    static int V, maxnum = 0, maxdist = 0;
    static boolean visited[];
    static ArrayList<Node> arr[];

    public static void dfs(int n, int dist) {

        for (Node now : arr[n]) {
            if (visited[now.y])
                continue;
            visited[now.y] = true;
            dfs(now.y, dist + now.w);
            visited[now.y] = false;
        }
        if (maxdist < dist) {
            maxnum = n;
            maxdist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        arr = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        for (int i = 0; i <= V; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int b = Integer.parseInt(st.nextToken());
                if (b == -1)
                    continue;

                int c = Integer.parseInt(st.nextToken());
                arr[a].add(new Node(b, c));
            }
        }
        visited[1] = true;
        dfs(1, 0);
        visited[1] = false;

        visited[maxnum] = true;
        dfs(maxnum, 0);

        System.out.println(maxdist);

    }

    static class Node {
        int y, w;

        public Node(int y, int w) {
            this.y = y;
            this.w = w;
        }
    }
}
// https://www.acmicpc.net/problem/1167