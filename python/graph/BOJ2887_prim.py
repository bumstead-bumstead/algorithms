import sys
from heapq import *

n = int(sys.stdin.readline())
q = []

nodes = [0]
graph = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

for _ in range(n):
    x, y, z = map(int, sys.stdin.readline().split())
    nodes.append([x, y, z])

for i in range(1, len(nodes)):
    for j in range(i + 1, len(nodes)):
        d = min(abs(nodes[i][0] - nodes[j][0]), abs(nodes[i][1] - nodes[j][1]), abs(nodes[i][2] - nodes[j][2]))
        graph[i][j] = d
        graph[j][i] = d

visited = [0 for _ in range(n+1)]
answer = 0

for i in range(1, n+1):
    heappush(q, [graph[1][i], i])
visited[1] = 1

cnt = 0

while cnt < n - 1:
    cost, nxt = heappop(q)

    if visited[nxt]:
        continue

    visited[nxt] = 1
    answer += cost
    cnt += 1

    for i in range(1, n+1):
        if visited[i]:
            continue
        heappush(q, [graph[nxt][i], i])

print(answer)
