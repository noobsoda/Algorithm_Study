package 자바공부.리스트_트리;

public class Node<T> {
    T data;
    Node<T> link;
    public Node(T data, Node<T> link) {
        this.data = data;
        this.link = link;
    }

    public Node(T data){
        super();
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", link=" + link + "]";
    }

    
}
