package 코딩테스트공부.B형공부.Pro산타의선물공장;

import java.io.*;
import java.util.*;

public class UserSolution {
    static int numQuery, N, M;
    static Node nodes[] = new Node[200055];
    static int version[] = new int[200055];
    static HashMap<Integer, Team> num;
    static Node head[];
    static Node tail[];
    static Team teamNum[];
    static int cnt;

    private static Node getNode(int id, int w, int v) {
        Node node = nodes[cnt++];
        node.id = id;
        node.w = w;
        node.v = v;
        node.next = null;
        return node;

    }

    private static void init(int n, int m, int[] id, int[] w) {
        N = n;
        M = m;
        cnt = 0;
        teamNum = new Team[m];
        num = new HashMap<>();
        head = new Node[m];
        tail = new Node[m];
        for (int i = 0; i < 200055; i++) {
            if (nodes[i] == null)
                nodes[i] = new Node();
            version[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            head[i] = tail[i] = getNode(0, 0, -1);
            teamNum[i] = new Team(i);
        }
        int st = -1;
        int size = n / m;
        for (int i = 0; i < n; i++) {
            if (i % size == 0)
                st++;
            // 버전이 몇인지 표시
            version[id[st]]++;
            // 몇 번째 라인에 있는지 표시
            num.put(id[st], teamNum[st]);

            // 노드 가져와서 꼬리에 붙이기
            Node node = getNode(id[i], w[i], version[id[st]]);
            tail[st].next = node;
            tail[st] = node;

        }
    }

    private static int pull(int w_max) {
        int res = 0;
        for (int i = 0; i < M; i++) {
            if (head[i].next == null)
                continue;
            Node node = head[i];

            while (node.next != null) {
                node = node.next;

                if (node.v != version[node.id])
                    continue;
                head[i].next = node.next;
                if (node.w <= w_max) {
                    res += node.w;
                    break;
                } else {
                    tail[i].next = node;
                    tail[i] = node;
                    break;
                }

            }

        }
        return res;
    }

    private static int remove(int r_id) {
        if (version[r_id] == -1)
            return -1;

        return r_id;

    }

    private static int check(int f_id) {
        if (version[f_id] == -1)
            return -1;
        int lineNum = num.get(f_id).num;
        if (head[lineNum].next == null) {
            return -1;
        }
        Node node = head[lineNum];
        Node prev = null;
        while (node.next != null) {
            prev = node;
            node = node.next;
            if (node.id == f_id && node.v == version[f_id]) {
                tail[lineNum].next = head[lineNum].next;
                tail[lineNum] = prev;
                prev.next = null;
                head[lineNum].next = node;
                break;
            }
        }

        return lineNum;
    }

    private static int breakDown(int b_num) {
        int line = --b_num;
        // 이미 부서져 있음
        if (teamNum[line].num != line) {
            return -1;
        }
        while (b_num != ++line) {
            // 1번 벨트로 돌아가
            if (line % M == 0) {
                line = 0;
            }
            // 다음 벨트로 부서져 있는 경우
            if (teamNum[line].num != line) {
                continue;
            }
            // 안 부서져 있으면
            tail[line].next = head[b_num].next;
            tail[line] = tail[b_num];

            // 번호 변경
            teamNum[b_num].num = line;

        }
        return b_num + 1;
    }

    static class Team {
        int num;

        public Team(int num) {
            this.num = num;
        }
    }

    static class Node {
        int id;
        int w;
        int v;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int id, int w, int v) {
            this.id = id;
            this.w = w;
            this.v = v;
            this.next = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numQuery = Integer.parseInt(st.nextToken());

        for (int q = 0; q < numQuery; q++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            if (command == 100) {
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                int id[] = new int[n];
                int w[] = new int[n];

                for (int i = 0; i < n; i++) {
                    id[i] = Integer.parseInt(st.nextToken());
                }
                for (int i = 0; i < n; i++) {
                    w[i] = Integer.parseInt(st.nextToken());
                }
                init(n, m, id, w);

            } else if (command == 200) {
                int w_max = Integer.parseInt(st.nextToken());
                System.out.println(pull(w_max));
            } else if (command == 300) {
                int r_id = Integer.parseInt(st.nextToken());
                System.out.println(remove(r_id));
            } else if (command == 400) {
                int f_id = Integer.parseInt(st.nextToken());
                System.out.println(check(f_id));
            } else if (command == 500) {
                int b_num = Integer.parseInt(st.nextToken());
                System.out.println(breakDown(b_num));
            }
        }
    }

}