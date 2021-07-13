package _go

type ListNode1 struct {
	Val  int
	Next *ListNode1
}

func addTwoNumbers(l1 *ListNode1, l2 *ListNode1) *ListNode1 {
	dummyHead := &ListNode1{Val: 0}
	n1, n2, carry, cur := 0, 0, 0, dummyHead

	for l1 != nil || l2 != nil || carry != 0 {
		if l1 == nil {
			n1 = 0
		} else {
			n1 = l1.Val
			l1 = l1.Next
		}

		if l2 == nil {
			n2 = 0
		} else {
			n2 = l2.Val
			l2 = l2.Next
		}
		cur.Next = &ListNode1{Val: (n1 + n2 + carry) % 10}
		cur = cur.Next
		carry = (n1 + n2 + carry) / 10
	}
	return dummyHead.Next
}
