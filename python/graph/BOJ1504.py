import sys
from heapq import *


def dij(start):
    q = []
    costs = [0] + [float('inf') for _ in range(v)]

    costs[start] = 0
    heappush(q, [0, start])

    while q:
        cost, node = heappop(q)

        if costs[node] != cost:
            continue

        for nxt_cost, nxt_node in graph[node]:
            if costs[nxt_node] < cost + nxt_cost:
                continue

            costs[nxt_node] = cost + nxt_cost
            heappush(q, [cost + nxt_cost, nxt_node])

    return costs


v, e = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(v + 1)]
d = []

for _ in range(e):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[a].append([c, b])  # [비용, 목적지]
    graph[b].append([c, a])

t1, t2 = map(int, sys.stdin.readline().split())

costs = dij(1)
d.append(costs[t1])
d.append(costs[t2])

costs = dij(t1)
d.append(costs[t2])
d.append(costs[v])

costs = dij(t2)
d.append(costs[v])

if d[0] + d[2] + d[4] == float('inf') and d[1] + d[2] + d[3] == float('inf'):
    print(-1)
else:
    answer = min(d[0] + d[2] + d[4], d[1] + d[2] + d[3])
    print(answer)
