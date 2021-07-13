package linkedlist;

import java.util.Optional;

public class Code09_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 找到链表的第一个入环节点，若没有，返回null
     * <p>
     * 思想：首先快慢节点走到fast
     *
     * @param head
     * @return
     */
    public static Optional<Node> getLoopNode(Node head) {
        if (head == null || head.next == null || head.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return Optional.ofNullable(slow);
    }

    public static void main(String[] args) {
        Node   	// 1->2->3->4->5->6->7->4...
                head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
        System.out.println(getLoopNode(head1).get().value);
    }
}
