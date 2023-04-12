package 코딩테스트공부.코테;

public class 카모1 {
    static int map[];

    public static void main(String[] args) {
        solution(new int[][] { { 2, 5 }, { 3, 7 }, { 10, 11 } });
    }

    public static int solution(int[][] flowers) {
        int answer = 0;
        map = new int[365 + 1];

        for (int n[] : flowers) {
            map[n[0]] += 1;
            map[n[1]] += -1;
        }

        for (int i = 0; i < 365; i++) {
            map[i + 1] += map[i];
            if (map[i + 1] != 0) {
                answer++;
            }
        }

        return answer;
    }
}
