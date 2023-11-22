from heapq import *
import sys

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]
m, n = map(int, sys.stdin.readline().split())

board = []
d = [[float('inf') for _ in range(m)] for _ in range(n)]

for _ in range(n):
    board.append(list(map(int, list(sys.stdin.readline().rstrip()))))

q = []
d[0][0] = 0

heappush(q, [0, 0, 0])  # [비용, row, col]

while q:
    cost, r, c = heappop(q)
    if d[r][c] != cost:
        continue

    for i in range(4):
        nxt_r = r + dr[i]
        nxt_c = c + dc[i]

        if nxt_r < 0 or nxt_r >= n or nxt_c < 0 or nxt_c >= m:
            continue
        nxt_cost = cost + board[nxt_r][nxt_c]
        if d[nxt_r][nxt_c] > nxt_cost:
            d[nxt_r][nxt_c] = nxt_cost
            heappush(q, [nxt_cost, nxt_r, nxt_c])

print(d[n - 1][m - 1])
