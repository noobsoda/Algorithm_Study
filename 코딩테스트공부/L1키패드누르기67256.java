package 코딩테스트공부;

public class L1키패드누르기67256 {
    static int left = -1, right = -2;
    static int map[][];

    public static void main(String[] args){
        System.out.println(solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
    }
    public static String solution(int[] numbers, String hand) {
        String answer = "";
        map = new int[4][3];
        hand = hand.toUpperCase();
        
        int cnt = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                map[i][j] = cnt++;
            }
        }
        map[3][0] = -1;
        map[3][1] = 0;
        map[3][2] = -2;
        
        
        StringBuilder sb = new StringBuilder();
        for(int n : numbers){
            if(n == 1 || n == 4 || n == 7){
                left = n;
                sb.append('L');
            }
            else if(n == 3 || n == 6 || n == 9){
                right = n;
                sb.append('R');
            }
            else{
                int x = 0, y = 0 ,lx = 0, ly = 0, rx = 0, ry = 0;                
                for(int i = 0; i < 4; i++){
                    for(int j = 0; j < 3; j++){
                        if(map[i][j] == left){
                            lx = i;
                            ly = j;
                        }                        
                        if(map[i][j] == n){
                            x = i;
                            y = j;
                        }
                        if(map[i][j] == right){
                            rx = i;
                            ry = j;
                        }
                    }
                }
                int ldiff = Math.abs(lx - x) + Math.abs(ly - y);
                int rdiff = Math.abs(rx - x) + Math.abs(ry - y);
                System.out.println(ldiff + " " + rdiff);
                if(ldiff < rdiff){
                    left = n;
                    sb.append('L');
                }
                else if(ldiff > rdiff){
                    right = n;
                    sb.append('R');
                }
                else{
                    if(hand.charAt(0) == 'L')
                        left = n;
                    else
                        right = n;
                    
                    sb.append(hand.charAt(0));
                }
            }
        }
        answer = sb.toString();
        return answer;
    }
}