package _go

var (
	letterMap = []string{
		" ",    //0
		"",     //1
		"abc",  //2
		"def",  //3
		"ghi",  //4
		"jkl",  //5
		"mno",  //6
		"pqrs", //7
		"tuv",  //8
		"wxyz", //9
	}
	res = []string{}
)

func letterCombinations(digits string) []string {
	if digits == "" {
		return []string{}
	}
	res = []string{}
	process(&digits, 0, "")
	return res
}

func process(digits *string, index int, s string) {
	if index == len(*digits) {
		res = append(res, s)
		return
	}
	num := (*digits)[index]
	letter := letterMap[num-'0']
	for i := range letter {
		process(digits, index+1, s+string(letter[i]))
	}
	return
}
