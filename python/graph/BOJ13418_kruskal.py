import sys


def sol():
    edges_max = []
    n, m = map(int, sys.stdin.readline().split(" "))
    p_max = [i for i in range(0, m+1)]
    p_min = [i for i in range(0, m+1)]
    ormak_max = 0
    ormak_min = 0

    ent = list(map(int, sys.stdin.readline().split(" ")))

    if ent[2] == 0:
        ormak_max += 1
        ormak_min += 1

    for _ in range(0, m):
        a, b, c = map(int, sys.stdin.readline().split(" "))
        edges_max.append((c, a, b))

    edges_max.sort()
    edges_min = sorted(edges_max, reverse=True)

    for i in range(0, len(edges_max)):
        edge = edges_max[i]
        edge2 = edges_min[i]

        if union(edge[1], edge[2], p_max):
            ormak_max += (edge[0] + 1) % 2
        if union(edge2[1], edge2[2], p_min):
            ormak_min += (edge2[0] + 1) % 2

    print(ormak_max**2 - ormak_min**2)

def union(x, y, parent):
    x = find(x, parent)
    y = find(y, parent)

    if x == y:
        return False
    elif x > y:
        parent[x] = y
    else :
        parent[y] = x

    return True

def find(x, parent):
    if parent[x] == x:
        return x

    return find(parent[x], parent)

sol()