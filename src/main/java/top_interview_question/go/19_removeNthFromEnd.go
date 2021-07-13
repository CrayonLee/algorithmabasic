package _go

type ListNode struct {
	Val  int
	Next *ListNode
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	var cur, pre *ListNode
	cur, pre = head, nil

	for cur != nil {
		n--
		if n == -1 {
			pre = head
		}
		if n < -1 {
			pre = pre.Next
		}
		cur = cur.Next
	}

	if n > 0 {
		return head
	}
	if pre == nil {
		return head.Next
	}
	pre.Next = pre.Next.Next

	return head
}
