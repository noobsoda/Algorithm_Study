package 코딩테스트공부.코테;

public class O키보드고장 {
    public static void main(String[] args) {
        System.out.println(solution("010"));
    }

    public static int solution(String number) {
        int answer = 0;
        for (int i = 0; i < number.length(); i++) {
            int first = number.charAt(i) - '0';
            int second;

            if (i + 1 == number.length()) {
                second = -99;
            } else {
                second = number.charAt(i + 1) - '0';
            }

            if (first == 0) {

            } else if (first + 1 == second) {
                i++;
            } else {
                answer++;
            }

            answer++;

        }
        return answer;

    }
}
