import java.io.*;

public class S3스타트와링크14889 {
    static int N, min = Integer.MAX_VALUE;
    static int map[][];
    static boolean visited[];

    public static void diff(){
        int steam = 0;
        int lteam = 0;

        for(int i = 0; i < N-1; i++){
            for(int j = i; j < N; j++){
                if(visited[i] && visited[j]){
                    steam += map[i][j];
                    steam += map[j][i];
                }

                if(!visited[i] && !visited[j]){
                    lteam += map[i][j];
                    lteam += map[j][i];
                }
            }
        }


        int v = Math.abs(steam - lteam);

        min = Math.min(v, min);

        if(min == 0){
            System.out.println(min);
            System.exit(0);
        }
    }

    public static void startlink(int index, int depth){
        if(depth == N/2){
            diff();
            return;
        }
        else{
            for(int i = index; i < N; i++){
                if(!visited[i]){
                    visited[i] = true;
                    startlink(i+1, depth+1);
                    visited[i] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());      
        map = new int[N][N];
        visited = new boolean[N];


        String nv[];
        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }

        startlink(0, 0);
        System.out.println(min);
    }
}
