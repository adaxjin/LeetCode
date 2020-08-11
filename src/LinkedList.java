import Setup.ListNode2;

public class LinkedList<E> {
    //fields
    private ListNode2<E> head;
    private ListNode2<E> tail;
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
        ListNode2<E> cur = head;
        while (index > 0){
            cur = cur.next;
            index--;
        }
        return (int)cur.val;
    }

    public void addHead(int val){
        ListNode2<E> newHead = new ListNode2<>(val);
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
        ListNode2<E> newTail = new ListNode2<>(val);
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
