def find_x(numbers, x):
    def dfs(index, curr):
        if index == len(numbers):
            return curr == x
        if(dfs(index + 1, curr + int(numbers[index])) or dfs(index + 1, curr * int(numbers[index])) or dfs(index + 1, int(str(curr) + numbers[index]))):
            return True
        return False
    return dfs(1, int(numbers[0]))


file = open("input.txt").read().strip()
lines = file.split('\n')
count = 0
for line in lines:
    [a, b] = line.split(':')
    numbers_str = b.strip().split(' ')
    if find_x(numbers_str, int(a)):
        count += int(a)
print(count)