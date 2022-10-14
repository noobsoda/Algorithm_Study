package 코딩테스트공부;

public class L1비밀지도17681 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            String s = Integer.toBinaryString(arr1[i] | arr2[i]);
            StringBuilder sb = new StringBuilder();

            if (s.length() < n) {
                for (int j = 0; j < n - s.length(); j++) {
                    sb.append(" ");
                }
            }
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();

        }
        return answer;
    }
}
