package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class G5탑2493 {
    static int N;
    static int map[];
    static Stack<Node> stack;
    static Stack<Node> tempstack;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N+1];
        stack = new Stack<>();
        tempstack = new Stack<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            stack.push(new Node(Integer.parseInt(st.nextToken()), i));

        while(!stack.isEmpty()){
            Node now = stack.pop();
            
            while(!tempstack.isEmpty()){
                Node temp;
                if(now.n > tempstack.peek().n){
                    temp = tempstack.pop();
                    map[temp.index] = now.index;
                }
                else{
                    break;
                }

            }

            if(stack.size() == 0){
                map[now.index] = 0;
                break;
            }
            if(stack.peek().n > now.n){
                map[now.index] = stack.peek().index;
            }
            else{
                tempstack.push(new Node(now.n, now.index));
            }
        }
        while(!tempstack.isEmpty()){
            Node now = tempstack.pop();
            map[now.index] = 0;
        }

        for(int i = 1; i <= N; i++){
            bw.write(map[i] + " ");
        }

        bw.flush();


    }
    static class Node{
        int n, index;
        public Node(int n, int index){
            this.n = n;
            this.index = index;
        }
    }
}
//https://www.acmicpc.net/problem/2493