package 코딩테스트공부;
import java.io.*;
import java.util.*;

public class G1Brainfuck인터프리터3954 {
    static int sm, sc, si, icnt, idx;
    static int map[];
    static char order[];
    static char input[];
    static Stack<Node> stack;
    static int pointer;
    static int gcnt;
    static final int max = 50_000_000;
    
    public static int stackOverFlow(){
        int start = idx++;
        int end = 0;
        int cnt = 1;


        Exit_lag: while(idx < sc){
            if(pointer < 0 || pointer >= sm) return 0;
            
            switch(order[idx]){
                case '+':
                    map[pointer]++;
                    map[pointer] %= 256;
                    
                    break;
                case '-':
                    map[pointer]--;
                    map[pointer] %= 256;
                    break;
                case '<':
                    pointer--;
                    break;
                case '>':
                    pointer++;
                    break;
                case ',':
                    if(icnt >= si){
                        map[pointer] = 255;
                    }
                    else{
                        int c = input[icnt++];
                        map[pointer] = c;
                    }
                    break;

                case '[':                    
                    //짝에 맞는놈 찾을 때까지 idx 고고
                    if(map[pointer] == 0){
                        //원상복귀할 스택 사이즈
                        int size = stack.size();
                        while(true){
                            if(order[idx] == '[')
                                stack.push(new Node(order[idx], idx));
                            else if(order[idx] == ']'){
                                stack.pop();
                                if(stack.size() == size){                                    
                                    break;
                                }
                            }


                            idx++;
                        }
                    }
                    else{
                        //스택으로 짝 찾기
                        stack.push(new Node(order[idx], idx));
                        cnt += stackOverFlow();
                        if(cnt > max){
                            break Exit_lag;
                        }
                    }
                    break;

                case ']':
                    if(map[pointer] != 0){
                        end = idx;
                        idx = stack.peek().idx;
                        
                    }
                    else{
                        stack.pop();                        
                        return 0;
                    }
                    

                    break;
                
                default:
                    break;
            }
            idx++;
            cnt++;
            gcnt++;
            if(cnt > max){
                System.out.println("Loops " + start + " " + end);
                return cnt;                
            }
        }

        return cnt;

        

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int test_case = 0; test_case < T; test_case++){
            gcnt = 0;
            gcnt++;
            idx = 0;
            icnt = 0;
            pointer = 0;


            st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken());

            stack = new Stack<>();

            //출력 무시, 어떤 루프에서 연산을 5천만번 이상 했냐 안했냐 여부 

            map = new int[sm];
            order = new char[sc];
            input = new char[si];

            String nv = br.readLine();
            order = nv.toCharArray();

            nv = br.readLine();
            input = nv.toCharArray();

            int cnt = 0;
            Exit_lag: while(idx < sc){
                if(pointer < 0 || pointer >= sm) break Exit_lag;
                switch(order[idx]){
                    case '+':
                        map[pointer]++;
                        break;
                    case '-':
                        map[pointer]--;
                        break;
                    case '<':
                        pointer--;
                        break;
                    case '>':
                        pointer++;
                        break;
                    case ',':
                        if(icnt >= si)
                            map[pointer] = 255;
                        else{
                            int c = input[icnt++];
                            map[pointer] = c;
                        }
                        break;
    
                    case '[':                        
                        //짝에 맞는놈 찾을 때까지 idx 고고
                        if(map[pointer] == 0){
                            //원상복귀할 스택 사이즈
                            int size = stack.size();
                            while(true){
                                if(order[idx] == '[')
                                    stack.push(new Node(order[idx], idx));
                                else if(order[idx] == ']'){
                                    stack.pop();
                                    if(stack.size() == size){
                                        idx++;
                                        break;
                                    }
                                }
    
    
                                idx++;
                            }
                        }
                        else{
                            //스택으로 짝 찾기
                            stack.push(new Node(order[idx], idx));                            
                            cnt = stackOverFlow();
                            if(cnt > max){
                                break Exit_lag;
                            }
                        }
                        break;
    
                    case ']':
                        if(map[pointer] != 0){                            
                            idx = stack.peek().idx;
                            
                        }
                        break;                    
                    default:
                        break;
                }
                idx++;
            }
            

            if(cnt < max)
                System.out.println("Terminates");

            //System.out.println(Arrays.toString(map));


            

        }

    }
    static class Node{
        char c;
        int idx;
        public Node(char c, int idx){
            this.c = c;
            this.idx = idx;
        }
    }
    
}
//이중루프 내부에서 천만번씩 연산이 일어났다 그렇다면 바깥 루프는 5천만번이 일어난것인가?
// mod 256 해야 함
//18% 배열 초과


//mod 안쓰고 할때 시간초과 나나 체크