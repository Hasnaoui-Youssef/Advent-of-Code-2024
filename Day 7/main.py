def find_x(numbers, x):
    def dfs(index, curr):
        if index == len(numbers):
            return curr == x
        if(dfs(index + 1, curr + numbers[index]) or dfs(index + 1, curr * numbers[index])):
            return True
        return False
    return dfs(1, numbers[0])


file = open("input.txt").read().strip()
lines = file.split('\n')
count = 0
for line in lines:
    [a, b] = line.split(':')
    numbers_str = b.strip().split(' ')
    numbers = [int(x) for x in numbers_str]
    if find_x(numbers, int(a)):
        count += int(a)
print(count)