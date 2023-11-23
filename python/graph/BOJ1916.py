'''
1. 특정한 정점에서 다른 정점 j까지의 최소 거리를 유지하는 배열 d[j] 유지
2. 정점 j에 대해 연결된 다른 정점까지의 최소 거리 최신화
'''

from heapq import *
import sys

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[] for _ in range(n + 1)]

for _ in range(m):
    f, t, cost = map(int, sys.stdin.readline().split())
    graph[f].append([cost, t])

f, t = map(int, sys.stdin.readline().split())
d = [float('INF') for _ in range(n+1)]
d[f] = 0
q = []
heappush(q, [0, f])

while q:
    cost, node = heappop(q)
    if d[node] != cost:
        continue

    for nxt_cost, nxt_node in graph[node]:
        tmp_cost = d[node] + nxt_cost
        if tmp_cost >= d[nxt_node]:
            continue
        d[nxt_node] = tmp_cost
        heappush(q, [tmp_cost, nxt_node])

print(d[t])
