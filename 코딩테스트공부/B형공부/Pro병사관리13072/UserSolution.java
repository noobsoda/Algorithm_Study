package 코딩테스트공부.B형공부.Pro병사관리13072;

import java.util.Arrays;

class UserSolution {
    static int version[] = new int[200055];
    static Node nodes[] = new Node[200055];
    static int teamNum[] = new int[100055];
    static int cnt;
    static Team team[];

    static class Team {
        Node[] head = new Node[6];
        Node[] tail = new Node[6];
    }

    public void init() {
        cnt = 0;
        for (int i = 0; i < 200055; i++) {
            if (nodes[i] == null)
                nodes[i] = new Node();
        }
        team = new Team[6];
        for (int i = 0; i < 6; i++) {
            team[i] = new Team();
            for (int j = 0; j < 6; j++) {
                team[i].head[j] = team[i].tail[j] = getNode(0, -1);
            }
        }
        // 삭제, 추가를 편하게 하기 위해 버전으로 관리
        Arrays.fill(version, -1);
        Arrays.fill(teamNum, 0);
    }

    private static Node getNode(int mID, int v) {
        Node node = nodes[cnt++];
        node.mID = mID;
        node.v = v;
        node.nxt = null;

        return node;
    }

    public void hire(int mID, int mTeam, int mScore) {
        // 고용버전 0번으로 고용
        version[mID]++;
        teamNum[mID] = mTeam;
        Node node = getNode(mID, version[mID]);
        team[mTeam].tail[mScore].nxt = node;
        team[mTeam].tail[mScore] = node;

    }

    public void fire(int mID) {
        version[mID] = -1;
    }

    public void updateSoldier(int mID, int mScore) {
        Node node = getNode(mID, version[mID]++);
        team[mScore].tail[mScore].nxt = node;
    }

    public void updateTeam(int mTeam, int mChangeScore) {
        // 음수일 때
        if (mChangeScore < 0) {
            for (int i = 1; i <= 5; i++) {
                int k = i + mChangeScore;
                if (k < 1)
                    k = 1;
                if (k == i)
                    continue;
                if (team[mTeam].head[i].nxt == null)
                    continue;

                team[mTeam].tail[k].nxt = team[mTeam].head[i].nxt;
                team[mTeam].tail[k] = team[mTeam].tail[i];
                team[mTeam].head[i].nxt = null;
                team[mTeam].tail[i] = team[mTeam].head[i];
            }
        }
        // 양수일 때
        else {
            for (int i = 5; i >= 1; i--) {
                int k = i + mChangeScore;
                if (k > 5)
                    k = 5;
                if (k == i)
                    continue;
                if (team[mTeam].head[i].nxt == null)
                    continue;

                team[mTeam].tail[k].nxt = team[mTeam].head[i].nxt;
                team[mTeam].tail[k] = team[mTeam].tail[i];
                team[mTeam].head[i].nxt = null;
                team[mTeam].tail[i] = team[mTeam].head[i];
            }
        }
    }

    public int bestSoldier(int mTeam) {
        int max = 0;
        for (int i = 5; i >= 1; i--) {
            if (team[mTeam].head[i].nxt == null)
                continue;
            Node node = team[mTeam].head[i];
            // mID가 가장 큰 솔져를 찾아라
            while (node.nxt != null) {
                node = node.nxt;
                // 버전이 맞지 않으면 넘기기
                if ((version[node.mID] != node.v) || version[node.mID] == -1)
                    continue;

                max = Math.max(node.mID, max);

            }

            // 인지도가 가장 큰놈 중에 mID가 큰놈을 찾으면 나가기
            if (max != 0)
                break;
        }
        return max;

    }

    static class Node {
        int mID;
        int v;
        Node nxt;

        public Node() {

        }

        public Node(int mID, int v) {
            this.mID = mID;
            this.v = v;
            this.nxt = null;
        }

        public Node(int mID, int v, Node nxt) {
            this.mID = mID;
            this.v = v;
            this.nxt = nxt;
        }

    }

}
