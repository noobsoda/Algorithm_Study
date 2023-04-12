package 코딩테스트공부.프로그래머스;

import java.util.*;

public class L4호텔방배정64063 {
    static HashMap<Long, Long> hmap;

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        hmap = new HashMap<>();
        int cnt = 0;
        for (long n : room_number) {
            answer[cnt++] = find(n);
        }
        return answer;
    }

    public long find(long room) {
        if (!hmap.containsKey(room)) {
            hmap.put(room, room + 1);
            return room;
        }
        long nextroom = hmap.get(room);
        long emptyroom = find(nextroom);
        hmap.put(room, emptyroom);
        return emptyroom;

    }
}
// https://programmers.co.kr/learn/courses/30/lessons/64063