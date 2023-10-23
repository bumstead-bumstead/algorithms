import sys

"""
- 양방향이 아닌 경우, 존재하지 않는 길을 비용이 1인 길로 만든다
- 최소 루트의 비용이 양방향이 되어야하는 길이 댄당
"""

n, m = map(int, sys.stdin.readline().split(" "))
d = [[float('inf') for _ in range(0, n + 1)] for _ in range(0, n + 1)]

for i in range(1, n+1):
    d[i][i] = 0

for i in range(0, m):
    u, v, b = list(map(int, sys.stdin.readline().split(" ")))
    d[u][v] = 0
    d[v][u] = 0
    if not b:
        d[v][u] = 1

for k in range(1, n+1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            d[i][j] = min(d[i][j], d[i][k] + d[k][j])

k = int(sys.stdin.readline())

for _ in range(0, k):
    u, v = map(int, sys.stdin.readline().split(" "))

    sys.stdout.write(str(d[u][v]))
    sys.stdout.write("\n")

