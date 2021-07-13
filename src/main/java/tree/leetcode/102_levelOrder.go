package leetcode

/**
* Definition for a binary tree node.
* type TreeNode struct {
* Val int
* Left *TreeNode
* Right *TreeNode
* }
 */

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

//BFS
func levelOrder(root *TreeNode) [][]int {
	if root == nil {
		return [][]int{}
	}
	queue := []*TreeNode{}
	queue = append(queue, root)
	curNum, nextLevelNum, res, tmp := 1, 0, [][]int{}, []int{}
	for len(queue) > 0 {
		if curNum > 0 {
			node := queue[0]
			if node.Left != nil {
				queue = append(queue, node.Left)
				nextLevelNum++
			}

			if node.Right != nil {
				queue = append(queue, node.Right)
				nextLevelNum++
			}
			curNum--
			tmp = append(tmp, node.Val)
			queue = queue[1:]
		}

		if curNum == 0 {
			res = append(res, tmp)
			curNum = nextLevelNum
			nextLevelNum = 0
			tmp = []int{}
		}
	}
	return res
}

//dfs
func levelOrderDFS(root *TreeNode) [][]int {
	res := [][]int{}
	dfsLevel(root, -1, &res)
	return res
}

func dfsLevel(node *TreeNode, level int, res *[][]int) {
	if node == nil {
		return
	}

	curLevel := level + 1
	if len(*res) <= curLevel {
		*res = append(*res, []int{})
	}
	(*res)[curLevel] = append((*res)[curLevel], node.Val)
	dfsLevel(node.Left, curLevel, res)
	dfsLevel(node.Right, curLevel, res)
}
