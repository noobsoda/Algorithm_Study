import java.io.*;
import java.util.*;

public class G2낚시왕17143 {
    static int R, C, M;
    static int map[][];
    static List<Shark> list;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, 1, -1};
    static int res = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new LinkedList<>();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];


        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r =  Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());
            int s =  Integer.parseInt(st.nextToken());
            int d =  Integer.parseInt(st.nextToken());
            int z =  Integer.parseInt(st.nextToken());

            list.add(new Shark(r-1, c-1, s, d-1, z));
            map[r-1][c-1] = z;
        }


        //낚시왕 이동 i는 낚시왕
        for(int i = 0; i < C; i++){

            //가까운 상어 get
            for(int j = 0; j < R; j++){
                if(map[j][i] != 0){
                    res += map[j][i];
                    map[j][i] = 0;
                    break;
                }
            }


            //상어 move
            for(Iterator<Shark> it = list.iterator(); it.hasNext();){
                Shark shark = it.next();
                if(map[shark.r][shark.c] == 0){
                    it.remove();
                }

                //샤크 무브
                shark.r += shark.r + dx[shark.d]*shark.s;
                shark.c += shark.c + dy[shark.d]*shark.s;


                //칸 넘어갈 때
                

                //벽 부딫혔을 때
                boolean flag = false;
                while(shark.r < 0 || shark.c < 0 || shark.r >= R || shark.c >= C){
                    flag = false;
                    if(shark.r < 0){
                        shark.r = Math.abs(shark.r);
                        shark.d = (shark.d + 1) % 2;
                    }
                    else if(shark.c < 0){
                        shark.c = Math.abs(shark.c);
                        shark.d = (shark.d + 1) % 2;
                    }
                    else if(shark.r >= R){
                        flag = true;
                        if(++shark.d % 4 == 0)
                            shark.d = 3;
                    }
                    else if(shark.c >= C){
                        flag = true;
                        if(++shark.d % 4 == 0)
                            shark.d = 3;
                    }


                }
                if(flag){
                    
                }


            }


        }
        System.out.println(res);







    }    
    static class Shark{
        int r,c,s,d,z;
        public Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;            
        }
    }
}
