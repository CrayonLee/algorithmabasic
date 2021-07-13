package linkedlist;

import java.util.ArrayList;
import java.util.List;

public class Code05_LinkedListMid {


    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 使用快慢指针
     *
     * @param head 头节点
     * @return
     */
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        //链表又三个节点以上
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     *
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     *
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     *   @param head
     *   @return
     */
    public static Node midOrDownMidPreNode(Node head) {
        if(head == null || head.next == null ){
            return  null;
        }
        if(head.next.next==null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 使用快慢指针
     *
     * @param head 头节点
     * @return
     */
    public static Node right1(Node head){
        if(head==null){
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while(cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get((list.size()-1)/2);
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     *
     * @param head
     * @return
     */
    public static Node right2(Node head){
        if (head == null) {
            return null;
        }
        Node cur =head;
        List<Node> list = new ArrayList<>();
        while(cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get((list.size())/2);
    }

    /**
     *   输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     */
    public static Node right3(Node head) {
        if(head==null||head.next.next==null||head.next.next==null){
            return null;
        }
        Node cur = head;
        List<Node> list  = new ArrayList<>();
        while (cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get((list.size()-3)/2);
    }

    /**
     *    输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */
    public static Node right4(Node head) {
        if(head==null||head.next==null){
            return null;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        while(cur!=null){
            list.add(cur);
            cur=cur.next;
        }
        return list.get((list.size()-2)/2);
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");
    }
}
