package 코딩테스트공부;

import java.util.*;
public class L2파일명정렬17686 {
    static ArrayList<File> list;

    public static void main(String[] args){
        String files1[] = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String files2[] = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        solution(files1);
        solution(files2);

    }
    public static String[] solution(String[] files) {
        String[] answer = new String[files.length];
        list = new ArrayList<>();
        
        for(int i = 0; i < files.length; i++){
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            StringBuilder tail = new StringBuilder();
            boolean headflag = false;
            boolean numberflag = false;
            for(int j = 0; j < files[i].length(); j++){  
                if(headflag && numberflag){
                    tail.append(files[i].charAt(j));
                }
                
                if(!numberflag && Character.isDigit(files[i].charAt(j))){
                    number.append(files[i].charAt(j));
                    headflag = true;
                    if(files[i].length() <= j+1)  continue;
                    if(!Character.isDigit(files[i].charAt(j+1)))
                       numberflag = true;
                }
                if(!headflag){
                    head.append(files[i].charAt(j));
                }
                
                       
            }
            String h = head.toString();
            String n = number.toString();
            String t = tail.toString();
            h = h.toUpperCase();
            list.add(new File(files[i], h, Integer.parseInt(n), t));
            
        }
        Collections.sort(list);
        
        int cnt = 0;
        for(File i : list){
            System.out.println(i.filename);
            answer[cnt] = i.filename;
            cnt++;
        }
        
            
        
        
        
        
        return answer;
    }
    static class File implements Comparable<File>{
        String filename, head, tail;
        int number;
        public File(String filename, String head, int number, String tail){
            this.filename = filename;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        public int compareTo(File o){
            if(head.compareTo(o.head) == 0){
                return number - o.number;
            }            
            return head.compareTo(o.head);
        }
        
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/17686