package linkedlist;

import java.util.Stack;

/**
 * 判断一个链表是不是回文链表
 */
public class Code06_IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need n/2 extra space
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //need o(1) extra space
    public static boolean isPalindrome3(Node head) {
       if(head==null||head.next==null){
           return true;
       }
       Node n1=head;
       Node n2=head;
       while(n2.next!=null&&n2.next.next!=null){
           n1=n1.next;
           n2=n2.next.next;
       }
       //n1 中点
        n2=n1.next;
       n1.next=null;
       Node n3=null;
       while(n2!=null){
           n3=n2.next;
           n2.next=n1;
           n1=n2;
           n2=n3;
       }
       n3=n1;
       n2=head;
       boolean res=true;
       while(n1!=null&&n2!=null){
           if(n1.value!=n2.value){
               res=false;
               break;
           }
           n1=n1.next;
           n2=n2.next;
       }
       n1=n3.next;
       n3.next=null;
       while(n1!=null){
           n2=n1.next;
           n1.next=n3;
           n3=n1;
           n1=n2;
       }
       return res;
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        Node node4 = new Node(2);
        Node node5 = new Node(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        boolean flag = isPalindrome3(head);

        System.out.println(flag);
    }
}
