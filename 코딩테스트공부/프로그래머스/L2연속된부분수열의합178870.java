package 코딩테스트공부.프로그래머스;

import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[1] = 1000000000;
        // 과거부터의합과 지금 시작의 max 값을 비교
        // 슬라이딩 윈도우 트라이
        // 작으면 뒤에꺼 크면 앞에꺼
        int end = 0;
        int start = 0;
        int sum = 0;
        while (start <= sequence.length) {
            // 만약 end가 범위를 벗어났다면 그만!

            if (sum < k) {
                if (end >= sequence.length) {
                    break;
                }
                sum += sequence[end];
                end++;
            } else if (sum > k) {
                sum -= sequence[start];
                start++;

            } else {
                int nowEnd = end - 1;
                if (answer[1] - answer[0] > nowEnd - start) {
                    answer[0] = start;
                    answer[1] = nowEnd;
                }
                sum -= sequence[start];
                start++;

            }

        }

        return answer;
    }
}