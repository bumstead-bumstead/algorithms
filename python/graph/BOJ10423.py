import sys
from heapq import heappop, heappush


parent = []
sups_dict = {}


def find(u):
    if parent[u] == u:
        return u

    return find(parent[u])


def union(u, v):
    u = find(u)
    v = find(v)

    if u == v:
        return False

    contains_u = sups_dict.get(u)
    contains_v = sups_dict.get(v)
    if contains_v and contains_u:
        return False

    if contains_u:
        parent[v] = u
    elif contains_v:
        parent[u] = v
    elif u > v:
        parent[u] = v
    else:
        parent[v] = u

    return True


def sol():
    global parent
    n, m, k = map(int, sys.stdin.readline().split(" "))
    sups = list(map(int, sys.stdin.readline().split(" ")))
    parent += list(i for i in range(0, n + 1))
    q = []
    answer = 0

    for sup in sups:
        sups_dict[sup] = 1

    for _ in range(0, m):
        u, v, w = map(int, sys.stdin.readline().split())
        if u < v:
            u, v = v, u
        heappush(q, (w, u, v))

    while len(q) > 0:
        w, u, v = heappop(q)
        if sups_dict.get(find(u)) and sups_dict.get(find(v)):
            continue

        if union(u, v):
            answer += w
    print(answer)


sol()
