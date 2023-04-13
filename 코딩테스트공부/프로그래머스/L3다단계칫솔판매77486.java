package 코딩테스트공부.프로그래머스;

import java.util.*;

@SuppressWarnings("unchecked")
public class L3다단계칫솔판매77486 {
    static ArrayList<Integer> arr[];
    static HashMap<String, Integer> hmap;
    static Queue<Integer> q;
    static int[] answer;

    public static void main(String[] args) {
        String enroll[] = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
        String referral[] = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
        String sellor[] = { "young", "john", "tod", "emily", "mary" };
        int amount[] = { 12, 4, 2, 5, 10 };
        System.out.println(Arrays.toString(solution(enroll, referral, sellor, amount)));
    }

    public static void bfs(int n, int money) {
        q.add(n);

        while (!q.isEmpty()) {
            int now = q.poll();
            int minusmoney = money / 10;
            if (now - 1 < 0)
                continue;
            answer[now - 1] += money - minusmoney;

            // 1원 미만일 경우
            if (minusmoney == 0)
                return;

            // 10분의 1 만들기
            money = minusmoney;

            for (int v : arr[now]) {
                q.add(v);
            }
        }
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;

        arr = new ArrayList[N + 1];
        hmap = new HashMap<>();
        answer = new int[N];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
            hmap.put(enroll[i], i + 1);
        }
        hmap.put("-", 0);
        arr[N] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int a = hmap.get(enroll[i]);
            int b = hmap.get(referral[i]);

            arr[a].add(b);
        }

        // bfs
        for (int i = 0; i < seller.length; i++) {
            int n = hmap.get(seller[i]);
            int money = amount[i] * 100;
            bfs(n, money);
        }

        return answer;
    }

}