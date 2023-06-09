package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P5Boggle9202 {
    static int W, B;
    static final int BOARD_SIZE = 4;
    static Node rootNode;
    static char board[][];
    static boolean visited[][];
    static Set<String> hSet;
    static int[] dx = { 0, 0, -1, 1, 1, 1, -1, -1 };
    static int[] dy = { -1, 1, 0, 0, 1, -1, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        W = Integer.parseInt(br.readLine());
        rootNode = new Node(true);
        for (int i = 0; i < W; i++) {
            String s = br.readLine();
            rootNode.addChild(s);

        }
        br.readLine();
        B = Integer.parseInt(br.readLine());

        for (int i = 0; i < B; i++) {
            board = new char[BOARD_SIZE][BOARD_SIZE];
            visited = new boolean[BOARD_SIZE][BOARD_SIZE];
            hSet = new HashSet<>();

            for (int j = 0; j < BOARD_SIZE; j++) {
                String s = br.readLine();
                board[j] = s.toCharArray();

            }
            for (int k = 0; k < BOARD_SIZE; k++) {
                for (int z = 0; z < BOARD_SIZE; z++) {
                    visited[k][z] = true;
                    dfs(k, z, new StringBuilder(board[k][z] + ""));
                    visited[k][z] = false;
                }

            }
            // 각 Boggle의 사이에는 빈 줄이 하나 있다.
            if (i != B - 1) {
                br.readLine();
            }
            int res = 0;
            String resWord = "";
            for (String word : hSet) {
                res += getScore(word);
                if (word.length() > resWord.length()) {
                    resWord = word;
                } else if (word.length() == resWord.length()) {
                    // 사전순
                    if (word.compareTo(resWord) < 0) {
                        resWord = word;
                    }
                }
            }
            if (hSet.size() == 0)
                bw.write(res + " " + hSet.size() + "\n");
            else
                bw.write(res + " " + resWord + " " + hSet.size() + "\n");
        }
        bw.flush();

    }

    public static void dfs(int x, int y, StringBuilder sb) {
        String word = sb.toString();
        int state = contains(word);
        if (state == -1)
            return;
        else if (state == 1)
            hSet.add(word);
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= BOARD_SIZE || ny >= BOARD_SIZE || visited[nx][ny])
                continue;

            visited[nx][ny] = true;
            sb.append(board[nx][ny]);
            dfs(nx, ny, sb);
            visited[nx][ny] = false;
            sb.deleteCharAt(sb.length() - 1);

        }
    }

    public static int getScore(String s) {
        switch (s.length()) {
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            case 8:
                return 11;
            default:
                return 0;
        }

    }

    public static int contains(String word) {

        Node thisNode = rootNode;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            thisNode = thisNode.childNode.get(ch);

            if (thisNode == null)
                return -1;
        }

        return thisNode.isLastChild ? 1 : 0;
    }

    static class Node {
        Map<Character, Node> childNode;
        boolean isLastChild;

        public Node() {
            childNode = new HashMap<>();
        }

        public Node(boolean isNotLastChild) {
            childNode = new HashMap<>();
            this.isLastChild = isNotLastChild;
        }

        public void addChild(String word) {
            Node thisNode = rootNode;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!thisNode.childNode.containsKey(c)) {
                    thisNode.childNode.put(c, new Node());
                }
                thisNode = thisNode.childNode.get(c);
            }
            thisNode.isLastChild = true;
        }

    }

}
// https://www.acmicpc.net/problem/9202