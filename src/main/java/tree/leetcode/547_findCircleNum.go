package leetcode

func findCircleNum(m [][]int) int {
	if m == nil || len(m) == 0 {
		return 0
	}
	visited := make([]bool, len(m))
	res := 0
	for i := range m {
		if !visited[i] {
			dfs(m, i, visited)
			res++
		}

	}

	return res
}

func dfs(m [][]int, cur int, visited []bool) {
	visited[cur] = true
	for i := 0; i < len(m[cur]); i++ {
		if !visited[i] && m[cur][i] == 1 {
			visited[i] = true
			dfs(m, i, visited)
		}
	}
}
func bfs(m [][]int, cur int, visited []bool) (ans int) {
	visited = make([]bool, len(m))
	for i, v := range visited {
		if !v {
			ans++
			queue := []int{i}
			for len(queue) > 0 {
				from := queue[0]
				queue = queue[1:]
				visited[from] = true
				for to, val := range m[from] {
					if val == 1 && !visited[to] {
						queue = append(queue, to)
					}
				}
			}
		}
	}
	return
}
