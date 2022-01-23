import java.io.*;
import java.util.*;

public class G4문자열폭발9935 {
    static String string, bomb;


    public static void bomb(){
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < string.length(); i++){
            st.push(string.charAt(i));

            if(st.size() >= bomb.length()){
                boolean flag = true;

                for(int j = 0; j < bomb.length(); j++){
                    if(st.get(st.size() - bomb.length()+j) != bomb.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                
                if(flag){
                    for(int j = 0; j < bomb.length(); j++){
                        st.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character c : st){
            sb.append(c);            
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
       
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        string = br.readLine();
        bomb = br.readLine();

        bomb();


    }    
}

//https://www.acmicpc.net/problem/9935