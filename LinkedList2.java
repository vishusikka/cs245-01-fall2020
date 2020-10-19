
public class LinkedList2 {

    ListNode head;
    int size;

    public LinkedList2(ListNode node) {
        this.head = node;
    }
    //adding the node to the end of the LinkedList
    public void add(ListNode node) {
        ListNode walker = head;
        while (walker.next != null) {
            walker = walker.next;
        }
        walker.next = node;
        size++;
    }
    // find the cycle and return the index
    public int findCycle(){
        if (head.next == null || head.next.next == null ) {
            return -1;
        }
        ListNode walker1 = head.next;
        ListNode walker2 = head.next.next;
        //finding the loop
        while (walker1 != walker2) {
            if (walker1 == null || walker2 == null) {
                return -1;
            }
            walker1 = walker1.next;
            walker2 = walker2.next.next;
        }

        // finding the index
        walker1 = walker1.next;
        int index = get_index(walker1);
        while (walker1 != walker2){
            walker1 = walker1.next;
            int new_index = get_index(walker1);
            if(new_index < index){
                index = new_index;
            }
        }
        return index;

    }

    public int get_index(ListNode node){
        int count = 0;
        ListNode walker = head.next;
        while (walker != node) {
            count++;
            walker = walker.next;
        }
        return count;
    }

    public static void main(String[] args) {

        // setting up the dummy node
        ListNode h = new ListNode(Integer.MIN_VALUE);
        // creating the linked list
        LinkedList2 l = new LinkedList2(h);

        // first add function
        ListNode node1 = new ListNode(1);
        l.add(node1);
        ListNode node2 = new ListNode(2);
        l.add(node2);
        ListNode node3 = new ListNode(3);
        l.add(node3);
        ListNode node4 = new ListNode(4);
        l.add(node4);
        ListNode node5 = new ListNode(5);
        l.add(node5);
        node5.next = node3;
        System.out.println(l.findCycle());

    }
}
class ListNode{
    int data;
    ListNode next;

    public ListNode(int d ){
        this.data = d;
        next = null;
    }

}

