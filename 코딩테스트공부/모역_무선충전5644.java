package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class 모역_무선충전5644 {
    static int M, A;
    static int[] HA, HB;
    static int map[][];
    static ArrayList<Node> arr;
    static Node nA, nB;
    static int dx[] = {0, -1, 0, 1, 0};
    static int dy[] = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
            
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            HA = new int[M];
            HB = new int[M];
            map = new int[11][11];
            arr = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                HA[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                HB[i] = Integer.parseInt(st.nextToken());
            }


            for(int i = 0; i < A; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                arr.add(new Node(y, x, c, p));                
            }
            nA = new Node(1, 1);
            nB = new Node(10, 10);
            System.out.println("#" + test_case + " " + 0);
		}
    }
    static class Node{
        int x, y, c, p;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int c, int p){
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }
}
//똑같은곳에서 동시에 범위 받는게 있을 때 
//조건에 해당하면 조합