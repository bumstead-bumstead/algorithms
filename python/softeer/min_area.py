import sys


def minArea(arr):
    min_x = 1001
    min_y = 1001
    max_x = -1001
    max_y = -1001

    for i in range(1, len(arr)):
        x, y = arr[i]
        min_x = min(x, min_x)
        min_y = min(y, min_y)
        max_x = max(x, max_x)
        max_y = max(y, max_y)

    return (max_x - min_x) * (max_y - min_y)


def find(depth):
    global chosen
    global answer

    if depth == k + 1:
        answer = min(answer, minArea(chosen))
        return

    for x, y in points[depth]:
        chosen[depth] = (x, y)
        find(depth + 1)


n, k = map(int, sys.stdin.readline().split())

chosen = [() for _ in range(k + 1)]
answer = float('inf')

points = [[] for _ in range(k + 1)]

for _ in range(n):
    x, y, c = map(int, sys.stdin.readline().split())
    points[c].append((x, y))

find(1)
print(answer)