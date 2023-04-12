package 코딩테스트공부.백준;

import java.io.*;

public class S5크로아티아알파벳2941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int res = 0;

        String croatia[] = new String[8];
        croatia[0] = "c=";
        croatia[1] = "c-";
        croatia[2] = "dz=";
        croatia[3] = "d-";
        croatia[4] = "lj";
        croatia[5] = "nj";
        croatia[6] = "s=";
        croatia[7] = "z=";

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < croatia.length; j++) {
                // i번쨰 문자와 char배열의 첫 번째 문자와 비교
                if (s.charAt(i) == croatia[j].charAt(0)) {
                    boolean flag = false;
                    // char배열의 길이 만큼 탐색해서 비교
                    for (int k = 0; k < croatia[j].length(); k++) {
                        if (i + k >= s.length()) {
                            flag = false;
                            break;
                        }
                        if (s.charAt(i + k) != croatia[j].charAt(k)) {
                            flag = false;
                            break;
                        }
                        flag = true;
                    }
                    // 해당 길이 만큼 i 뒤로 땡김
                    if (flag) {
                        i += croatia[j].length() - 1;
                        break;
                    }
                }

            }
            // 문자 플라스
            res++;
        }
        System.out.println(res);

    }
}
// https://www.acmicpc.net/problem/2941