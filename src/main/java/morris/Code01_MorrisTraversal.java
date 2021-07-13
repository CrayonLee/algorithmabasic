package morris;

import com.sun.org.apache.bcel.internal.generic.IFNULL;
import sun.net.www.HeaderParser;

import java.util.ArrayList;
import java.util.List;

public class Code01_MorrisTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void process(Node root) {
        if (root == null) {
            return;
        }
        process(root.left);
        process(root.right);
    }

    /**
     * 假设来到当前节点cur，开始时cur来到头节点位置
     * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
     * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
     * a. 如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur = cur.left)
     * b. 如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur = cur.right)
     * 3）cur为空时遍历停止
     */
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }

            }
            cur = cur.right;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight=null;
        while (cur != null) {
            mostRight=cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight=mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.print(cur.value+" ");
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else{
                    mostRight.right=null;
                }
            }else{
                System.out.print( cur.value+" ");
            }
            cur=cur.right;
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur =head;
        Node mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight=mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else{
                    mostRight.right=null;
                }
            }
            System.out.print( cur.value+" ");
            cur=cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur =head;
        Node mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else{
                    mostRight.right=null;
                    printEdge(cur.left);
                }
            }

            cur=cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    private static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur =tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur=cur.right;
        }
        reverseEdge(tail);
    }

    private static Node reverseEdge(Node head) {
        Node pre =null;
        Node next =null;
        while (head!=null){
            next =head.right;
            head.right=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        morrisPre(head);
        morrisIn(head);
        morrisPos(head);
        printTree(head);

    }
}
