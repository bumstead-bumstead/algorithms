import sys
from heapq import heappop, heappush

v, e = map(int, sys.stdin.readline().split(" "))
k = int(sys.stdin.readline())
graph = [[] for _ in range(0, v + 1)]
costs = [sys.maxsize for _ in range(0, v + 1)]

for _ in range(0, e):
    a, b, c = list(map(int, sys.stdin.readline().split(" ")))
    graph[a].append([c, b])  # (비용, 목적지)

q = [[0, k]]
costs[k] = 0

while q:
    cost, node = heappop(q)

    for nxt_cost, nxt_node in graph[node]:
        new = nxt_cost + cost

        if new < costs[nxt_node]:
            costs[nxt_node] = new
            heappush(q, [new, nxt_node])

for i in range(1, len(costs)):
    if costs[i] == sys.maxsize:
        costs[i] = "INF"

for cost in costs[1:]:
    if cost == sys.maxsize:
        sys.stdout.write("INF")
    else :
        sys.stdout.write(str(cost))
    sys.stdout.write("\n")
