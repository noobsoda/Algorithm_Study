package 코딩테스트공부;

import java.util.*;

class L2오픈채팅방42888 {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<String, String>();
        StringTokenizer st;
        int cnt = 0;

        for (int i = 0; i < record.length; i++) {
            st = new StringTokenizer(record[i]);
            String first = st.nextToken();
            String id = st.nextToken();

            if (first.equals("Leave")) {
                cnt++;
            } else if (first.equals("Enter")) {
                cnt++;
                map.put(id, st.nextToken());
            } else {
                map.put(id, st.nextToken());
            }
        }

        String[] answer = new String[cnt];
        cnt = 0;

        for (int i = 0; i < record.length; i++) {
            st = new StringTokenizer(record[i]);
            String first = st.nextToken();
            if (first.equals("Enter")) {
                answer[cnt++] = map.get(st.nextToken()) + "님이 들어왔습니다.";
            } else if (first.equals("Leave")) {
                answer[cnt++] = map.get(st.nextToken()) + "님이 나갔습니다.";
            }
        }

        return answer;
    }

}