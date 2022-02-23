package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class S1별찍기10_2447 {
    static int N;
    static String[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new String[N][N]; 
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = " "; 
            } 
        } 
        
        star(arr, 0, 0, N);

        for(String[] strings : arr){
            for(String string : strings){
                bw.write(string + "");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        
    }

    public static void star(String[][] arr, int x, int y, int N) {
         if (N == 1) { arr[x][y] = "*"; 
         return; 
         // 종료 
        } 
        for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                  if (!(i == 1 && j == 1)) {
                       star(arr, x + i * (N / 3), y + j * (N / 3), N / 3); 
                    } 
                } 
            } 
        }



}
//https://www.acmicpc.net/problem/2447
