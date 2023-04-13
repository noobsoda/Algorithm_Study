package 코딩테스트공부.코테;

import java.util.*;

class LG코드몬스터2 {
    static List<Node> list;
    static boolean visited[];
    static int K;

    public static boolean Check(int[] limits, int[][] sockets) {
        int[] nowlimit = new int[limits.length];

        for (int i = sockets.length - 1; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                if (sockets[i][j] == -1) {
                    nowlimit[i] += K;
                } else if (sockets[i][j] != 0) {
                    nowlimit[i] += nowlimit[sockets[i][j] - 1];
                }
            }
            if (nowlimit[i] > limits[i])
                return false;
        }

        return true;
    }

    public static boolean combi(int start, int depth, int end, int[] limits, int[][] sockets) {
        if (depth == end) {
            if (Check(limits, sockets))
                return true;
            return false;
        }

        for (int i = start; i < list.size(); i++) {
            visited[i] = true;
            Node now = list.get(i);
            sockets[now.x][now.y] = 0;
            if (combi(i + 1, depth + 1, end, limits, sockets))
                return true;
            sockets[now.x][now.y] = -1;
            visited[i] = false;
        }
        return false;
    }

    public int solution(int k, int[] limits, int[][] sockets) {
        K = k;
        int answer = 0;
        list = new LinkedList<>();

        for (int i = 0; i < limits.length; i++) {
            for (int j = 0; j < 5; j++) {
                if (sockets[i][j] == -1) {
                    list.add(new Node(i, j));
                }
            }
        }
        visited = new boolean[list.size()];

        // 돌기전에 이미 소비전력 되있는지 확인
        for (int i = 1; i <= list.size(); i++)
            if (combi(0, 0, i, limits, sockets)) {
                answer = i;
                break;
            }

        return answer;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}