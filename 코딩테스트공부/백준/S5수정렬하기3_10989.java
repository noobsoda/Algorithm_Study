package 코딩테스트공부.백준;

import java.io.*;
import java.util.StringTokenizer;

public class S5수정렬하기3_10989 {
    static int N;
    static int list[] = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            list[n]++;
        }
        for (int n = 0; n < list.length; n++) {
            for (int i = 0; i < list[n]; i++) {
                bw.write(n + "\n");
            }
        }
        bw.flush();
        bw.close();

    }
}
