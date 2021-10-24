import java.io.*;

public class G3마법사상어토네이노20057 {
    static int N, result = 0;
    static int map[][];

    //왼쪽, 아래, 오른쪽, 위 순서
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {-1, 0, 1, 0};

    // 5% -> 10% -> 7% -> 2% -> 1% -> a 순서
    static int dsx[][] = {
    {0, -1, 1, -1, 1, -2, 2, -1, 1, 0}, 
    {2, 1, 1, 0, 0, 0, 0, -1, -1, 1}, 
    {0, -1, 1, -1, 1, -2, 2, -1, 1, 0}, 
    {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1}};
    static int dsy[][] = {
    {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1}, 
    {0, -1, 1, -1, 1, -2, 2, -1, 1, 0}, 
    {2, 1, 1, 0, 0, 0, 0, -1, -1, 1}, 
    {0, -1, 1, -1, 1, -2, 2, -1, 1, 0}};

    static int percent[] = {5, 10, 10, 7, 7, 2, 2, 1, 1};
    

    public static void sandstorm(int x, int y, int d){

        int value = map[x][y];        
        int mvalue = 0;
        
        map[x][y] = 0;

        // 방향에 맞게 값 들어가는지 확인하기
        for(int i = 0; i < 10; i++){
            int dx = x + dsx[d][i];
            int dy = y + dsy[d][i];

            //모래가 담장 밖으로 넘어갈 때
            if(dx < 0 || dy < 0 || dx >= N || dy >= N){
                if(i < 9)   {
                    result += value * percent[i] / 100;
                    mvalue += value * percent[i] / 100;;
                }
                else{
                    result += value - mvalue;
                }                
                continue;
            }

            // 모래가 내부에 있을 때
            if(i < 9){
                map[dx][dy] += value * percent[i] / 100;
                mvalue += value * percent[i] / 100;
            }
            else{
                map[dx][dy] +=  value - mvalue;
            }

        }
        
    }
    public static void main(String[] args) throws IOException{
        String[] nv;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        int x = N/2;
        int y = N/2;


        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
        
        //이동경로 코드
        for(int i = 0; i < N*2-1; i++){
            for(int j = 0; j < i / 2 + 1; j++){
                if(j == N-1) continue;

                x = x + dx[i%4];
                y = y + dy[i%4];
                sandstorm(x, y, i%4);
                //System.out.println(x + " " + y);
            }
        }

        System.out.println(result);
        
        


        



    }
    
}

//https://www.acmicpc.net/problem/20057

// 3, 5
// N = 5, 9
// 1칸   2,2 -> 2,1  x유지 y감소
//       2,1 -> 3,1  x증가 y유지

// 2칸   3,1 -> 3,3  x유지 y증가
//       3,3 -> 1,3  x감소 y유지

// 3칸   1,3 -> 1,0  x유지 y감소
//       1,0 -> 4,0  x증가 y유지 

// 4칸   4,0 -> 4,4  x유지 y증가
//       4,4 -> 0,4  x감소 y유지
//       0,4 -> 0,0  x유지 y감소
      

//7 13
//9 17