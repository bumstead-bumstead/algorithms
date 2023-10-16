import sys
from heapq import *

"""
1. 각 노드별로 가장 가까운 면접장, 거리, 경로 저장
2. 경로 상에 있는 다른 노드들을 visited

"""
n, m, k = list(map(int, sys.stdin.readline().split()))
graph = [[] for _ in range(0, n + 1)]
visited = [0 for _ in range(0, n + 1)]

for _ in range(0, m):
    u, v, c = list(map(int, sys.stdin.readline().split()))
    graph[u].append([c, v])  # [거리, 목적지]

meeting = list(map(int, sys.stdin.readline().split()))

for a in meeting:
    visited[a] = 1

answer = [0, 0]  # [비용, 위치]

for i in range(1, n + 1):
    if visited[i]:
        continue
    q = [[0, i]]
    costs = [float('inf') for _ in range(0, n + 1)]
    prev = [float('inf') for _ in range(0, n + 1)]
    costs[i] = 0

    while q:
        cost, node = heappop(q)

        for nxt_cost, nxt_node in graph[node]:
            if nxt_cost + cost < costs[nxt_node]:
                prev[nxt_node] = node
                costs[nxt_node] = nxt_cost + cost
                heappush(q, [costs[nxt_node], nxt_node])

    # 제일 가까운 면접장 고르기
    tmp_cost = float('inf')
    tmp_meeting = 0
    for a in meeting:
        if costs[a] < tmp_cost:
            tmp_cost = costs[a]
            tmp_meeting = a

    # 그 중간에 있는 것 visited
    while tmp_meeting != i:
        tmp_meeting = prev[tmp_meeting]
        visited[tmp_meeting] = 1

    if answer[0] < tmp_cost:
        answer[0] = tmp_cost
        answer[1] = tmp_meeting

print(answer[1])
print(answer[0])

