package datastructures.linkedlist;


import java.util.ArrayList;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public MyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head; //temp and head both point to first Node
        while (temp != null) { // while temp points to something, while loop continues
            System.out.println(temp.value);
            temp = temp.next; //temp assigned to next Node/null
        }
    } // uses while loop. O(n)

    public void getHead() {
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
    } // O(1)

    public void getTail() {
        if (head == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.value);
        }
    } // O(1)

    public void getLength() {
        System.out.println("Length: " + length);
    } // O(1)

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
        //note here that the Nodes are again not deleted. we could have 100 nodes that point from one to the next,
        // but without the head pointer to the first Node, there is no way to access the list and length is set to '0'
        // so the list of Linked Nodes I assume just gets picked up by the garbage collector at some point
    } // O(1)

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    } // O(1)

    public Node removeLast() {
        if (length == 0) return null; //if list has no items, returns null

        //if list has length, both temporary variables 'pre' and 'temp' set to value of the 'head' pointer
        Node temp = head;
        Node pre = head;
        while (temp.next != null) { //while there is a next item in the list, the while loop continues
            pre = temp; // pre is set to temp
            temp = temp.next; // temp set to next
            // so now, after the first node where both pre and temp are assigned to head, temp will always be one in
            //front of pre
        }
        //when temp reaches the final node and the loop realizes there is no next, tail pointer is set to pre which
        // is pointing to the second to last Node
        tail = pre;
        tail.next = null; //tail pointer points to null and effectively breaks off the final node from the list.
        // note that temp still points to the final Node value, but the Node is no longer a part of the list
        length--; // length of list decremented. the problem here is that if the list was only one Node in length, then
        //temp.next would be null, the while loop would be skipped, both pre and temp variables would still point to head
        // and the lenngth would decrement despite the one Node still remaining

        //fina segment here retests for the single node conition and says if we decremented the length to zero, then head
        // and tail must equal null and no longer point to the single Node
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp; // still set to value of null from while loop

    } // O(n) since starts at beginning and uses while loop to get to end

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode; //head Node and tail Node fields of LinkedList both = newNode
            tail = newNode;
        } else {
            newNode.next = head; //next 'Node' type field points to head
            head = newNode; //head node = newNode
        }
        length++;
    } // O(1)

    public Node removeFirst(){
        if (length == 0) return null;
        Node temp = head; // temp and head point to same Node in memory
        head = head.next; // head points to 2nd node
        temp.next = null; // breaks off first node which temp is pointing to
        length--;
        if (length ==0){
            tail = null;
        }
        return temp; //returns removed node
    } // O(1). better than arrayList in this case where ArrayList relies on indexes.

    public Node get(int index){
        if (index < 0 || index >= length){
            return null;
        }
        Node temp = head; // temp = head to achieve value without modifying head
        for (int i = 0; i < index; i++){
            temp = temp.next; //traverse the loop and reassign temp.
            //stops the loop before the index since temep = the next item so we get value that
            // the preceding Node points to
        }
        return temp; //return the Node at deesired index
    } // O(n), requires for loop iteration

    public boolean set(int index, int value){
        Node temp = get(index); //get method utilized where if a node exists at the index, it returns it. otherwise null
        if (temp != null){
            temp.value = value; //reassigns value field of returned Node and returns boolean
            return true;
        }
        return false;
    } // utilizes get() which is O(n) so this is too

    public boolean insert(int index, int value)  {
        if (index < 0 || index > length) return false; //realize that length is greater than the last
        // index but we are possibly adding at index of length valuee so just > is exclusionary, not >=
        if (index == 0) {
            prepend(value); //reuse prepend method which is O(1)
            return true; //cannot directly return the prepend() method since it returns void which is
            // not in accordance with mandated return of boolean for the insert() method
        }
        if (index == length) {
            append(value); // reuses append which is O(1)
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1); //pointer created 'temp' and get method used with is O(n) to get the
        //node before desired index since we need something to point forward
        newNode.next = temp.next; //new node placed after temp but temp does not yet point to newNode. effectivley
        // newNode.next is set to null
        temp.next = newNode; //preceding node reassigned from null to point to newNode
        length++;
        return true;
    } //re-uses append and prepend methods which are O(1) but also
    // uses get() for inserting at index that is not first or last. this is O(n) so we drop non dominants: O(1) and the
    //insert method is just O(n)

    public Node remove(int index) {
        if (index < 0 || index >= length) return null; // >= since we can't remove an index of 'length' value
        if (index == 0) return removeFirst(); //can include removeFirst() in return line since it returns
        // a Node which is in agreeance with the remove() method which requires a Node be returned
        // removeFirst is O(1)
        if (index == length - 1) return removeLast(); //reuse removeLast which is O(n)

        Node prev = get(index - 1); //using get method is O(n)
        Node temp = prev.next; //don't want to use the get method here for the next index since
        // this way we can have it be O(1) instead of O(n)

        prev.next = temp.next; // prev.next = null
        temp.next = null; //breaks off the temp pointer/Node
        length--;
        return temp;
    } //re-uses removeFirst() which is O(1), and reuses removeLast() and get()
    // both of which are O(n). Non-dominant Big O is dropped so remove() is O(n)

    public void reverse() {
        Node temp = head; //temp and head point to first node
        head = tail; // head and tail both point to last node
        tail = temp; // tail points to first node. head and tail effectively swapped and temp still points first node
        Node after; // variable created but does not yet point to anything
        Node before = null;
        for (int i = 0; i < length; i++) { //using a 3 node list as an example....
            after = temp.next; // now 'after' points to node 2
            temp.next = before; // temp pointer now points 'left' to null instead of right to 'after'
            before = temp; //before and temp both point to node 1
            temp = after; //temp and after both point to node 2
            //end of first loop, before on node 1, temp and after on node 2, nothing on node 3
            //after second loop, after and temp both point 'right' to null and before points to node 3 along with 'tail'
        }
    } // uses for loop. O(n)
}






