package top_interview_question;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Problem_0023_MergeKSortedLists {
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }



    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length==0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()){
           tail.next=heap.poll();
           tail=tail.next;
           if(tail.next!=null){
               heap.add(tail.next);
           }
        }
        return dummy.next;
    }
}
