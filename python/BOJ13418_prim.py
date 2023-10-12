import sys
import queue


def sol():
    n, m = map(int, sys.stdin.readline().split(" "))
    maxq = queue.PriorityQueue() #오르막길 최대
    minq = queue.PriorityQueue()
    edges = [[] for _ in range(0, n+1)]
    max = 0
    min = 0
    visited = [0 for _ in range(0, n+1)]
    visited[0] = 1
    visited[1] = 1

    start = list(map(int, sys.stdin.readline().split(" ")))
    maxq.put((start[2], [start[1], start[2]])) # (목적지 노드, 가중치)
    minq.put(((-1)*start[2], [start[1], start[2]])) # (목적지 노드, 가중치)

    max += start[2]

    for i in range(0, m-1):
        tmp = list(map(int, sys.stdin.readline().split(" ")))
        edges[tmp[0]].append([tmp[1], tmp[2]]) # (목적지 노드, 가중치)
        edges[tmp[1]].append([tmp[0], tmp[2]])

    for _ in range(0, n):
        if maxq.empty():
            break
        tmp = maxq.get()
        max += (tmp[1][1] + 1) % 2
        visited[tmp[1][0]] = 1

        for a in edges[tmp[1][0]]:
            if visited[a[0]]:
                continue
            maxq.put((a[1], a))

    visited = [0 for _ in range(0, n+1)]
    visited[0] = 1
    visited[1] = 1

    for _ in range(0, n):
        if minq.empty():
            break
        tmp = minq.get()
        min += (tmp[1][1] + 1) % 2
        visited[tmp[1][0]] = 1

        for a in edges[tmp[1][0]]:
            if visited[a[0]]:
                continue
            minq.put(((-1)*a[1], a))

    print(max**2 - min**2)

sol()
