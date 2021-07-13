package main

import "container/list"

/**
	给定一个整型数组arr，和一个整数num。某个arr中的子数组sub，若是想达标，
必须知足：sub中最大值 – sub中最小值 <= num，返回arr中达标子数组的数量
*/
func num(arr []int, sum int) int {
	n := len(arr)
	if n == 0 || sum < 0 {
		return 0
	}
	count := 0
	R := 0
	maxWindow := list.New().Init()
	minWindow := list.New().Init()

	for L := 0; L < n; L++ {
		for R < n {
			//右扩
			for maxWindow.Len() > 0 && arr[maxWindow.Back().Value.(int)] <= arr[R] {
				maxWindow.Remove(maxWindow.Back())
			}
			maxWindow.PushBack(R)

			for minWindow.Len() > 0 && arr[minWindow.Back().Value.(int)] >= arr[R] {
				minWindow.Remove(minWindow.Back())
			}
			minWindow.PushBack(R)

			//若最大值-最小值>sum十就不往右边扩了
			if arr[maxWindow.Front().Value.(int)]-arr[minWindow.Front().Value.(int)] > sum {
				break
			} else {
				R++
			}
		}
		count += R - L
		//删掉过时窗口i的位置
		if maxWindow.Front().Value.(int) == L {
			maxWindow.Remove(maxWindow.Front())
		}
		if minWindow.Front().Value.(int) == L {
			minWindow.Remove(minWindow.Front())
		}
	}
	return count
}
