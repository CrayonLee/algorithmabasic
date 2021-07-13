package _go

import "sort"

func threeSum(nums []int) [][]int {
	sort.Ints(nums)
	ans := make([][]int, 0)
	for i := 0; i < len(nums)-2; i++ {
		if i == 0 || nums[i-1] != nums[i] {
			sum1 := twoSum1(nums, i+1, -nums[i])
			for i2 := range sum1 {
				ints := sum1[i2]
				ints = append([]int{nums[i]}, ints...)
				ans = append(ans, ints)
			}
		}
	}
	return ans
}

func twoSum1(nums []int, begin, target int) [][]int {
	l, r := begin, len(nums)-1
	ans := make([][]int, 0)
	for l < r {
		if nums[l]+nums[r] > target {
			r--
		} else if nums[l]+nums[r] < target {
			l++
		} else {
			if l == begin || nums[l-1] != nums[l] {
				cur := make([]int, 0)
				cur = append(cur, nums[l])
				cur = append(cur, nums[r])
				ans = append(ans, cur)
			}
			l++
		}
	}
	return ans
}
