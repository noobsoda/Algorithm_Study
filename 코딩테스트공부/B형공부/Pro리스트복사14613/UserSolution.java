package 코딩테스트공부.B형공부.Pro리스트복사14613;

import java.util.*;

class UserSolution {

    // 문자열 번호 매칭 해쉬맵
    static HashMap<String, Integer> nameMap;
    static int originArray[][];
    static int pre[], last[];
    static int initCnt;
    static int changeCnt;
    static int addressCnt;

    static Node node[];

    public void init() {
        node = new Node[110000];
        initCnt = 1;
        changeCnt = 1;
        addressCnt = 1;
        nameMap = new HashMap<>();
        originArray = new int[10 + 1][200000 + 1];
        pre = new int[110000];
        last = new int[6000];

    }

    public String makeName(char mName[]) {
        StringBuilder sb = new StringBuilder();
        for (char c : mName) {
            if (c == '\0')
                break;
            sb.append(c);
        }
        return sb.toString();

    }

    public void makeList(char _mName[], int mLength, int mListValue[]) {

        String mName = makeName(_mName);
        nameMap.put(mName, addressCnt);
        System.arraycopy(mListValue, 0, originArray[initCnt], 0, mLength);
        node[changeCnt] = new Node(-1, initCnt);
        pre[changeCnt] = -1;
        last[addressCnt] = changeCnt;

        initCnt++;
        changeCnt++;
        addressCnt++;

    }

    public void copyList(char mDest[], char mSrc[], boolean mCopy) {
        String mSrcName = makeName(mSrc);
        String mDestName = makeName(mDest);
        if (mCopy) {
            nameMap.put(mDestName, addressCnt);

            node[changeCnt] = new Node(-1, -1);
            pre[changeCnt] = last[nameMap.get(mSrcName)];
            last[addressCnt] = changeCnt;
            addressCnt++;
            changeCnt++;

        } else {
            nameMap.put(mDestName, nameMap.get(mSrcName));
        }
    }

    public void updateElement(char mName[], int mIndex, int mValue) {
        // 주소로 연결된 마지막 nxt에서 마지막만 업데이트
        // int mNameCnt = nameMap.get(makeName(mName));
    }

    public int element(char mName[], int mIndex) {
        // 조회할 때 nxt 쭉 따라가다가 pre로 연결 안되있으면 stop 거기가 연결되어 있는것

        return 0;
    }

    static class Node {
        int first, second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
