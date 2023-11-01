import copy
import sys

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


def dfs(start, end, depth, visited):
    global distance
    global route
    if start[0] == end[0] and start[1] == end[1]:
        if distance > depth:
            distance = depth
            route = copy.deepcopy(visited)
            return

    for i in range(4):
        r = (start[0] + dr[i] + n) % n
        c = (start[1] + dc[i] + m) % m

        if visited[r][c] or board[r][c][0] == 0:
            continue

        visited[r][c] = 1
        dfs([r, c], end, depth + 1, visited)
        visited[r][c] = 0


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

            # (공격력, 공격 시간, 행 열 합, 열, 행) 순서
            q.append([board[i][j][0], -board[i][j][1], i + j, j, i])
    if len(q) == 1:
        break

    q.sort(reverse=True)

    b = q[0]  # 맞는 놈
    a = q[len(q) - 1]  # 때리는 놈
    board[a[4]][a[3]][0] += (n + m)
    board[a[4]][a[3]][1] = rep + 1
    a[0] += (n + m)

    print("공격 : 공격력 - ", a[0], ", (", a[4], a[3], ") to (", b[4], b[3], ")")

    dfs([a[4], a[3]], [b[4], b[3]], 0, [[0 for _ in range(m)] for _ in range(n)])
    show(route, "route")
    board[b[4]][b[3]][0] -= a[0]

    if not raser(route):
        # 포탄 공격
        for i in range(8):
            r = (b[4] + dr[i] + n) % n
            c = (b[3] + dc[i] + m) % m

            if r == a[4] and c == a[3]:
                continue

            board[r][c][0] -= a[0] // 2
            route[r][c] = 1
    else:
        route[a[4]][a[3]] = 0
        route[b[4]][b[3]] = 0
        for i in range(n):
            for j in range(m):
                if route[i][j] == 0:
                    continue
                board[i][j][0] -= a[0] // 2

    # 정비
    route[a[4]][a[3]] = 1
    route[b[4]][b[3]] = 1
    for i in range(n):
        for j in range(m):
            if board[i][j][0] <= 0:
                board[i][j][0] = 0
                continue
            if route[i][j] == 1:
                continue
            board[i][j][0] += 1
    show(board, "board")

# 정산
answer = -1
for i in range(n):
    for j in range(m):
        answer = max(answer, board[i][j][0])

print(answer)

# dfs -> bfs로 수정해보기