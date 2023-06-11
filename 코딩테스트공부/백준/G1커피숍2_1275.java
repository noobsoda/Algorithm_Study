package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G1커피숍2_1275 {
    static int N, Q;
    static int arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

        }
        SegmentTree segmentTree = new SegmentTree(arr);

        System.out.println(segmentTree.query(0, 2));

    }

    static class SegmentTree {
        public SegmentTree(int arr[]) {

        }

        public int query(int left, int right) {
            return 0;
        }
    }
}
// https://www.acmicpc.net/problem/1275