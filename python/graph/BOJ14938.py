import sys

n, m, r = list(map(int, sys.stdin.readline().split(" ")))
n_items = [0] + list(map(int, sys.stdin.readline().split(" ")))
d = [[sys.maxsize for _ in range(0, n + 1)] for _ in range(0, n + 1)]

for i in range(1, n + 1):
    d[i][i] = 0

for i in range(0, r):
    a, b, c = list(map(int, sys.stdin.readline().split(" ")))
    d[a][b] = c
    d[b][a] = c

for k in range(1, n + 1):  # 중간에 거치는 노드
    for i in range(1, n + 1):  # 출발지 노드
        for j in range(1, n + 1):  # 도착지 노드
            d[i][j] = min(d[i][j], d[i][k] + d[k][j])

answer = 0

for i in range(1, n + 1):
    tmp = 0
    for j in range(1, n + 1):
        if d[i][j] > m:
            continue
        tmp += n_items[j]
    answer = max(answer, tmp)

print(answer)
