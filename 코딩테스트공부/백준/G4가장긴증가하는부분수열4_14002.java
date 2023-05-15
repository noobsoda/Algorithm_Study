package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G4가장긴증가하는부분수열4_14002 {
    static int N;
    static int dp[], arr[];
    static Node nodes[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N];
        arr = new int[N];
        nodes = new Node[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            nodes[i] = new Node(arr[i]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (i == j)
                    continue;
                if (arr[j] < arr[i]) {
                    if (dp[i] < dp[j] + 1) {
                        nodes[i].nxt = nodes[j];
                        dp[i] = dp[j] + 1;
                    }

                }
            }
        }
        int max = 0, maxIndex = 0;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
                maxIndex = i;
            }
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
        System.out.println(makeSequence(new StringBuilder(), nodes[maxIndex]));

    }

    public static StringBuilder makeSequence(StringBuilder sb, Node node) {

        if (node.nxt != null) {
            makeSequence(sb, node.nxt);
        }
        sb.append(node.n + " ");

        return sb;
    }

    static class Node {
        Node nxt;
        int n;

        public Node(int n) {
            this.n = n;
            this.nxt = null;
        }

        public Node(int n, Node nxt) {
            this.n = n;
            this.nxt = nxt;
        }
    }
}
// https://www.acmicpc.net/problem/14002