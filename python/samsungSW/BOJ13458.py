import math

n = int(input())
a = list(map(int, input().split()))
b, c = map(int, input().split())
answer = n

for man in a:
    answer += max(0, math.ceil((man-b) / c))

print(answer)