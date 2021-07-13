package linkedlist;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

import java.awt.*;

public class Node {
    public  int val;
    public  Node next;



    public Node(int val) {
        this.val = val;
    }



    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }


    public static void print(Node head){
        while(head!=null){
            if(head.next!=null){
                System.out.print(head.val+"->");
            }else{
                System.out.println(head.val+"->NULL");
            }
            head=head.next;
        }
    }
}
