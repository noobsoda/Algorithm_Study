package 자바공부.리스트_트리;

public class StackTest {
    public static void main(String[] args){
        SsafyStack<Integer> stack = new SsafyStack<>();

        stack.push(1);
        stack.push(2);


        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.peek());
        stack.pop();

        stack.pop();
        stack.peek();
        System.out.println(stack.IsEmpty());
    }
}
