package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")

public class G3BFS스페셜저지16940 {
    static int N;
    static boolean visited[];
    static int result[];
    static Queue<Integer> q;
    static ArrayList<Integer> arr[];

    public static boolean bfs(int start) {
        visited[start] = true;
        q.add(start);
        int cnt = start + 1;
        HashSet<Integer> set = new HashSet<>();

        while (!q.isEmpty()) {
            int now = q.poll();
            set.clear();

            for (int n : arr[now]) {
                if (visited[n])
                    continue;

                visited[n] = true;
                set.add(n);
            }

            int size = set.size();

            for (int i = cnt; i < cnt + size; i++) {
                if (set.contains(result[i]))
                    q.add(result[i]);
                else
                    return false;
            }
            cnt += size;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        arr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        result = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 1;
        if (!bfs(1))
            answer = 0;
        if (result[1] != 1)
            answer = 0;

        System.out.println(answer);

    }
}
// https://www.acmicpc.net/problem/16940