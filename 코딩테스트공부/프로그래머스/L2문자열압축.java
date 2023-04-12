package 코딩테스트공부.프로그래머스;

import java.io.*;

public class L2문자열압축 {
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(solution(s));

    }

    static public int solution(String s) {
        int answer = 0;

        for (int i = 1; i <= s.length(); i++) {
            int count = 1;
            String compression = "";
            String temp = "";

            for (int j = 0; j < s.length() + i; j += i) {
                String nows;
                if (j >= s.length()) {
                    nows = "";
                } else if (j + i > s.length()) {
                    nows = s.substring(j);
                } else {
                    nows = s.substring(j, j + i);
                }

                if (j != 0) {
                    if (temp.equals(nows)) {
                        count++;
                    } else if (count >= 2) {
                        compression += count + temp;
                        count = 1;
                    } else {
                        compression += temp;
                    }
                }

                temp = nows;

            }

            min = Math.min(min, compression.length());
        }
        answer = min;
        return answer;
    }
}
