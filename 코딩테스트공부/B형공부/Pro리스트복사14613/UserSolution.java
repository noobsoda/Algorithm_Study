package 코딩테스트공부.B형공부.Pro리스트복사14613;

import java.util.*;

@SuppressWarnings("unchecked")

class UserSolution {

    // 문자열 번호 매칭 해쉬맵
    static HashMap<String, Integer> nameMap;
    static HashMap<Integer, Integer> updateMap[];
    static int originArray[][];
    static int pre[];
    static int nxt[];
    static int originCnt;
    static int copyCnt;

    public static void init() {
        originCnt = 0;
        copyCnt = 11;
        nameMap = new HashMap<>();
        updateMap = new HashMap[6000];
        originArray = new int[10 + 1][200000 + 1];
        pre = new int[6000];
        nxt = new int[6000];

        for (int i = 0; i < 6000; i++) {
            updateMap[i] = new HashMap<>();
        }

    }

    public static void makeList(char mName[], int mLength, int mListValue[]) {
        StringBuilder sb = new StringBuilder();
        for (char c : mName) {
            if (c == '\0')
                break;
            sb.append(c);
        }
        nameMap.put(sb.toString(), ++originCnt);
        System.arraycopy(mListValue, 0, originArray[originCnt], 0, mLength);

    }

    public static void copyList(char mDest[], char mSrc[], boolean mCopy) {
        // 연결시켜두기 mCopy가 true면 값복사이므로 nxt만 추가 false면 pre도 추가해서 양방향 추가
    }

    public static void updateElement(char mName[], int mIndex, int mValue) {
        // 빅오 1
        // 마지막 함수 nxt에서 마지막만 업데이트
    }

    public static int element(char mName[], int mIndex) {
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
