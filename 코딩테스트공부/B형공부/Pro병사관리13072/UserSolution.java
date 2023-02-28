package 코딩테스트공부.B형공부.Pro병사관리13072;

import java.util.Iterator;
import java.util.PriorityQueue;

class UserSolution {
    private static PriorityQueue<Soldier> teamPQ[];
    private static Integer index[];

    public void init() {
        teamPQ = new PriorityQueue[5];
        for (int i = 0; i < 5; i++)
            teamPQ[i] = new PriorityQueue<>();
        index = new Integer[100001];

    }

    public void hire(int mID, int mTeam, int mScore) {
        teamPQ[mTeam].add(new Soldier(mID, mTeam, mScore));
        index[mID] = mTeam;
    }

    public void fire(int mID) {
        teamPQ[index[mID]].remove(new Soldier(mID, 0, 0));
        index[mID] = 0;
    }

    public void updateSoldier(int mID, int mScore) {
        teamPQ[index[mID]].remove(new Soldier(mID, 0, 0));
        teamPQ[index[mID]].add(new Soldier(mID, index[mID], mScore));
    }

    public void updateTeam(int mTeam, int mChangeScore) {
    }

    public int bestSoldier(int mTeam) {

        return 0;

    }

    static class Soldier implements Comparable<Soldier> {
        int mId;
        int mTeam;
        int mScore;

        public Soldier(int mId, int mTeam, int mScore) {
            this.mId = mId;
            this.mTeam = mTeam;
            this.mScore = mScore;
        }

        @Override
        public boolean equals(Object o) {
            Soldier soldier = (Soldier) o;
            if (this.mId == soldier.mId) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Soldier o) {
            if (this.mScore == o.mScore) {
                return o.mId - this.mId;
            }
            return o.mScore - this.mScore;
        }
    }
}
