import java.io.*;
import java.util.*;

public class G2다리만들기2_17472 {
    static int N, M, depthmax;
    static int map[][];
    static int parents[];
    static boolean visited[][];
    static Queue<Node> q;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};   
    static int shortestPath() {
		int sum =0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int x = now.x;
			int y = now.y;
			
			if(find(x) != find(y)) {
				sum += now.value;
				union(x,y);
			}
		}
		
		int rx = parents[0];
		for(int i = 1; i < depthmax; i++) {
			if(rx != find(parents[i])) {				
				return -1;
			}
		}
		
		return sum;
	}

    static void makeBridge(int x, int y) {
        int color = map[x][y];
        q = new LinkedList<>();	
        visited = new boolean[N][M];
        for(int i=0; i<4; i++) {
            q.add(new Node(x, y, 0));
            visited[x][y] = true;
            
            while(!q.isEmpty()) {
                Node now = q.poll();               
                
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
                
                if(map[nx][ny] != color) {
                    if(map[nx][ny] != 0) {
                        int from = color-1;
                        int to = map[nx][ny]-1;
                        int bridgeLen = now.value;
                        if(bridgeLen > 1) {		
                            pq.add(new Node(from, to, bridgeLen));
                            break;
                        }
                    }else {
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny, now.value+1));
                    }
                }
            }
            q.clear();
        }
    }
       
    //섬 가르기
    public static void division(int x, int y, int color){
        visited[x][y] = true;
        map[x][y] = color;
        q.add(new Node(x, y, 0));

        while(!q.isEmpty()){
            Node now = q.poll();

            
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

                if(map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    map[nx][ny] = color;
                    q.add(new Node(nx, ny, 0));
                }
            }
            
        }
    }
    public static void bridge2(){    
        int color = 1;
        //다른 지역 표시
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    division(i, j, color);
                    color++;
                }
            }
        }
        //섬 갯수
        depthmax = color-1;
        parents = new int[depthmax];

        //다리 노드 찾기
        for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					makeBridge(i, j);
				}
			}
		}

        //노드 연결되있는지 확인할 배열
        for(int i = 1; i < depthmax; i++) {
			parents[i] = i;
		} 
        int answer = shortestPath();

        
        System.out.println(answer);
        

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        
        q = new LinkedList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        

        bridge2();

        
    }    
    static class Node implements Comparable<Node>{
        int x, y, value;
        public Node(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Node o){
            return this.value - o.value;
        }
    }
    static int find(int x) {
		if(parents[x] == x) 
            return x;
        parents[x] = find(parents[x]);		
        return parents[x];
		
		
	}
	
	static void union(int x, int y){
        int xroot = find(x);
        int yroot = find(y);

        if(xroot != yroot){
            parents[yroot] = x;
        }
        else return;
    }
}
//https://www.acmicpc.net/problem/17472