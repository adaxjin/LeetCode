public class MyQueue<E> {
    // fields
    private ListNode2<E> head, tail;
    int length;

    // methods
    public MyQueue(){
        head = null;
        tail = null;
        length = 0;
    }
    public void offer(E val){
        if (tail == null){
            tail = new ListNode2<>(val);
            head = tail;
        } else {
            ListNode2<E> newTail = new ListNode2<>(val);
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        length++;
    }

    public E poll(){
        if (head == null) throw new NullPointerException();
        ListNode2<E> temp = head;
        if (head == tail){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp.val;
    }

    public E peek(){
        if (head == null) throw new NullPointerException();
        return head.val;
    }
}
