import sys
from heapq import *

n = int(sys.stdin.readline())
graph = []
parents = [i for i in range(n)]

for _ in range(n):
    graph.append(list(map(int, sys.stdin.readline().split())))

q = []
visited = [0 for _ in range(n)]
answer = 0
cnt = 0
visited[0] = 1

for i in range(len(graph[0])):
    if visited[i]:
        continue
    heappush(q, [graph[0][i], i])

while cnt < n - 1:
    cost, t = heappop(q)
    if visited[t]:
        continue
    visited[t] = 1
    answer += cost
    cnt += 1

    for i in range(len(graph[0])):
        if visited[i]:
            continue
        heappush(q, [graph[t][i], i])

print(answer)
