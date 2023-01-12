package 코딩테스트공부;

import java.util.*;

public class 카카오코테2 {
    public static void main(String[] args) {
        long n = solution(4, 5, new int[] { 4, 4, 2, 4, 2 }, new int[] { 4, 4, 2, 4, 2 });

        System.out.println(n);

    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Node> dst = new Stack<>();
        Stack<Node> pst = new Stack<>();
        long answer = 0;

        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0)
                dst.push(new Node(i + 1, deliveries[i]));
            if (pickups[i] != 0)
                pst.push(new Node(i + 1, pickups[i]));
        }

        while (!dst.empty() || !pst.empty()) {
            int dist = 0;
            int dcap = cap;
            int pcap = cap;
            if (!dst.empty()) {
                Node del = dst.pop();
                dist = del.cnt;

                // 배송지
                dcap -= del.m;
                if (dcap > 0) {
                    while (!dst.empty()) {
                        Node now = dst.pop();
                        dcap -= now.m;

                        if (dcap < 0) {
                            now.m = Math.abs(dcap);
                            dst.push(now);
                            break;
                        }
                    }
                } else if (dcap < 0) {
                    del.m = Math.abs(dcap);
                    dst.push(del);
                }

            }
            if (!pst.empty()) {

                Node pic = pst.pop();
                pcap -= pic.m;

                if (pic.cnt > dist)
                    dist = pic.cnt;

                if (pcap > 0) {
                    while (pcap > 0 && !pst.empty()) {
                        Node now = pst.pop();
                        pcap -= now.m;

                        if (pcap < 0) {
                            now.m = Math.abs(pcap);
                            pst.push(now);
                            break;
                        }
                    }
                } else if (pcap < 0) {
                    pic.m = Math.abs(pcap);
                    pst.push(pic);
                }

            }

            answer += dist * 2;
            System.out.println(dist);
        }

        // 갈떄는 멀리 있는거
        // 올 때는 가까이 있는거

        // 배달 다 했는데 아직 픽업할게 멀리 있으면 더 가야 함

        return answer;
    }

    static class Node {
        int cnt, m;

        public Node(int cnt, int m) {
            this.cnt = cnt;
            this.m = m;
        }
    }

}

// 14
// 10
// 6