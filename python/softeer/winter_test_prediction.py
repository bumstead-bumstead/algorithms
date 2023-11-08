import sys
from collections import deque


def show(board):
    print("-------------------")
    for row in board:
        print(*row)
    print("-------------------")


def checkMold():
    for row in board:
        for isMelt in row:
            if isMelt == 1:
                return True
    return False


def findExternal():
    answer = [[0 for _ in range(m)] for _ in range(n)]
    q = deque()

    for i in range(n):
        for j in range(0, m, m - 1):
            q.append([i, j])
            answer[i][j] = 1

            while q:
                r, c = q.popleft()

                for k in range(4):
                    nxt_r = r + dr[k]
                    nxt_c = c + dc[k]

                    if nxt_r < 0 or nxt_r >= n or nxt_c < 0 or nxt_c >= m or answer[nxt_r][nxt_c] == 1 or board[nxt_r][
                        nxt_c] == 1:
                        continue

                    q.append([nxt_r, nxt_c])
                    answer[nxt_r][nxt_c] = 1

    return answer


dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]
n, m = map(int, sys.stdin.readline().split())  # n : 로우 개수 y, m : 컬럼 개수 x -> board[n][m]

board = []

for _ in range(n):
    board.append(list(map(int, sys.stdin.readline().split())))

cnt = 0

while checkMold():
    external = findExternal()
    for i in range(n):
        for j in range(m):
            if external[i][j] or board[i][j] == 0:
                continue

            ex_cnt = 0
            for k in range(4):
                nxt_r = i + dr[k]
                nxt_c = j + dc[k]

                if nxt_r < 0 or nxt_r >= n or nxt_c < 0 or nxt_c >= m or external[nxt_r][nxt_c] == 0:
                    continue

                ex_cnt += 1

            if ex_cnt >= 2:
                board[i][j] = 0
    cnt += 1

print(cnt)
