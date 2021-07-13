package _go

func maxInt(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func lengthOfLongestSubstring(s string) int {
	maxLen, start := 0, 0
	table := [128]int{}

	for i := range table {
		table[i] = -1
	}

	for i, c := range s {
		if table[c] >= start {
			start = table[c] + 1
		}
		table[c] = i
		maxLen = maxInt(maxLen, i-start+1)
	}
	return maxLen
}

func lengthOfLongestSubstring1(s string) int {
	len, pre, cur := 0, -1, 0
	table := [128]int{}

	for i := range table {
		table[i] = -1
	}
	for i, c := range s {
		pre = maxInt(pre, table[c])
		cur = i - pre
		len = maxInt(len, cur)
		table[c] = i
	}
	return len
}
