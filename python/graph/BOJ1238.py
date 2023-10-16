import sys
from heapq import *


def dij(start, end, graph):
    costs = [float('inf') for _ in range(0, len(graph))]
    costs[start] = 0
    q = [[0, start]]

    while q:
        cost, node = heappop(q)

        if cost >= costs[end]:
            continue

        for nxt_cost, nxt_node in graph[node]:
            if costs[nxt_node] > cost + nxt_cost:
                costs[nxt_node] = cost + nxt_cost
                heappush(q, [cost + nxt_cost, nxt_node])

    return costs[end]


n, m, x = list(map(int, sys.stdin.readline().split(" ")))
graph = [[] for _ in range(0, n + 1)]

for _ in range(0, m):
    u, v, w = list(map(int, sys.stdin.readline().split(" ")))
    graph[u].append([w, v])  # [시간, 목적지]

answer = -1

for i in range(1, n + 1):
    answer = max(answer, dij(i, x, graph) + dij(x, i, graph))

print(answer)