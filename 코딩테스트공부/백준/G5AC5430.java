package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class G5AC5430 {
    static String nv[];

    static String ac(Deque<Integer> deque) {
        boolean reverse = false;

        for (int j = 0; j < nv.length; j++) {
            if (nv[j].equals("R"))
                reverse = !reverse;
            else {
                if (deque.size() == 0)
                    return "error";

                if (reverse)
                    deque.removeLast();
                else
                    deque.removeFirst();
            }
        }

        StringBuilder sb = new StringBuilder("[");
        while (!deque.isEmpty()) {
            sb.append(reverse ? deque.removeLast() : deque.removeFirst());
            if (deque.size() != 0)
                sb.append(',');
        }
        sb.append(']');

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            Deque<Integer> dq = new LinkedList<>();

            nv = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");

            for (int j = 0; j < n; j++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            System.out.println(ac(dq));
        }

    }
}

// https://www.acmicpc.net/problem/5430