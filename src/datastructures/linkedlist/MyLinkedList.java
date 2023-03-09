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
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
    }

    public void getTail() {
        if (head == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.value);
        }
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }


    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        length++;
    }

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

    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode; //head Node and tail Node both = newNode
            tail = newNode;
        } else {
            newNode.next = head; //next 'Node' type field points to head
            head = newNode; //head node = newNode
        }
        length++;
    }

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
    }

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
    }

    public boolean set(int index, int value){
        Node temp = get(index); //get method utilized where if a node exists at the index, it returns it. otherwise null
        if (temp != null){
            temp.value = value; //reassigns value field of returned Node and returns boolean
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value)  {
        if (index < 0 || index > length) return false; //realize that length is greater than the last
        // index but we are possibly adding at index of length valuee so just > is exclusionary, not >=
        if (index == 0) {
            prepend(value); //reuse prepend method
            return true; //cannot directly return the prepend() method since it returns void which is
            // not in accordance with mandated return of boolean for the insert() method
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1); //pointer created 'temp' and get method used with is O(n) to get the
        //node before desired index since we need something to point forward
        newNode.next = temp.next; //new node placed after temp
        temp.next = newNode; //preceding node reassigned to point to newNode
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst(); //can include removeFirst() in return line since it returns
        // a Node which is in agreeance with the remove() method which requires a Node be returned
        if (index == length - 1) return removeLast(); //reuse

        Node prev = get(index - 1); //using geet method is O(n)
        Node temp = prev.next; //don't want to use the get method here for the next index since
        // this way we can have it be O(1) instead of O(n)

        prev.next = temp.next;
        temp.next = null; //breaks off the temp pointer/Node
        length--;
        return temp;
    }

}






