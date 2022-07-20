import java.io.*;
import java.util.*;

public class G4드래곤커브15685 {
    final static int SX = 100;
    final static int SY = 100;
    static int N;
    static boolean map[][];
    static int dx[] = {0, -1, 0, 1};
    static int dy[] = {1, 0, -1, 0};
    static int ex, ey, res = 0;
    //오른쪽, 위, 왼쪽, 아래 순서
    static List<Dragon> list;

    public static void curving(int x, int y){

        List<Dragon> templist = new LinkedList<>();
        boolean flag = false;
        for(Iterator<Dragon> it = list.iterator(); it.hasNext();){
                        
            Dragon warrior = it.next();
            int nx = warrior.x - x;
            int ny = warrior.y - y;
            

            // -1 , 0 곱하고
            nx *= -1;
            

            //swap하면 90도 회전
            nx += y;
            ny += x;


            map[SX + ny][SY + nx] = true;

            //끝 점 할당
            if(!flag){
                flag = true;
                ex = ny;
                ey = nx;  
                //System.out.println(ny + " " + nx);
                continue;          
            }
            
            templist.add(new Dragon(ny, nx));
            //System.out.println(ny + " " + nx);
        }


        templist.add(new Dragon(x, y));
        list.addAll(templist);

        //System.out.println(x + " " + y);
        map[SX + x][SY + y] = true;

    }

    public static void dragonCurve(int g){

        //드래곤
        for(int i = 0; i < g; i++){
            curving(ex, ey);
        }
        //System.out.println();
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new boolean[201][201];


        for(int i = 0; i < N; i++){
            list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[SX+x][SY+y] = true;

            list.add(new Dragon(x, y));  
            ex = x + dx[d];
            ey = y + dy[d];
            map[SX+ex][SY+ey] = true;
            //드래곤 워리어
            dragonCurve(g);
            
     
        }
        for(int i = 0; i < 200; i++){
            for(int j = 0; j < 200; j++){
                boolean flag = true;
                
                for(int ni = i; ni < i + 2; ni++){
                    for(int nj = j; nj < j + 2; nj++){
                        if(!map[ni][nj]) flag = false;
                        
                    }
                }
                if(flag)res++;

            }
        }
        System.out.println(res);


    }
    static class Dragon{
        int x, y;
        public Dragon(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
//https://www.acmicpc.net/problem/15685