package main

import (
	"fmt"
	"os"
	"regexp"
	"strconv"
)

const safeMulReg = `mul\([0-9]{1,3},[0-9]{1,3}\)|do\(\)|don't\(\)`
const twoNumbersReg = `([0-9]{1,3}),([0-9]{1,3})`

func main() {
	safeMulRegex := regexp.MustCompile(safeMulReg)
	twoNumberRegex := regexp.MustCompile(twoNumbersReg)
	data, _ := os.ReadFile("input.txt")
	s := string(data)
	do := true
	ans := 0
	safeMatches := safeMulRegex.FindAllString(s, -1)
	for _, val := range safeMatches {
		if val == "don't()" {
			do = false
		} else if val == "do()" {
			do = true
		} else {
			if do {
				match := twoNumberRegex.FindStringSubmatch(val)
				val1, _ := strconv.Atoi(match[1])
				val2, _ := strconv.Atoi(match[2])
				ans += (val1 * val2)
			}
		}
	}
	fmt.Println(ans)

}
