import copy
import sys
from collections import deque

# 우하좌상
dr = [0, 1, 0, -1, 1, 1, -1, -1]
dc = [1, 0, -1, 0, 1, -1, 1, -1]

distance = 101
n, m, k = list(map(int, sys.stdin.readline().split()))
board = [[] for _ in range(n)]
route = [[0 for _ in range(m)] for _ in range(n)]

q = []


def raser(board):
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j]:
                return True
    return False


def show(board, str):
    print("-------", str, "---------")
    for a in board:
        print(*a)
    print("----------------")


def bfs(board, start, target):
    global route
    nxt = deque()
    nxt.append(start)
    route[start[0]][start[1]] = start

    while nxt:
        node = nxt.popleft()

        if node == target:
            return True

        for i in range(4):
            nxt_r = (node[0] + dr[i] + n) % n
            nxt_c = (node[1] + dc[i] + m) % m

            if route[nxt_r][nxt_c] != 0 or board[nxt_r][nxt_c][0] == 0:
                continue

            route[nxt_r][nxt_c] = node
            nxt.append([nxt_r, nxt_c])

    return False


for i in range(n):
    tmp = list(map(int, sys.stdin.readline().split()))
    for j in range(m):
        board[i].append([tmp[j], 0])  # (공격력, 공격 시간)

for rep in range(k):
    distance = 101
    route = [[0 for _ in range(m)] for _ in range(n)]
    q = []

    for i in range(n):
        for j in range(m):
            if board[i][j][0] == 0:
                continue

            # (공격력, 공격 시간, 행 열 합, 열, 행, 열) 순서 -> - + + +
            q.append([board[i][j][0], -board[i][j][1], -(i + j), -j, i, j])
    if len(q) == 1:
        break

    q.sort(reverse=True)

    b = q[0]  # 맞는 놈
    a = q[len(q) - 1]  # 때리는 놈
    board[a[4]][a[5]][0] += (n + m)
    board[a[4]][a[5]][1] = rep + 1
    a[0] += (n + m)

    # print("공격 : 공격력 - ", a[0], ", (", a[4], a[5], ") to (", b[4], b[5], ")")

    visited = [[0 for _ in range(m)] for _ in range(n)]

    if not bfs(board, [a[4], a[5]], [b[4], b[5]]):
        # 포탄 공격
        # print("포탄 공격")
        for i in range(8):
            r = (b[4] + dr[i] + n) % n
            c = (b[5] + dc[i] + m) % m

            if r == a[4] and c == a[5]:
                continue

            board[r][c][0] -= a[0] // 2
            visited[r][c] = 1
    else:
        # print("레이저 공격")
        tmp = route[b[4]][b[5]]
        while tmp != [a[4], a[5]]:
            visited[tmp[0]][tmp[1]] = 1
            board[tmp[0]][tmp[1]][0] -= a[0] // 2
            # print("공격 :", tmp)
            tmp = route[tmp[0]][tmp[1]]

    board[b[4]][b[5]][0] -= a[0]

    # 정비
    visited[a[4]][a[5]] = 1
    visited[b[4]][b[5]] = 1
    for i in range(n):
        for j in range(m):
            if board[i][j][0] <= 0:
                board[i][j][0] = 0
                continue
            if visited[i][j] == 0:
                board[i][j][0] += 1

    # show(board, "board")

# 정산
answer = -1
for i in range(n):
    for j in range(m):
        answer = max(answer, board[i][j][0])

print(answer)
