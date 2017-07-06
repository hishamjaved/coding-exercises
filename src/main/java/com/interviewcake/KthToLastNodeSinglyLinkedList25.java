package com.interviewcake;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 You have a linked list and want to find the kth to last node.
 Write a function kthToLastNode() that takes an integer kk and the headNode of a singly linked list, and returns the kth
 to last node in the list.

 For example:

 public static class LinkedListNode {

 public String value;
 public LinkedListNode next;

 public LinkedListNode(String value) {
 this.value = value;
 }
 }

 LinkedListNode a = new LinkedListNode("Angel Food");
 LinkedListNode b = new LinkedListNode("Bundt");
 LinkedListNode c = new LinkedListNode("Cheese");
 LinkedListNode d = new LinkedListNode("Devil's Food");
 LinkedListNode e = new LinkedListNode("Eccles");

 a.next = b;
 b.next = c;
 c.next = d;
 d.next = e;

 kthToLastNode(2, a);
 // returns the node with value "Devil's Food" (the 2nd to last node)

 Gotchas
 We can do this in O(n) time.

 We can do this in O(1) space. If you're recursing, you're probably taking O(n) space on the call stack!

 Breakdown
 It might be tempting to iterate through the list until we reach the end and then walk backwards kk nodes.

 But we have a singly linked list! We can’t go backwards. What else can we do?

 What if we had the length of the list?

 Then we could calculate how far to walk, starting from the head, to reach the kkth to last node.

 If the list has nn nodes:

 A linked list represented by cirlces and arrows, with the distance from the first to the last node labelled n.
 And our target is the kkth to last node:

 A linked list represented by cirlces and arrows, with the distance from the first to the last node labelled n. The third-to-last node is the kth to last node, with its distance to the last node labelled k.
 The distance from the head to the target is n-kn?k:

 A linked list represented by cirlces and arrows, with the distance from the first to the last node labelled n. The third-to-last node is the kth to last node, with its distance to the last node labelled k and its distance to the first node labelled n minus k.
 Well, we don't know the length of the list (nn). But can we figure it out?

 Yes, we could iterate from the head to the tail and count the nodes!

 Can you implement this approach in code?

 public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

 // STEP 1: get the length of the list
 // start at 1, not 0
 // else we'd fail to count the head node!
 int listLength = 1;
 LinkedListNode currentNode = head;

 // traverse the whole list,
 // counting all the nodes
 while (currentNode.next != null) {
 currentNode = currentNode.next;
 listLength += 1;
 }

 // STEP 2: walk to the target node
 // calculate how far to go, from the head,
 // to get to the kth to last node
 int howFarToGo = listLength - k;

 currentNode = head;
 for (int i = 0; i < howFarToGo; i++) {
 currentNode = currentNode.next;
 }

 return currentNode;
 }

 What are our time and space costs?

 O(n) time and O(1) space, where nn is the length of the list.

 More precisely, it takes nn steps to get the length of the list, and another n-kn?k steps to reach the target node. In the worst case k=1k=1, so we have to walk all the way from head to tail again to reach the target node. This is a total of 2n2n steps, which is O(n)O(n).

 Can we do better?

 Mmmmaaaaaaybe.

 The fact that we walk through our whole list once just to get the length, then walk through the list again to get to the kkth to last element sounds like a lot of work. Perhaps we can do this in just one pass?

 What if we had like a "stick" that was kk nodes wide. We could start it at the beginning of the list, so that the left side of the stick was on the head and the right side was on the kkth node.

 A linked list represented by cirlces and arrows. The third node is labelled "kth," and a linear "stick" k nodes long extends from above the first node to above the kth node.
 Then we could slide the stick down the list...

 A linked list represented by cirlces and arrows. The third node is labelled "kth," and a linear "stick" k nodes long extends from above the second node to above the fourth node.
 until the right side hit the end. At that point, the left side of the stick would be on the kkth to last node!

 A linked list represented by cirlces and arrows. The third-to-last node is labelled "kth to last," and a linear "stick" k nodes long extends from above the kth-to-last node to above the last node.
 How can we implement this? Maybe it'll help to keep two pointers?

 We can allocate two variables that'll be references to the nodes at the left and right sides of the "stick"!

 public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

 LinkedListNode leftNode  = head;
 LinkedListNode rightNode = head;

 // move rightNode to the kth node
 for (int i = 0; i < k - 1; i++) {
 rightNode = rightNode.next;
 }

 // starting with leftNode on the head,
 // move leftNode and rightNode down the list,
 // maintaining a distance of k between them,
 // until rightNode hits the end of the list
 while (rightNode.next != null) {
 leftNode  = leftNode.next;
 rightNode = rightNode.next;
 }

 // since leftNode is k nodes behind rightNode,
 // leftNode is now the kth to last node!
 return leftNode;
 }

 This'll work, but does it actually save us any time?
 */

public class KthToLastNodeSinglyLinkedList25 {


    protected static class LinkedListNode{
        public String value;
        public LinkedListNode next;

        public LinkedListNode(String value) {
            this.value = value;
        }
    }

    private static final String dirPath = System.getProperty("user.dir") + File.separator
            + "target" + File.separator + "classes" + File.separator + "interviewcake"
            + File.separator + KthToLastNodeSinglyLinkedList25.class.getSimpleName() + File.separator;
    private static final String input = dirPath + "Test.in";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(input))));
        int k = in.nextInt();
        LinkedListNode prev=null;
        LinkedListNode head=null;
        LinkedListNode node;
        while (in.hasNextLine()) {
            node = new LinkedListNode(in.nextLine());
            if(prev!=null){
                prev.next=node;
                if(head==null){
                    head=prev;
                }
            }
            prev = node;
        }
        System.out.println(kthToLastNode1(k,head));
        System.out.println(kthToLastNode2(k, head));
    }

    /**
     * This solution first calculate the size and then find the kth node complexity is O(n)
     * and it is as efficient as other solution
     * @param k
     * @param head
     * @return
     */
    private static String kthToLastNode1(int k,LinkedListNode head) {
        if(k<=0){
            return null;
        }

        int s=0;
        LinkedListNode node = head;
        while(node!=null){
            s++;
            node = node.next;
        }

        s = s - k;
        if(s<1){
            return null;
        }
        int i=0;

        node = head;
        while(i<s){
            i++;
            node = node.next;
        }

        return node.value;
    }

    /**
     * This solution use some extra space but less steps and could be little efficient due to processor internal cache
     * @param k
     * @param head
     * @return
     */
    private static String kthToLastNode2(int k,LinkedListNode head) {

        if(k<=0){
            return null;
        }
        LinkedListNode left = head;
        LinkedListNode right = head;
        for(int i=0;i<k;i++){
            if(right!=null){
                right=right.next;
            }
        }

        if(right==null){
            return null;
        }

        while(right!=null){
            left = left.next;
            right = right.next;
        }

        return left.value;
    }

}

