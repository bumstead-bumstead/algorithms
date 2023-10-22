import sys

# sys.maxsize 호출하는게 훨씬 느리다. float 생성자로 최댓값 가져오기

v, e = map(int, sys.stdin.readline().split(" "))
d = [[float('inf') for _ in range(0, v + 1)] for _ in range(0, v + 1)]

for _ in range(0, e):
    a, b, c = list(map(int, sys.stdin.readline().split(" ")))
    d[a][b] = c

for k in range(1, v + 1):
    for i in range(1, v + 1):
        for j in range(1, v + 1):
            d[i][j] = min(d[i][j], d[i][k] + d[k][j])

answer = float('inf')

for i in range(1, v+1):  # i -> j -> i 사이클
    answer = min(answer, d[i][i])

if answer == float('inf'):
    print(-1)
else:
    print(answer)