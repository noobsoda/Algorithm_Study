import java.io.*;
import java.util.*;

public class G5공유기설치2110 {
    static int N, C;
    static int arr[];
    public static boolean check(int mid){
        int cnt = 1;
        //최소의 최대 거리
        int last = arr[0] + mid;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= last){
                cnt++;
                last = arr[i] + mid;
            }
        }
        //cnt가 C보다 높으면 true
        return cnt >= C;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String nv[] = br.readLine().split(" ");
        N = Integer.parseInt(nv[0]);
        C = Integer.parseInt(nv[1]);
        arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        

        int ans = 1;
        int start = 1;
        int end = arr[N-1] - arr[0];

        while(start <= end){
            int mid = (start + end) / 2;
            if(check(mid)){
                ans = Math.max(ans, mid);
                start = mid+1;
            }
            else{
                end = mid-1;
            }
        }
        System.out.println(ans);
    }
}
