package _go

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	size := len(nums1) + len(nums2)
	even := (size & 1) == 0
	if len(nums1) != 0 && len(nums2) != 0 {
		if even {
			return float64(findKthNum(nums1, nums2, size/2)+findKthNum(nums1, nums2, size/2+1)) / 2
		} else {
			return float64(findKthNum(nums1, nums2, size/2+1))
		}
	} else if len(nums1) != 0 {
		if even {
			return float64(nums1[(size-1)/2]+nums1[size/2]) / 2
		} else {
			return float64(nums1[size/2])
		}
	} else if len(nums2) != 0 {
		if even {
			return float64(nums2[(size-1)/2]+nums2[size/2]) / 2
		} else {
			return float64(nums2[size/2])
		}

	} else {
		return 0
	}
}

func findKthNum(arr1, arr2 []int, k int) int {
	l, s := 0, 0
	if len(arr1) >= len(arr2) {
		l = len(arr1)
		s = len(arr2)
	} else {
		l = len(arr2)
		s = len(arr1)
	}
	longs := make([]int, l)
	shorts := make([]int, s)
	if len(arr1) >= len(arr2) {
		longs = arr1
	} else {
		longs = arr2
	}

	if len(arr1) < len(arr2) {
		shorts = arr1
	} else {
		shorts = arr2
	}
	if k <= s {
		return getUpMedian(shorts, 0, k-1, longs, 0, k-1)
	}
	if k > 1 {
		if shorts[k-l-1] >= longs[l-1] {
			return shorts[k-l-1]
		}

		if (longs[k-s-1]) >= shorts[s-1] {
			return longs[k-s+1]
		}
		return getUpMedian(shorts, k-1, s-1, longs, k-s, l-1)
	}
	//第二段
	if longs[k-s-1] >= shorts[s-1] {
		return longs[k-s-1]
	}
	return getUpMedian(shorts, 0, s-1, longs, k-s, k-1)
}
func getUpMedian(a []int, s1, e1 int, b []int, s2, e2 int) int {
	mid1, mid2 := 0, 0
	for mid1 < mid2 {
		mid1 = (s1 + e1) / 2
		mid2 = (s2 + e2) / 2
		if a[mid1] == b[mid2] {
			return a[mid1]
		}
		//如果数组长度是奇数时
		if ((e1 - s1 + 1) & 1) == 1 {
			if a[mid1] > b[mid2] {
				if b[mid2] >= a[mid1] {
					return b[mid2]
				}
				e1 = mid1 - 1
				s2 = mid2 + 1
			} else {
				//a[mid1]<b[mid2]
				if a[mid1] >= b[mid2-1] {
					return a[mid1]
				}
				e2 = mid2 - 1
				s1 = mid1 + 1
			}
		} else {
			//偶数长度
			if a[mid1] > b[mid2] {
				e1 = mid1
				s2 = mid2 + 1
			} else {
				e2 = mid2
				s1 = mid1 + 1
			}
		}
	}
	return min(a[s1], b[s2])
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
