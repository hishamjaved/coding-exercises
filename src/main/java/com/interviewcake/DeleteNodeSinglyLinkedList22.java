package com.interviewcake;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 Delete a node from a singly-linked list ? , given only a variable pointing to that node.
 The input could, for example, be the variable b below:

 public static class LinkedListNode {

 public String value;
 public LinkedListNode next;

 public LinkedListNode(String value) {
 this.value = value;
 }
 }

 LinkedListNode a = new LinkedListNode("A");
 LinkedListNode b = new LinkedListNode("B");
 LinkedListNode c = new LinkedListNode("C");

 a.next = b;
 b.next = c;

 deleteNode(b); // requires O(1) time complexity
 */

public class DeleteNodeSinglyLinkedList22 {

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewcake"
            + File.separator + DeleteNodeSinglyLinkedList22.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static class LinkedListNode {

        public String value;
        public LinkedListNode next;

        public LinkedListNode(String value) {
            this.value = value;
        }
    }



    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int n=in.nextInt();
        int deleteIndex=in.nextInt();

        LinkedListNode prev=null;
        LinkedListNode nodeToDelete=null;
        LinkedListNode head=null;
        for(int i=0;i<n;i++){
            LinkedListNode current = new LinkedListNode(in.next());
            System.out.print(current.value+", ");
            if(i==0){
                head=current;
            }
            if(prev!=null){
                prev.next = current;
            }
            prev=current;
            if(i==deleteIndex){
                nodeToDelete=current;
            }
        }

        System.out.println("");
        System.out.println("DELETE NODE AT INDEX: "+deleteIndex);
        deleteInnerNodes(nodeToDelete);

        System.out.println("");
        LinkedListNode current = head;
        while(current!=null){
            System.out.print(current.value + ", ");
            current = current.next;
        }
    }

    /**
     * This method works only with inner nodes of linked list
     * Time Complexity: O(1)
     * @param node
     */
    private static void deleteInnerNodes(LinkedListNode node) {
        LinkedListNode nextNode=node.next;
        if(nextNode!=null){
            node.next = nextNode.next;
            node.value=nextNode.value;
            nextNode = null;
        }
    }

}

