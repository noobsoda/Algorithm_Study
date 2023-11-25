package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G1구간합구하기1_2042 {
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        DynamicSegmentTree tree = new DynamicSegmentTree(4000000);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            long n = Long.parseLong(st.nextToken());
            tree.update(i+1, n);
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1)
                tree.update(b, c);
            else
                System.out.println(tree.query(b, c));
        }

        

    }
    static class SegmentTreeNode {
        long sum;
        long start, end;
        SegmentTreeNode left, right;
    
        public SegmentTreeNode(long start, long end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.left = this.right = null;
        }
    }
    public static class DynamicSegmentTree {
        private SegmentTreeNode root;
        private int maxRange;
    
        public DynamicSegmentTree(int maxRange) {
            this.root = new SegmentTreeNode(0, maxRange);
            this.maxRange = maxRange;
        }
    
        public void update(int index, long value) {
            update(root, index, value);
        }
    
        private void update(SegmentTreeNode node, int index, long value) {
            if (node.start == node.end) {
                node.sum = value;
                return;
            }
    
            long mid = getMid(node.start, node.end);
            if (index <= mid) {
                if (node.left == null) {
                    node.left = new SegmentTreeNode(node.start, mid);
                }
                update(node.left, index, value);
            } else {
                if (node.right == null) {
                    node.right = new SegmentTreeNode(mid + 1, node.end);
                }
                update(node.right, index, value);
            }
    
            node.sum = getSum(node.left) + getSum(node.right);
        }
    
        public long query(long start, long end) {
            return query(root, start, end);
        }
    
        private long query(SegmentTreeNode node, long start, long end) {
            if (node == null || start > node.end || end < node.start) {
                return 0;
            }
    
            if (start <= node.start && end >= node.end) {
                return node.sum;
            }
    
            long mid = getMid(node.start, node.end);
            return query(node.left, start, Math.min(mid, end)) + query(node.right, Math.max(mid + 1, start), end);
        }
    
        private long getMid(long start, long end) {
            return start + (end - start) / 2;
        }
    
        private long getSum(SegmentTreeNode node) {
            if (node == null) {
                return 0;
            }
            return node.sum;
        }
    }
}
//https://www.acmicpc.net/problem/2042