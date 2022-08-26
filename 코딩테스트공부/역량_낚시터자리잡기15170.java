package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class 역량_낚시터자리잡기15170 {
    static int N, min;
    static int door[];
    static boolean visited[];
    static int fisherman[];
    static int map[];

    public static void Fill(int state, int select, int tempmap[]){
        //0이면 왼쪽부터 채우기
        int cnt = 0;
        int idx = 1;
        switch(state){
            case 0:
                if(tempmap[door[select]] == 0){
                    tempmap[door[select]] = idx;
                    cnt++;
                }
                while(cnt < fisherman[select]){
                    for(int i = 0; i < 2; i++){
                        if(door[select]-idx > 0 && tempmap[door[select]-idx] == 0){
                            tempmap[door[select]-idx] = idx+1;
                            cnt++;
                        }
                        if(cnt >= fisherman[select]) break;

                        if(door[select]+idx < N+1 && tempmap[door[select]+idx] == 0){
                            tempmap[door[select]+idx] = idx+1;
                            cnt++;
                        }
                        
                    }

                    idx++;
                }
                break;
            //1이면 오른쪽부터 채우기
            case 1:
                if(tempmap[door[select]] == 0){
                    tempmap[door[select]] = idx;
                    cnt++;
                }
                while(cnt < fisherman[select]){
                    for(int i = 0; i < 2; i++){
                        if(door[select]+idx < N+1 && tempmap[door[select]+idx] == 0){
                            tempmap[door[select]+idx] = idx+1;
                            cnt++;
                        }
                        if(cnt >= fisherman[select]) break;

                        if(door[select]-idx > 0 && tempmap[door[select]-idx] == 0){
                            tempmap[door[select]-idx] = idx+1;
                            cnt++;
                        }
                        
                    }
                    
                    idx++;
                }
                break;
        }



    }

    public static void Permu(int depth, int tempmap[]){
        if(depth == 3){
            int sum = 0;
            for(int i = 1; i <= N; i++)
                sum += tempmap[i];
            min = Math.min(sum, min);

            System.out.println(Arrays.toString(tempmap));

            return;
        }
        int tempmap2[] = new int[N+1];



        for(int i = 0; i < 3; i++){
            if(visited[i]) continue;

            visited[i] = true;
            //게이트 코드
            for(int j = 0; j < 2; j++){
                System.arraycopy(tempmap, 0, tempmap2, 0, N+1);
                Fill(j, i, tempmap2);
                Permu(depth+1, tempmap2);
                //짝수일 경우에만 2번
                if(fisherman[i] % 2 != 0)
                    break;
            }
            visited[i] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            door = new int[3];
            fisherman = new int[3];
            visited = new boolean[3];
            map = new int[N+1];

            for(int i = 0; i < 3; i++){
                st = new StringTokenizer(br.readLine());
                door[i] = Integer.parseInt(st.nextToken());
                fisherman[i] = Integer.parseInt(st.nextToken());
            }


            Permu(0, map);

            System.out.println("#" + test_case + " " + min);

            
        }
    }   
}
// 5
// 10
// 4 5
// 6 2
// 10 2
// 10
// 8 5
// 9 1
// 10 2
// 24
// 15 3
// 20 4
// 23 7
// 39
// 17 8
// 30 5
// 31 9
// 60
// 57 12
// 31 19
// 38 16
