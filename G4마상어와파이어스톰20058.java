import java.io.*;
import java.util.*;
public class G4마상어와파이어스톰20058 {
    static int N, Q, size = 1, sum = 0, max = 0;
    static int A[][];
    static int tempA[][];
    static int L[];
    static int dL[] = {1, 2, 4, 8, 16, 32, 64};
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static boolean visited[][];

    //가장 큰 덩어리 구하기 dfs만 수정하면 완성
    public static int dfs(int x, int y, int count){

        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx > size || ny > size)
                continue;
            if(A[nx][ny] != 0 && !visited[nx][ny]){
                count += dfs(nx, ny, 1);
            }                
        }
        return count;
            
    }  
    //돌리고돌리고
    public static void rotate(int x, int y, int nL){        
        for(int i = 0; i < nL; i++){
            for(int j = 0; j < nL; j++){
                tempA[j+x][y+nL-1-i] = A[x+i][y+j];
            }
        }        
    }
    public static void firestorm(int nL){
        for(int i = 0; i < size / nL; i++){
            for(int j = 0; j < size / nL; j++){
                rotate(i*nL, j*nL, nL);
            }
        }
        for(int i = 0; i < size; i++){
            System.arraycopy(tempA[i], 0, A[i], 0, tempA[i].length);
        }
        //빙하녹이기 tempA사용해서 A에 덮어씌우기        
        //근처 빙하가 2개 이하면 얼음 -1
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int nearice = 0;
                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || ny < 0 || nx > size || ny > size){
                        nearice++;
                        continue;
                    }
                    if(tempA[nx][ny] == 0)
                        nearice++;
                    
                }
                if(nearice >= 2){
                    //0이하로 안 떨어지게 하기
                    
                    if(A[i][j] > 0)
                        A[i][j]--;
                    
                }
                sum += A[i][j];
            }
        }
        
        max = 0;
        //서로 이어져있는 최대 빙산갯수 확인
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(visited[i][j] || A[i][j] == 0)
                    continue;
                else{  
                    max = Math.max(max, dfs(i, j, 1));
                }
            }
        }
        //방문 요소 초기화
        for(boolean a[] : visited){
            Arrays.fill(a, false);
        }
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nv[] = br.readLine().split(" ");

        N = Integer.parseInt(nv[0]);
        Q = Integer.parseInt(nv[1]);  

        size = dL[N];
        A = new int[size+1][size+1];
        tempA = new int[size+1][size+1];
        visited = new boolean[size][size];
        L = new int[Q];

        for(int i = 0; i < size; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < size; j++){
                A[i][j] = Integer.parseInt(nv[j]);
            }
        }
        nv = br.readLine().split(" ");
        for(int i = 0; i < Q; i++){
            sum = 0;
            L[i] = Integer.parseInt(nv[i]);
            firestorm(dL[L[i]]);            
        }

        
        

        System.out.println(sum);
        System.out.println(max);
        

    }    
    static class Node{
        int x, y, count;
        public Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
