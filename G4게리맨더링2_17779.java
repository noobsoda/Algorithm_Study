import java.io.*;

public class G4게리맨더링2_17779 {
    static int N;
    static int map[][];
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String nv[];
        for(int i = 0; i < N; i++){
            nv = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(nv[j]);
            }
        }
    }    
}
