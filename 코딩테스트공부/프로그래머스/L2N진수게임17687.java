package 코딩테스트공부.프로그래머스;

public class L2N진수게임17687 {
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        // 진수 바꿔서 문자열로 나열 t*m까지 100000까지 가능
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (sb.toString().length() < t * m) {
            sb.append(Integer.toString(cnt++, n));
        }
        String res = sb.toString();
        res = res.toUpperCase();

        // 만약 문자열 길이 t*m넘어가면 break;

        sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sb.append(res.charAt(i * m + p - 1));
        }
        answer = sb.toString();
        return answer;
    }
}
