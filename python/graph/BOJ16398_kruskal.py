import sys


def find(x):
    if parents[x] == x:
        return x
    parents[x] = find(parents[x])
    return parents[x]


def union(x, y):
    x = find(x)
    y = find(y)

    if x == y:
        return False
    elif x < y:
        parents[y] = x
    else:
        parents[x] = y
    return True


n = int(sys.stdin.readline())
graph = []
parents = [i for i in range(n)]

for _ in range(n):
    graph.append(list(map(int, sys.stdin.readline().split())))

edges = []

for i in range(n):
    for j in range(i+1, n):
        edges.append([graph[i][j], i, j])

edges.sort()
answer = 0
cnt = 0

for cost, f, t in edges:
    if cnt == n-1:
        break
    if union(f, t):
        cnt += 1
        answer += cost

print(answer)
