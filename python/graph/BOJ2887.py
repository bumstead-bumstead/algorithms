import sys
from heapq import *


def union(x, y):
    x = find(x)
    y = find(y)

    if x == y:
        return False
    elif x > y:
        parent[x] = y
    else:
        parent[y] = x

    return True


def find(x):
    if parent[x] == x:
        return x

    return find(parent[x])


n = int(sys.stdin.readline())
parent = [i for i in range(n + 1)]
q = []

nodes = [0]

for _ in range(n):
    x, y, z = map(int, sys.stdin.readline().split())
    nodes.append([x, y, z])

for i in range(1, len(nodes)):
    for j in range(i + 1, len(nodes)):
        d = min(abs(nodes[i][0] - nodes[j][0]), abs(nodes[i][1] - nodes[j][1]), abs(nodes[i][2] - nodes[j][2]))
        heappush(q, [d, i, j])  # [거리, 시작점, 도착점]

answer = 0
edge_n = 0

while edge_n < n - 1:
    d, start, end = heappop(q)

    if union(start, end):
        answer += d
        edge_n += 1

print(answer)
