public class LinkedList<E> {
    //fields
    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    //methods
    // constructor
    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    // getVal, addHead, addTail, getSize
    public int getVal(int index){ // throw Exception
        if (head == null || index < 0 || index > size - 1){
            return -1; // throw new IllegalArgumentsException("");
        }
        ListNode<E> cur = head;
        while (index > 0){
            cur = cur.next;
            index--;
        }
        return cur.val;
    }

    public void addHead(int val){
        ListNode<E> newHead = new ListNode<E>(val);
        if (head == null){
            tail = newHead;
        } else {
            newHead.next = head;
            head.prev = newHead;
        }
        head = newHead;
        size++;
    }

    public void addTail(int val){
        ListNode<E> newTail = new ListNode<E>(val);
        if (tail == null){
            head = newTail;
        } else {
            newTail.prev = tail;
            tail.next = newTail;
        }
        tail = newTail;
        size++;
    }

    public int getSize(){
        return size;
    }
}
