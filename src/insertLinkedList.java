public class insertLinkedList {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }
    public ListNode insert(ListNode head, int value){
        ListNode newNode = new ListNode(value);

        // 没head或小于：插在head前
        if (head == null || value <= head.val){
            newNode.next = head;
            return newNode;
        }

        ListNode prev = head;
        while (prev.next != null && prev.next.val < value){
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        return head;
    }
}
