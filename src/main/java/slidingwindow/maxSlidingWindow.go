package main

import (
	"fmt"
)

func maxSlidingWindow(nums []int, k int) []int {
	if len(nums) == 0 || len(nums) < k {
		return nil
	}
	//存储下标
	window := make([]int, 0, k)
	res := make([]int, 0, len(nums)-k+1)

	for i, v := range nums {
		for len(window) != 0 && nums[window[len(window)-1]] <= v {
			window = window[0 : len(window)-1]
		}
		window = append(window, i)
		//判断当前位置是否过期
		if window[0] == i-k {
			window = window[1:len(window)]
		}
		//更新
		if i >= k-1 {
			res = append(res, nums[window[0]])
		}
	}
	return res

}

func main() {
	var a = []int{1, 3, -1, -3, 5, 3, 6, 7}
	window := maxSlidingWindow(a, 3)
	fmt.Printf("%v", window)
}
