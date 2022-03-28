package 코딩테스트공부;

public class L2괄호변환60058 {
    public static void main(String[] args){
        System.out.println(solution("(()())()"));
        System.out.println(solution(")()(()"));

    }
    public static String bracket(String u, String v){
        StringBuilder sb = new StringBuilder();
        if(u.isEmpty())
            return "";
        
        int left = 0;
        int right = 0;
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == '(')
                left++;
            else
                right++;
            if(left == right)
                break;
        }
        int mid = left + right;
        v = u.substring(mid);
        u = u.substring(0, mid);
        
        //u에 대해 올바른 문자열인지 검사
        boolean flag = false;
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == '('){
                flag = true;
                left++;
            }
            else if(u.charAt(i) == ')' && flag){
                right++;
                if(left == right){
                    flag = false;
                }
            }
        }
        
        v = bracket(v, "");        
        //올바른 문자열이 아닌경우
        if(left != right){
            StringBuilder sb2 = new StringBuilder();
            //u 앞뒤 자르고 v 가운데에 넣고 u 뒤에 붙여넣기
            u = u.substring(1, u.length()-1);
            for(int i = 0; i < u.length(); i++){
                if(u.charAt(i) == '(')
                    sb2.append(')');
                else
                    sb2.append('(');
            }
            
            u = sb2.toString();
            sb.append('(');
            sb.append(v);
            sb.append(')');
            sb.append(u);
        }           
        //올바른 문자열인 경우
        //u앞에 넣고 뒤에 v 넣기   
        else{
            sb.append(u);              
            sb.append(v);            
        }        
        u = sb.toString();
        
        return u;
    }
    
    public static String solution(String p) {
        String answer = "";               
        answer = bracket(p, "");       
        
        return answer;
    }
}
//https://programmers.co.kr/learn/courses/30/lessons/60058#