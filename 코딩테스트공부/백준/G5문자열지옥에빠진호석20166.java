package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

/**
 * 메모리제이션을 추가로 활용할것
 */
public class G5문자열지옥에빠진호석20166 {
    static int N, M, K, res;
    static Map<String, String> hMap;
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
        for (int i = 0; i < K; i++) {
            res = 0;
            String favWord = br.readLine();
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    // 시작단어가 일치하지 않는다면 X
                    if (map[x][y] != favWord.charAt(0))
                        continue;
                    dfs(favWord, x, y, 0);

                }
            }
            bw.write(res + "\n");
        }
        bw.flush();

    }

    public static void dfs(String favWord, int x, int y, int depth) {

        // 문자열이 일치하지 않는다면 X
        if (map[x][y] != favWord.charAt(depth)) {
            return;
        }
        if (depth == favWord.length() - 1) {
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
            dfs(favWord, nx, ny, depth + 1);

        }
    }

}
// https://www.acmicpc.net/problem/20166