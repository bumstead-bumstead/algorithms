import sys
from collections import deque
from heapq import *

"""
1. 각 연결된 방에 번호 붙이기
2. 각 방에서부터 다른 방까지의 거리 (부숴야하는 벽의 개수) BFS 탐색으로 계산하기
3. 다익스트라로 1번 방부터 마지막 방 까지 거리 계산
"""


def sol():
    m, n = map(int, sys.stdin.readline().split(" "))
    board = []
    dr = [1, -1, 0, 0]
    dc = [0, 0, 1, -1]

    # 미로 초기화
    for i in range(0, n):
        board.append(list(sys.stdin.readline())[:-1])
        for j in range(0, len(board[i])):
            board[i][j] = int(board[i][j])

    visited = [[0 for _ in range(0, m)] for _ in range(0, n)]
    q = deque()
    cnt = 1
    boundaries = [set(), set()]

    # 각각의 연결된 빈 공간 정의, 빈 공간 모서리 인덱스 계산
    for i in range(0, n):
        for j in range(0, m):
            if visited[i][j] or board[i][j] == 1:
                continue
            cnt += 1
            q.append([i, j])
            boundaries.append(set())
            visited[i][j] = True

            while q:
                r, c = q.popleft()
                board[r][c] = cnt

                for k in range(0, 4):
                    r_n = r + dr[k]
                    c_n = c + dc[k]

                    if r_n < 0 or r_n > n - 1 or c_n < 0 or c_n > m - 1 or visited[r_n][c_n]:
                        continue
                    if board[r_n][c_n] == 1:
                        boundaries[cnt].add((r, c)) ######
                        continue

                    q.append([r_n, c_n])
                    visited[r_n][c_n] = 1

    if cnt == 2:
        return 0
    start = board[0][0]
    end = board[n - 1][m - 1]
    graph = [{} for _ in range(0, cnt + 1)]

    # boundaries 원소들 set -> list 변환
    for i in range(0, len(boundaries)):
        boundaries[i] = list(boundaries[i])

    # 인접한 노드 구하기
    for i in range(2, len(boundaries)):
        visited = [[0 for _ in range(0, m)] for _ in range(0, n)]
        r, c = boundaries[i][0]
        q.append([r, c])

        while q:
            r, c = q.popleft()
            for k in range(0, 4):
                r_n = r + dr[k]
                c_n = c + dc[k]

                if r_n < 0 or r_n > n - 1 or c_n < 0 or c_n > m - 1 or visited[r_n][c_n]:
                    continue
                if board[r_n][c_n] != 1 and board[r_n][c_n] != i:
                    graph[i][board[r_n][c_n]] = 201
                    break

                q.append([r_n, c_n])
                visited[r_n][c_n] = True

    # 각 노드 별 거리 계산
    for i in range(2, len(boundaries)):
        tmp = graph[i].keys()

        for j in tmp:
            dist = graph[i].get(j)

            if dist != 201:
                continue

            for r1, c1 in boundaries[i]:
                for r2, c2 in boundaries[j]:
                    dist = min(dist, abs(r1 - r2) + abs(c1 - c2))

            graph[i][j] = dist - 1  # [거리, 다음 노드]
            graph[j][i] = dist - 1

    # 다익스트라

    costs = [float('inf') for _ in range(0, len(graph))]
    q = [[0, start]]
    costs[start] = 0

    while q:
        cost, node = heappop(q)

        for nxt_node in graph[node].keys():
            nxt_cost = graph[node].get(nxt_node)

            if costs[nxt_node] > cost + nxt_cost:
                costs[nxt_node] = cost + nxt_cost
                heappush(q, [cost + nxt_cost, nxt_node])
    return costs[end]


print(sol())
#
# for a in board:
#     print(a)
# print("------------------------")
# for a in graph:
#     print(a)
