package 코딩테스트공부.B형공부.Pro섬지키기14596;

class UserSolution {
    static int size;
    static int islandMap[][];
    static int tempMap[][];

    public void init(int N, int mMap[][]) {
        size = N;
        islandMap = new int[N][N];
        tempMap = new int[2][N];

        for (int i = 0; i < N; i++) {
            islandMap[i] = mMap[i].clone();

        }

    }

    public int numberOfCandidate(int M, int mStructure[]) {
        int res = 0;
        for (int i = 0; i < size; i++) {
            // 가로
            for (int j = 0; j < size; j++) {
                if (j + M - 1 >= size)
                    break;
                for (int k = 0; k < M; k++) {
                    tempMap[0][j + k] = islandMap[i][j + k];
                    tempMap[1][j + k] = islandMap[i][j + k];
                }
                for (int k = 0; k < M; k++) {
                    tempMap[0][j + k] += mStructure[k];
                    tempMap[1][j + k] += mStructure[M - 1 - k];
                }
                boolean flags[] = new boolean[2];
                for (int k = 0; k < M - 1; k++) {
                    for (int z = 0; z < 2; z++) {
                        if (tempMap[z][j + k] != tempMap[z][j + k + 1])
                            flags[z] = true;
                    }
                }
                if (!flags[0] || !flags[1])
                    res++;

            }
            // 세로
            if (M == 1)
                continue;
            for (int j = 0; j < size; j++) {
                if (j + M - 1 >= size)
                    break;
                for (int k = 0; k < M; k++) {
                    tempMap[0][j + k] = islandMap[j + k][i];
                    tempMap[1][j + k] = islandMap[j + k][i];
                }
                for (int k = 0; k < M; k++) {
                    tempMap[0][j + k] += mStructure[k];
                    tempMap[1][j + k] += mStructure[M - 1 - k];
                }
                boolean flags[] = new boolean[2];
                for (int k = 0; k < M - 1; k++) {
                    for (int z = 0; z < 2; z++) {
                        if (tempMap[z][j + k] != tempMap[z][j + k + 1])
                            flags[z] = true;
                    }
                }
                if (!flags[0] || !flags[1])
                    res++;
            }
        }
        return res;
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        return 0;
    }
}