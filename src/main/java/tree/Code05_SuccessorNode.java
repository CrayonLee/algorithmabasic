package tree;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回指定节点的后继节点
 */
public class Code05_SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }


    }

    static List<Node> res = new ArrayList<>();

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }

        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 暴力解法  返回二叉树的中序遍历序列 然后找出该节点后的节点
     *
     * @param node
     * @return
     */
    public static Node rightWay(Node node) {
        Node parent = node;
        while (parent.parent != null) {
            parent = parent.parent;
        }
        List<Node> in = in(parent);
        int i = in.indexOf(node);
        return i == in.size() - 1 ? null : in.get(i + 1);
    }

    public static List<Node> in(Node head) {

        if (head == null) {
            return res;
        }
        in(head.left);
        res.add(head);
        in(head.right);
        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;


        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " next: " + rightWay(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        System.out.println(test.value + " next: " + rightWay(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
        Node node = rightWay(test);
        if (node == null) {
            System.out.println(test.value + " next: " + null);
        }

    }


}
