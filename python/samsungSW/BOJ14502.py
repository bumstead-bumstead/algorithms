from collections import deque
import copy

n, m = map(int, input().split())
board = []
dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]


def count():
    tmpBoard = copy.deepcopy(board)
    visited = [[0 for _ in range(m)] for _ in range(n)]
    q = deque()

    for i in range(n):
        for j in range(m):
            if visited[i][j] or tmpBoard[i][j] != '2':
                continue

            q.append([i, j])
            visited[i][j] = 1

            while q:
                row, col = q.popleft()

                for k in range(4):
                    nxt_row = row + dr[k]
                    nxt_col = col + dc[k]

                    if nxt_row < 0 or nxt_row == n or nxt_col < 0 or nxt_col == m or visited[nxt_row][nxt_col] or tmpBoard[nxt_row][nxt_col] == '1':
                        continue

                    tmpBoard[nxt_row][nxt_col] = '2'
                    visited[nxt_row][nxt_col] = 1
                    q.append([nxt_row, nxt_col])

    result = 0

    for i in range(n):
        for j in range(m):
            if tmpBoard[i][j] == '0':
                result += 1

    return result


for _ in range(n):
    board.append(input().split())

answer = 0
for i in range(n * m):
    for j in range(i + 1, n * m):
        for k in range(j + 1, n * m):
            pos1 = [i // m, i % m]
            pos2 = [j // m, j % m]
            pos3 = [k // m, k % m]
            if board[pos1[0]][pos1[1]] != '0' or board[pos2[0]][pos2[1]] != '0' or board[pos3[0]][pos3[1]] != '0':
                continue

            board[pos1[0]][pos1[1]] = '1'
            board[pos2[0]][pos2[1]] = '1'
            board[pos3[0]][pos3[1]] = '1'

            answer = max(answer, count())

            board[pos1[0]][pos1[1]] = '0'
            board[pos2[0]][pos2[1]] = '0'
            board[pos3[0]][pos3[1]] = '0'

print(answer)
