package 코딩테스트공부;

import java.util.*;

public class 카카오코테5 {
    static int n = 0;
    static String map[][];
    static int visit[][];
    static ArrayList<String> arr;

    public static void Print(int size) {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                System.out.print(visit[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solution(new String[] { "MERGE 2 4 1 4", "MERGE 2 1 1 1", "UPDATE 1 4 WAT", "MERGE 1 1 1 4", "PRINT 1 3",
                "PRINT 1 4" });
    }

    public static void update(StringTokenizer st) {
        if (st.countTokens() == 3) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String s = st.nextToken();

            map[x][y] = s;
            // 머지 되있다면 다 바꾸셈
            if (visit[x][y] == 0)
                return;

            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (visit[i][j] == visit[x][y]) {
                        map[i][j] = s;
                    }
                }
            }

        } else {
            String s1 = st.nextToken();
            String s2 = st.nextToken();

            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (map[i][j].equals(s1)) {
                        map[i][j] = s2;
                    }
                }
            }
        }
    }

    private static void merge(StringTokenizer st) {
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        // 둘다 머지 되있지 않을 때
        if (visit[r1][c1] == 0 && visit[r2][c2] == 0) {
            n++;
            visit[r1][c1] = n;
            visit[r2][c2] = n;

        }
        // 오른쪽만 머지 되있을 때
        else if (visit[r1][c1] == 0 && visit[r2][c2] != 0) {
            visit[r1][c1] = visit[r2][c2];

        }
        // 왼쪽만 머지 되있을 때
        else if (visit[r1][c1] != 0 && visit[r2][c2] == 0) {
            visit[r2][c2] = visit[r1][c1];

        }
        // 둘다 머지 되 있을 때
        else if (visit[r1][c1] != 0 && visit[r2][c2] != 0) {
            int right = visit[r2][c2];
            // 두 머지 되있는 부분
            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (visit[i][j] == right) {
                        visit[i][j] = visit[r1][c1];
                    }
                }
            }
        }
        if (!map[r1][c1].isEmpty() && !map[r2][c2].isEmpty()) {
            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (visit[i][j] == visit[r1][c1]) {
                        map[i][j] = map[r1][c1];
                    }
                }
            }
        } else if (map[r1][c1].isEmpty() && !map[r2][c2].isEmpty()) {
            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (visit[i][j] == visit[r1][c1]) {
                        map[i][j] = map[r2][c2];
                    }
                }
            }
        } else if (map[r2][c2].isEmpty() && !map[r1][c1].isEmpty()) {
            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (visit[i][j] == visit[r1][c1]) {
                        map[i][j] = map[r1][c1];
                    }
                }
            }
        }
    }

    private static void unmerge(StringTokenizer st) {
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        String temp = map[r][c];
        int v = visit[r][c];
        if (v == 0)
            return;
        // 머지 되있다면 전부 해제후 초기상태로 돌아간다
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if (visit[i][j] == v) {
                    visit[i][j] = 0;
                    map[i][j] = "";
                }
            }
        }
        // 셀이 값을 가지고 있을 경우 r,c 위치에 값 넣기
        if (!temp.isEmpty())
            map[r][c] = temp;

    }

    private static void print(StringTokenizer st) {
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        if (map[r][c].isEmpty()) {
            arr.add("EMPTY");
        } else {
            arr.add(map[r][c]);
        }

    }

    public static String[] solution(String[] commands) {
        arr = new ArrayList<>();
        StringTokenizer st;
        map = new String[51][51];
        visit = new int[51][51];
        String[] answer;
        for (int i = 0; i < 51; i++) {
            Arrays.fill(map[i], "");
        }

        for (String command : commands) {
            st = new StringTokenizer(command);

            String s = st.nextToken();
            if (s.equals("UPDATE")) {
                update(st);
            } else if (s.equals("MERGE")) {
                merge(st);
            } else if (s.equals("UNMERGE")) {
                unmerge(st);
            } else if (s.equals("PRINT")) {
                print(st);
            }

            Print(4);

        }
        answer = new String[arr.size()];
        int idx = 0;
        for (String s : arr) {
            answer[idx++] = s;
        }
        return answer;
    }

}
