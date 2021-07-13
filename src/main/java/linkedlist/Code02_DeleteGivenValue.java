package linkedlist;

import java.awt.print.Printable;

/**
 * 将链表中的指定值删除  因为可能会删掉头部的值  所以需要返回新的头部
 */
public class Code02_DeleteGivenValue {
    public static Node removeValue(Node head, int num) {
        //head来到第一个不需要删除的位置,作为新的节点返回
       while(head!=null){
           if(head.val!=num){
               break;
           }
           head=head.next;
       }
       Node pre = head;
       Node cur = head;
       while(cur!=null){
           if(cur.val==num){
               pre.next=cur.next;
           }else{
               pre=cur;
           }
           cur=cur.next;
       }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1=new Node(2);
        Node node2 = new Node(3);
        head.next=node1;
        node1.next=node2;
        Node node = removeValue(head, 1);
        Node.print(node);

    }
}
