package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * Dfs 탐색같은 접근이 아니라 미리 Hash를 통해서 값을 미리 구하면 K == 1000 일때도 빠르게 할 수 있음
 */
public class G4문자열지옥에빠진호석20166 {
    static int N, M, K, res;
    static Map<String, Integer> hMap;
    static char map[][];
    static int dx[] = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int dy[] = { -1, 0, 1, 1, 1, 0, -1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hMap = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }
        res = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                for (int j = 1; j <= 5; j++) {
                    dfs(new StringBuilder(), j, x, y, 0);
                }

            }
        }
        for (int i = 0; i < K; i++) {
            String favWord = br.readLine();
            if (hMap.containsKey(favWord)) {
                bw.write(hMap.get(favWord) + "\n");
            } else {
                bw.write(0 + "\n");
            }
        }
        bw.flush();

    }

    public static void dfs(StringBuilder sb, int length, int x, int y, int depth) {
        sb.append(map[x][y]);
        // 문자열이 일치하지 않는다면 X
        if (depth == length - 1) {
            if (hMap.containsKey(sb.toString())) {
                hMap.put(sb.toString(), hMap.get(sb.toString()) + 1);
            } else {
                hMap.put(sb.toString(), 1);
            }
            res++;
            return;
        }
        // 맨 끝 문자열에 도달했을 경우 +1

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0) {
                nx = N - 1;
            } else if (nx >= N) {
                nx = 0;
            }
            if (ny < 0) {
                ny = M - 1;
            } else if (ny >= M) {
                ny = 0;
            }
            dfs(sb, length, nx, ny, depth + 1);
            sb.deleteCharAt(sb.length() - 1);

        }
    }

}
// https://www.acmicpc.net/problem/20166