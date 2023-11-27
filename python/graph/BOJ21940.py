import sys

n, m = map(int, sys.stdin.readline().split())

d = [[float('inf') for _ in range(n + 1)] for _ in range(n + 1)]
for i in range(1, n + 1):
    d[i][i] = 0

for _ in range(m):
    a, b, t = map(int, sys.stdin.readline().split())
    d[a][b] = t

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            d[i][j] = min(d[i][j], d[i][k] + d[k][j])

n_friends = int(sys.stdin.readline())
friends = list(map(int, sys.stdin.readline().split()))

max_dist = [-1 for _ in range(n + 1)]

for i in range(1, n + 1):
    for k in friends:
        max_dist[i] = max(d[i][k] + d[k][i], max_dist[i])

min_dist = min(max_dist[1:])

answer = []

for i in range(1, len(max_dist)):
    if max_dist[i] == min_dist:
        answer.append(i)

answer.sort()

print(*answer)