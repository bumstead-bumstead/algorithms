import sys


def sol():
    n, m = map(int, sys.stdin.readline().split(" "))

    sj = list(map(int, sys.stdin.readline().split(" ")))
    parent = [i for i in range(0, n+1)]
    gods = [0, sj]
    edges = []
    cost = 0

    for i in range(2, n+1):
        x, y = map(int, sys.stdin.readline().split(" "))

        for j in range(1, len(gods)):
            x2, y2 = gods[j]

            d = (x2 - x) ** 2 + (y2 - y) ** 2
            edges.append((d, i, j))

        gods.append([x, y])

    for i in range(0, m):
        x, y = map(int, sys.stdin.readline().split(" "))
        union(x, y, parent)

    edges.sort()

    for i in range(0, len(edges)):
        d, x, y = edges[i]

        if union(x, y, parent):
            cost += d**(1/2)

    print(format(cost, ".2f"))


def union(x, y, parent):
    x = find(x, parent)
    y = find(y, parent)

    if x == y:
        return False

    if x > y:
        parent[x] = y
    else :
        parent[y] = x

    return True


def find(x, parent):
    if x == parent[x]:
        return x

    return find(parent[x], parent)

sol()