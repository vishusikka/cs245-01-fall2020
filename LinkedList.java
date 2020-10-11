public class LinkedList {

    ListNode head;
    int size;

    public LinkedList(ListNode node) {
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

    public void add(ListNode node, int position) {
        //check if position is valid
        if (position < 0 || position > size) {
            System.out.println("Invalid position");
            return;
        }
        if (position == 0) {
            ListNode temp = head.next;
            head.next = node;
            node.next = temp;
            size++;
            return;
        }
        ListNode walker = head;
        int count = 0;
        while (count < position) {
            walker = walker.next;
            count++;
        }
        ListNode temp = walker.next;
        walker.next = node;
        node.next = temp;
        size++;

    }

    public ListNode get(int position) {
        if (position < 0 || position >= size) {
            System.out.println("Invalid position");
            return null;
        }
        ListNode walker = head.next;
        for (int i = 0; i < position; i++) {
            walker = walker.next;
        }
        return walker;
    }

    public ListNode remove(int position) {
        // check if position is invalid
        if (position < 0 && position > size) {
            System.out.println("Invalid position");
            return null;
        }
        if (position == 0) {
            ListNode walker = head.next;
            head.next = head.next.next;
            size--;
            return walker;
        }
        ListNode walker = head.next;
        int count = 0;
        while (count < position - 1) {
            walker = walker.next;
            count++;
        }
        walker.next = walker.next.next;
        size--;
        return walker;
    }

    public ListNode reverse(ListNode h) {
        ListNode before = null;
        ListNode current = h.next;
        ListNode after = current.next;
        while (current.next != null) {
            current.next = before;
            before = current;
            current = after;
            after = current.next;
        }
        current.next = before;
        h = current;
        head.next = h;
        return h;
    }

    public void printLinkedList(){

        if (head == null){
            System.out.println("LinkedList is empty!");
            return;
        }
        ListNode walker = head.next;

        while(walker != null){
            System.out.print(walker.data + " ");
            walker = walker.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {

        // setting up the dummy node
        ListNode h = new ListNode(Integer.MIN_VALUE);
        // creating the linked list
        LinkedList l = new LinkedList(h);

        // first add function
        System.out.println("adding 1 and 2");
        ListNode node1 = new ListNode(1);
        l.add(node1);
        ListNode node2 = new ListNode(2);
        l.add(node2);
        l.printLinkedList();
        System.out.println();

        // second add function
        System.out.println("adding 5 at position 0");
        ListNode node3 = new ListNode(5);
        l.add(node3, 0);
        l.printLinkedList();
        System.out.println("adding 7 at invalid position");
        ListNode node4 = new ListNode(7);
        l.add(node4, 12);
        l.printLinkedList();
        System.out.println("adding 10 at position 2");
        ListNode node5 = new ListNode(10);
        l.add(node5, 2);
        l.printLinkedList();
        System.out.println();

        // get function
        System.out.println("getting position 3");
        ListNode result = l.get(3);
        if (result != null) {
            System.out.println(result.data);
        }
        System.out.println("getting an invalid position");
        result = l.get(12);
        if (result != null) {
            System.out.println(result.data);
        }
        System.out.println();

        System.out.println("removing position 2");
        l.remove(2);
        l.printLinkedList();
        System.out.println("removing invalid position");
        l.remove(-2);
        l.printLinkedList();
        System.out.println();

        System.out.println("reversing");
        l.reverse(h);
        l.printLinkedList();
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
