import java.io.*;
import java.util.*;

public class S1컨베이어벨트위로봇20055 {
    static int N, K;
    static int map[];
    static boolean robot[];
    static LinkedList<Node> list;

    //현재 로봇 위치와 레일 칸수 옮기기
    public static void railmov(){
        int temp = map[2*N-1];
        boolean temprobot = robot[2*N-1];
        for(int i = 2*N-1; i > 0; i--){
            map[i] = map[i-1];
            robot[i] = robot[i-1];            
        }
        if(robot[N-1])
            robot[N-1] = false;
        map[0] = temp;
        robot[0] = temprobot;
    }

    //로봇 움직임
    public static int robotmov(){
        int stop = 0;
        
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            Node now = it.next();
            //레일 움직였으니 +1씩
            now.index++;
            //로봇 위치가 N에 도달시 로봇 삭제
            if(now.index == N-1){
                robot[now.index] = false;
                it.remove();
                continue;
            }
            //로봇이 움직일 수 있는지 확인
            if(map[now.index+1] > 0 && !robot[now.index+1]){
                map[now.index+1]--;
                //로봇이 움직여서 N에 도달시 로봇 삭제
                if(now.index+1 == N-1){
                    robot[now.index] = false;
                    it.remove();
                    continue;
                }

                //로봇이 N에 도달하지 않았으면 로봇 위치 다음칸으로 이동                
                robot[now.index+1] = true;
                robot[now.index] = false;
                now.index++;
            }
            
            
        }


        for(int i = 0; i < 2*N; i++){
            if(map[i] <= 0)
                stop++;
        }
        return stop;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nv = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        K = Integer.parseInt(nv[1]);

        map = new int[N*2+1];
        robot = new boolean[N*2+1];
        list = new LinkedList<>();

        nv = br.readLine().split(" ");
        for(int i = 0; i < N*2; i++){
            map[i] = Integer.parseInt(nv[i]);
        }
        int t = 0;
        int stop = 0;

        while(stop < K){            
            railmov();
            stop = robotmov();
            if(map[0] > 0){
                list.add(new Node(0));
                map[0] -= 1;
                robot[0] = true;
                if(map[0] == 0)
                    stop++;
            }
            t++;
        }
        System.out.println(t);
    }
    static class Node{
        int index;
        public Node(int index){
            this.index = index;
        }
    }
    
}
//https://www.acmicpc.net/problem/20055