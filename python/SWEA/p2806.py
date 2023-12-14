dr = [1, 1, -1, -1]
dc = [1, -1, 1, -1]


def diagonal(row, col, dir):
    if row < 0 or col < 0 or row >= n or col >= n:
        return True
    if board[row][col] == 1:
        return False
    return diagonal(row + dr[dir], col + dc[dir], dir)


def possible(row, col):
    for i in range(n):
        if board[i][col] == 1:
            return False
    for i in range(4):
        if not diagonal(row, col, i):
            return False
    return True


def dfs(depth):
    global cnt
    if depth == n:
        cnt += 1
        return
    for i in range(n):
        if possible(depth, i) :
            board[depth][i] = 1
            dfs(depth + 1)
            board[depth][i] = 0


T = int(input())


for test_case in range(1, T + 1):
    cnt = 0
    n = int(input())
    board = [[0 for _ in range(n)] for _ in range(n)]
    dfs(0)

    print("#%d %d" % (test_case, cnt))