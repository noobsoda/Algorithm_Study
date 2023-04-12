package 코딩테스트공부.프로그래머스;

public class L2격자선 {
    static int map[][][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };
    // 상 하 좌 우

    public static void main(String[] args) {
        String[] moves = { "U", "R", "D", "L", "U", "R", "D", "L" };
        solution(moves);
    }

    public static int solution(String[] moves) {
        int answer = 0;
        map = new int[10][10][4];

        int x = 5;
        int y = 5;

        // 4번
        map[x][y][3] = 1;
        map[x][y + 1][2] = 1;
        map[x + 1][y][1] = 1;
        map[x + 1][y + 1][0] = 1;

        for (int i = 0; i < moves.length; i++) {
            if (moves[i].equals("U")) {
                x += -1;
                map[x][y][3] = 1;
                map[x][y + 1][2] = 1;
                map[x + 1][y][1] = 1;
                map[x + 1][y + 1][0] = 1;
            } else if (moves[i].equals("D")) {
                x += 1;
                map[x][y][3] = 1;
                map[x][y + 1][2] = 1;
                map[x + 1][y][1] = 1;
                map[x + 1][y + 1][0] = 1;
            } else if (moves[i].equals("L")) {
                y += -1;
                map[x][y][3] = 1;
                map[x][y + 1][2] = 1;
                map[x + 1][y][1] = 1;
                map[x + 1][y + 1][0] = 1;
            } else if (moves[i].equals("R")) {
                y += 1;
                map[x][y][3] = 1;
                map[x][y + 1][2] = 1;
                map[x + 1][y][1] = 1;
                map[x + 1][y + 1][0] = 1;
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    if (map[i][j][k] == 1)
                        cnt++;

                }
                if (cnt == 4) {
                    answer++;
                }
            }
        }

        return answer;
    }
}

// 3 3 1
// 2 3 1
// 2 4 0
// 2 4 1
// 3 4 0
// 3 3 0
