import sys

n, k = map(int, sys.stdin.readline().split())
line = list(sys.stdin.readline())

p = 0
h = 0
answer = 0

while True:
    while p < n and line[p] != 'P':
        p += 1
    while h < n and line[h] != 'H':
        h += 1

    if p == n or h == n:
        break

    if abs(p - h) <= k:
        answer += 1
        p += 1
        h += 1
    elif p > h:
        h += 1
    else:
        p += 1

print(answer)