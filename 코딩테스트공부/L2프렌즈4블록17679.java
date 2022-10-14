package 코딩테스트공부;

public class L2프렌즈4블록17679 {
    static int N, M;
    static int dx[] = { 0, 0, 1, 1 };
    static int dy[] = { 0, 1, 0, 1 };
    static boolean visited[][];
    static char map[][];

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        N = m;
        M = n;
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = board[i].toCharArray();
        }

        boolean flag = true;

        while (flag) {
            flag = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == ' ')
                            continue;
                        if (map[i][j] != map[nx][ny])
                            continue;

                        cnt++;
                    }
                    if (cnt == 4) {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            visited[nx][ny] = true;
                            flag = true;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) {
                        map[i][j] = ' ';
                        visited[i][j] = false;
                        answer++;
                    }
                }
            }
            downmap(map);

        }

        return answer;
    }

    public static void downmap(char map[][]) {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == ' ') {
                    for (int k = i; k >= 0; k--) {
                        if (map[k][j] != ' ') {
                            map[i][j] = map[k][j];
                            map[k][j] = ' ';
                            break;
                        }
                    }
                }
            }
        }
    }
}