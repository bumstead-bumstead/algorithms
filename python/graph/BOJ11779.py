import sys
from heapq import heappush, heappop

"""
- 간선 수가 매우 많아서, 탐색 중에 목적지까지의 비용보다 적은 간선은 무시해버려야함  
"""
n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
graph = [[] for _ in range(0, n + 1)]
pre = [0 for _ in range(0, n + 1)]
costs = [sys.maxsize for _ in range(0, n + 1)]

for _ in range(0, m):
    u, v, w = list(map(int, sys.stdin.readline().split(" ")))
    graph[u].append([w, v])

s, e = map(int, sys.stdin.readline().split(" "))
q = [[0, s]]  # [비용, 도착지]
costs[s] = 0

while q:
    cost, node = heappop(q)

    if cost > costs[e] :
        continue

    for nxt_cost, nxt_node in graph[node]:
        new_cost = cost + nxt_cost
        if new_cost < costs[nxt_node]:
            costs[nxt_node] = new_cost
            heappush(q, [new_cost, nxt_node])
            pre[nxt_node] = node

route = [e]

tmp = e
while tmp != s:
    tmp = pre[tmp]
    route.append(tmp)

print(costs[e])
print(len(route))
print(*route[::-1], sep=" ")

