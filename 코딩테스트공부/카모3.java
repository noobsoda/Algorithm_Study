package 코딩테스트공부;

import java.util.*;

public class 카모3 {

    public static void main(String[] args) {
        // String[] times = { "01:06:30:00", "01:04:12:00" };
        // solution("2021:04:12:16:08:35", times);
        String[] times = { "01:06:30:00", "00:01:12:00" };
        System.out.println(Arrays.toString(solution("2021:04:12:16:08:35", times)));
    }

    public static int[] solution(String s, String[] times) {
        int start = 0;
        int last = 0;
        int[] answer = new int[2];
        StringTokenizer st = new StringTokenizer(s, ":");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int sec = Integer.parseInt(st.nextToken());

        Time time = new Time(year, month, day, hour, min, sec);
        start = day;
        boolean flag = false;

        for (String t : times) {
            st = new StringTokenizer(t, ":");
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int seconds = Integer.parseInt(st.nextToken());

            time.sec += seconds;
            time.min += m;
            time.hour += h;
            time.day += d;

            if (time.sec >= 60) {
                time.min += 1;
                time.sec %= 60;
            }
            if (time.min >= 60) {
                time.hour += 1;
                time.min %= 60;
            }
            if (time.hour >= 24) {
                time.day += 1;
                time.hour %= 24;
            }

            if (last == 0) {
                last = time.day;
            } else if (time.day - last > 1) {
                flag = true;
            }
            last = time.day;

        }
        answer[0] = flag ? 0 : 1;
        answer[1] = last - start + 1;

        return answer;
    }

    static class Time {
        int year;
        int month;
        int day;
        int hour;
        int min;
        int sec;

        public Time(int year, int month, int day, int hour, int min, int sec) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }

        @Override
        public String toString() {
            return "Time [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", min=" + min
                    + ", sec=" + sec + "]";
        }

    }

}
