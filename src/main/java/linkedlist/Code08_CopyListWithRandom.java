package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 *  复制含有随机指针的链表
 */
public class Code08_CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head) {
        // key 老节点
        // value 新节点
        Map<Node,Node> map = new HashMap<>();
        Node cur =head;
        while(cur!=null){
            map.put(cur,new Node(cur.value));
            cur=cur.next;
        }
        cur=head;
        while(cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).rand=map.get(cur.rand);
            cur=cur.next;
        }
        return map.get(head);
    }
}
