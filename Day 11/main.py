file = open("input.txt").read().strip()
nums = list(map(int, file.split(' ')))
print(nums)

def split_in_two(x) :
    string_num = str(x)
    first_half = string_num[:len(string_num) // 2]
    second_half = string_num[len(string_num) // 2:]
    return int(first_half), int(second_half)

print(split_in_two(5249))

big_dict = dict((n, 1) for n in nums)

def blink(n):
    if n == 0:
        return [1]
    elif len(str(n)) % 2 == 0:
        two_x = split_in_two(n)
        return [two_x[0], two_x[1]]
    else:
        return [n * 2024]
    
for i in range(75):
    temp = dict()
    for num, n in big_dict.items():
        for blinked in blink(num):
            if blinked in temp:
                temp[blinked] += n
            else:
                temp[blinked] = n
    big_dict = temp

ans = sum(n for n in big_dict.values())
print(ans)
