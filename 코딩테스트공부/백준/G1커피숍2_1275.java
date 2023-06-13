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
        int[] tree;
        int[] arr;

        public SegmentTree(int[] arr) {
            int n = arr.length;
            tree = new int[4 * n];
            this.arr = arr;
            buildTree(1, 0, n - 1);
        }

        private void buildTree(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;
            buildTree(node * 2, start, mid);
            buildTree(node * 2 + 1, mid + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public void update(int idx, int val) {
            updateTree(1, 0, arr.length - 1, idx, val);
        }

        private void updateTree(int node, int start, int end, int idx, int val) {
            if (start == end) {
                arr[idx] = val;
                tree[node] = val;
                return;
            }

            int mid = (start + end) / 2;
            if (idx <= mid) {
                updateTree(node * 2, start, mid, idx, val);
            } else {
                updateTree(node * 2 + 1, mid + 1, end, idx, val);
            }
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public int query(int left, int right) {
            return queryTree(1, 0, arr.length - 1, left, right);
        }

        private int queryTree(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int sumLeft = queryTree(node * 2, start, mid, left, right);
            int sumRight = queryTree(node * 2 + 1, mid + 1, end, left, right);
            return sumLeft + sumRight;
        }
    }
}
// https://www.acmicpc.net/problem/1275