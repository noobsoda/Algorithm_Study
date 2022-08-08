package 자바공부.리스트_트리;

public interface IStack<T> {
    void push(T t);
    T pop();
    T peek();
    boolean IsEmpty();
    int size();
}
