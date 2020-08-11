public class MyStack<T> {
    // field
    private ListNode<T> head;

    // method
    public MyStack(){
        head = null;
    }

    public void push(T item){
        ListNode newNode = new ListNode<T>(item);
        newNode.next = head;
        head = newNode;
    }

    public T pop(){
        if (head == null) throw new NullPointerException();
        ListNode<T> popNode = head;
        head = head.next;
        popNode.next = null;
        return popNode.item;
    }

    public T peek(){
        if (head == null) throw new NullPointerException();
        return head.item;
    }
}
