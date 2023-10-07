import sys
import collections

board = []
dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]


def drop(visited):
    distance = 101
    for j in range(0, len(board[0])):
        for i in range(len(board) - 1, -1, -1):
            if not visited[i][j]:
                continue

            for k in range(i+1, len(board)):
                if k == len(board)-1:
                    distance = min(distance, k - i)
                if board[k][j] == 'x':
                    distance = min(distance, k - i - 1)
                    break
            break

    distance = max(distance, 1)

    for j in range(0, len(board[0])): #열
        for i in range(len(board) - 1, -1, -1):
            if not visited[i][j]:
                continue

            board[i][j], board[i+distance][j] = board[i+distance][j], board[i][j]



def shot(row, col):
    board[row][col] = '.'
    queue = collections.deque()

    for i in range(0, 4):
        r = row + dr[i]
        c = col + dc[i]
        if r < 0 or r >= len(board) or c < 0 or c >= len(board[0]) or board[r][c] == '.':
            continue

        visited = [[False for _ in range(0, len(board[0]))] for _ in range(0, len(board))]

        queue.append([r, c])
        visited[r][c] = True
        d = True

        while len(queue) != 0:
            tmp = queue.popleft()

            for k in range(0, 4):
                r = tmp[0] + dr[k]
                c = tmp[1] + dc[k]
                if r < 0 or r >= len(board) or c < 0 or c >= len(board[0]) or visited[r][c] or board[r][c] == '.':
                    continue

                if r == len(board) - 1:
                    d = False
                    queue.clear()
                    break

                queue.append([r, c])
                visited[r][c] = True

        if d:
            drop(visited)


def sol():
    global board
    global h
    R, C = map(int, sys.stdin.readline().split())
    board = [['.' for _ in range(0, C)] for _ in range(0, R)]

    for i in range(0, R):
        board[i] = list(sys.stdin.readline())[0:-1]

    N = int(sys.stdin.readline())
    commands = list(map(int, sys.stdin.readline().split()))

    for turn in range(0, N):
        target = len(board) - commands[turn]

        if turn % 2 == 0:
            for i in range(0, len(board[0])):
                if board[target][i] == '.':
                    continue

                shot(target, i)
                break
        else:
            for i in range(len(board[0]) - 1, -1, -1):
                if board[target][i] == '.':
                    continue

                shot(target, i)
                break

    for row in board:
        print(*row, sep="")

if __name__ == '__main__':
    sol()
