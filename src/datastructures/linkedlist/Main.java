package datastructures.linkedlist;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        //EXERCISE 2 TEST CODE

//        MyLinkedList myLinkedList = new MyLinkedList(1);
//        myLinkedList.makeEmpty();
//        myLinkedList.append(1);
//        myLinkedList.append(2);
//
//        myLinkedList.getHead();
//        myLinkedList.getTail();
//        myLinkedList.getLength();
//
//        System.out.println("\nLinked List:");
//        myLinkedList.printList();


        //EXERCISE 3 TEST CODE

        MyLinkedList myLinkedList = new MyLinkedList(1);
        myLinkedList.append(2);

        // (2) Items - Returns 2 Node
        System.out.println(myLinkedList.removeLast().value);
        // (1) Item - Returns 1 Node
        System.out.println(myLinkedList.removeLast().value);
        // (0) Items - Returns null
        System.out.println(myLinkedList.removeLast());


    }

}
