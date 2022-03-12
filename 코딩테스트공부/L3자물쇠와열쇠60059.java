package 코딩테스트공부;
import java.util.*;
import java.io.*;

public class L3자물쇠와열쇠60059 {
    static int N, M;  
        
    public static void rotation(int[][] key){
        int temp[][] = new int[M][M];
        for(int i = 0; i < M; i++)
            System.arraycopy(key[i], 0, temp[i], 0, key[i].length);
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                key[j][M-1-i] = temp[i][j];
            }
        }
    }
    public static int[][] setMap(int map[][]) {
		int len = map.length;
		int convert[][] = new int[len][len];

		for(int i = 0; i < len; i++) 
			convert[i] = map[i].clone();

		return convert;
	}
    public static boolean check(int map[][], int n, int m) {
		for(int i = m-1; i < n+m-1; i++) {
			for(int j = m-1; j < n+m-1; j++)
				if(map[i][j] == 0)
					return false;
		}
		
		return true;
	}
   
    public static boolean solution(int[][] key, int[][] lock) {        
        M = key.length;
        N = lock.length;

        int map[][] = new int[N + 2*(M-1)][N + 2*(M-1)];
        int temp[][] = new int[N + 2*(M-1)][N + 2*(M-1)];
        
        for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) 
				map[M-1+i][M-1+j] = lock[i][j];
		}
        for(int i = 0; i <= map.length-M; i++) {
			for(int j = 0; j <= map.length-M; j++) {
				l:	for(int k = 0; k < 4; k++) {
					//key 회전
					rotation(key);
					//temp 세탁
					temp = setMap(map);

					//자물쇠에 키를 꽂음
					for(int x = 0; x < M; x++) {
						for(int y = 0; y < M; y++) {
							if(temp[i+x][j+y] == 1 && key[x][y] == 1)
								continue l;
							else if(temp[i+x][j+y] == 0 && key[x][y] == 1)
								temp[i+x][j+y] = 1;
						}
					}
					//자물쇠 검사
					if(check(temp, N, M))
						return true;
				}
			}
		}

		return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int key[][] = new int[M][M];
        int lock[][] = new int[N][N];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                key[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                lock[i][j] = Integer.parseInt(st.nextToken());                
            }
        }
        
        System.out.println(solution(key, lock));
    }
}
// 2 3
// 1 0
// 0 1
// 1 1 1 
// 1 1 0 
// 1 0 1 
//https://programmers.co.kr/learn/courses/30/lessons/60059