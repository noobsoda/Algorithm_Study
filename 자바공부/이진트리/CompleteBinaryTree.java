package 자바공부.이진트리;

import java.util.*;

public class CompleteBinaryTree {
    private char[] nodes;
    private int lastIndex;
    private final int SIZE;

    public CompleteBinaryTree(int size){
        SIZE = size;
        nodes = new char[size+1];
    }
    public boolean add(char e){
        if(lastIndex == SIZE) return false;

        nodes[++lastIndex] = e;
        return true;
    }

    public void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); // 루트노드

        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print(nodes[cur] + " ");

            
        }
    }

    
}
