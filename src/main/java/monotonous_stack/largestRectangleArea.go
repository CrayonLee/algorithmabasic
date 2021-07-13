package monotonous_stack

func largestRectangleArea(heights []int) (maxArea int) {
	if len(heights) == 0 {
		return 0
	}
	maxArea, stack := 0, []int{}

	for i := 0; i < len(heights); i++ {
		for len(stack) != 0 && heights[stack[len(stack)-1]] >= heights[i] {
			j := stack[len(stack)-1]
			stack = stack[0 : len(stack)-1]
			k := -1
			if len(stack) != 0 {
				k = stack[len(stack)-1]
			}
			curArea := (i - k - 1) * heights[j]
			maxArea = maxInt(curArea, maxArea)
		}
		stack = append(stack, i)
	}

	for len(stack) != 0 {
		j := stack[len(stack)-1]
		stack = stack[0 : len(stack)-1]
		k := -1
		if len(stack) != 0 {
			k = stack[len(stack)-1]
		}
		curArea := (len(heights) - k - 1) * heights[j]
		maxArea = maxInt(curArea, maxArea)
	}
	return
}

func maxInt(a, b int) int {
	if a > b {
		return a
	}
	return b
}
