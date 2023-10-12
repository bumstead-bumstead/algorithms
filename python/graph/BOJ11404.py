import sys


def show(board):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if board[i][j] == sys.maxsize:
                board[i][j] = 0
            sys.stdout.write(str(board[i][j]))
            sys.stdout.write(" ")
        sys.stdout.write("\n")


n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

board = [[sys.maxsize for _ in range(0, n + 1)] for _ in range(0, n + 1)]

for i in range(1, n + 1):
    board[i][i] = 0

for i in range(0, m):
    a, b, c = map(int, sys.stdin.readline().split(" "))

    if board[a][b] < c:
        continue

    board[a][b] = c

for k in range(1, n + 1):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            minimum = min(board[i][j], board[i][k] + board[k][j])
            board[i][j] = minimum
            board[i][j] = minimum

show(board)
sys.stdout.close()
