package 코딩테스트공부.B형공부.Pro섬지키기14596;

import java.util.HashMap;
import java.util.Map;

class UserSolution {
    static int size;
    static Map<String, Integer> hMap;

    public void init(int N, int mMap[][]) {
        size = N;
        hMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int m = 0;
                StringBuilder sb = new StringBuilder();
                int temp = -1;
                // 가로
                while (j + m < size) {
                    if (m == 0)
                        temp = mMap[i][j + m];
                    else {
                        sb.append(temp - mMap[i][j + m] + 5);
                        temp = mMap[i][j + m];
                        if (hMap.containsKey(sb.toString())) {
                            hMap.put(sb.toString(), hMap.get(sb.toString()) + 1);
                        } else {
                            hMap.put(sb.toString(), 1);
                        }
                    }
                    m++;
                }
                // 세로
                m = 0;
                sb = new StringBuilder();
                while (i + m < size) {
                    if (m == 0)
                        temp = mMap[i + m][j];
                    else {
                        sb.append(temp - mMap[i + m][j] + 5);
                        temp = mMap[i + m][j];
                        if (hMap.containsKey(sb.toString())) {
                            hMap.put(sb.toString(), hMap.get(sb.toString()) + 1);
                        } else {
                            hMap.put(sb.toString(), 1);
                        }
                    }
                    m++;
                }

            }

        }

    }

    public int numberOfCandidate(int M, int mStructure[]) {
        int res = 0;
        int temp = -1;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        if (M == 1) {
            return size * size;
        }
        for (int i = 0; i < M; i++) {
            if (i == 0)
                temp = mStructure[i];
            else {
                sb1.append(temp - mStructure[i] + 5);
                sb2.insert(0, temp - mStructure[i] + 5);
                temp = mStructure[i];
            }

        }

        if (sb1.toString().equals(sb2.toString())) {
            if (hMap.containsKey(sb1.toString())) {
                res += hMap.get(sb1.toString());
            }
        } else {
            if (hMap.containsKey(sb1.toString())) {
                res += hMap.get(sb1.toString());
            }
            if (hMap.containsKey(sb2.toString())) {
                res += hMap.get(sb2.toString());
            }
        }

        return res;
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel) {
        return 0;
    }
}