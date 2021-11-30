import java.io.*;
import java.util.*;

public class G5치킨배달15686 {
    static int N, M;
    static int map[][];
    static List<Point> list;
    static List<Point> nlist;
    static PriorityQueue<Integer> pq;


    public static void check(){
        int sum = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1){
                    int min = N*2+1;
                    for(Iterator<Point> it = nlist.iterator(); it.hasNext();){
                        Point p = it.next();
                        min = Math.min(Math.abs(p.x - i) + Math.abs(p.y - j), min);
                    }
                    sum += min;
                }
            }
        }
        pq.add(sum);
        
    }
    //조합을 통해 치킨집 돌리기
    public static void chickendinner(int index, int depth){
        //두개를 정해서 두개 중 어느 집이 더 가까운지 Min
        if(depth == M){
            check();
            return;
        }
        else{
            for(int i = index+1; i < list.size(); i++){
                int x = list.get(i).x;
                int y = list.get(i).y;
                nlist.add(new Point(x, y));
                chickendinner(i, depth+1);
                nlist.remove(nlist.size()-1);

            }
            return;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        M = Integer.parseInt(nv[1]);
        map = new int[N][N];
        list = new LinkedList<>();
        nlist = new LinkedList<>();
        pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
                
                if(map[i][j] == 2){
                    list.add(new Point(i, j));
                }
            }
        }
        chickendinner(-1, 0);

        System.out.println(pq.peek());



        
    }
    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
}

//https://www.acmicpc.net/problem/15686