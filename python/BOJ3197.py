'''
아이디어 : iteration마다 모든 2차원 배열에 대한 BFS를 돌리면 시간 초과된다. O(1500^3)
 -> 백조가 이미 움직인 자리, 얼음이 이미 녹거나 검사를 마친 자리는 다시 탐색하지 않는다.
 이를 위해서, 다음 iteration에 움직일 (또는 녹일) position 정보를 queue에 유지한다.
'''

import copy
import sys
from collections import deque

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]
meltQueue = deque()
birdQueue = deque()
birdVisited = []
meltVisited = []

def isReachable(board, birdPosition):
    global birdVisited
    queue = deque()

    if len(birdVisited) == 0:
        birdVisited = [[False for _ in range(0,len(board[0]))] for _ in range(0, len(board))]
        queue.append(birdPosition)
        birdVisited[birdPosition[0]][birdPosition[1]] = True
    else:
        queue = copy.deepcopy(birdQueue)
        birdQueue.clear()

    while len(queue) > 0:
        tmp = queue.popleft()
        for i in range(0, 4):
            r = tmp[0] + dr[i]
            c = tmp[1] + dc[i]

            if r < 0 or r >= len(board) or c < 0 or c >= len(board[0]) or birdVisited[r][c]:
                continue

            birdVisited[r][c] = True

            if board[r][c] == 'X':
                birdQueue.append([r, c])
                continue

            if board[r][c] == 'L':
                return True

            queue.append([r, c])

    return False


def melt(board):
    global meltVisited
    l = len(meltQueue)

    for _ in range(0, l):
        position = meltQueue.popleft()
        i = position[0]
        j = position[1]

        # if meltVisited[i][j]:
        #     continue

        for k in range(0, 4):
            r = i + dr[k]
            c = j + dc[k]

            if (r < 0 or r >= len(board) or c < 0
                    or c >= len(board[0])
                    or board[r][c] == '.' or board[r][c] == 'L'
                    or meltVisited[r][c]):
                continue

            board[r][c] = '.'
            meltVisited[r][c] = True
            meltQueue.append([r,c])
        meltVisited[i][j] = True

def initializeMeltQueue(board):
    global meltVisited
    meltVisited = [[False for _ in range(0, len(board[0]))] for _ in range(0, len(board))]

    for i in range(0, len(board)):
        for j in range(0, len(board[0])):
            if (meltVisited[i][j] or board[i][j] == 'X'): continue

            for k in range(0, 4):
                r = i + dr[k]
                c = j + dc[k]

                if (r < 0 or r >= len(board) or c < 0
                        or c >= len(board[0])
                        or board[r][c] == '.' or board[r][c] == 'L'
                        or meltVisited[r][c]):
                    continue

                board[r][c] = '.'
                meltVisited[r][c] = True
                meltQueue.append([r, c])

def sol():
    global birdPosition
    R, C = map(int, sys.stdin.readline().split())

    board = [[] for _ in range(0, R)]
    count = 0

    for i in range(0, R):
        board[i] = list(sys.stdin.readline())[0:C]

        for j in range(0, len(board[i])) :
            if (board[i][j] == 'L') :
                birdPosition = [i, j]

    while True:
        if isReachable(board, birdPosition):
            return count

        count += 1
        if count == 1: initializeMeltQueue(board)
        else: melt(board)

if __name__ == '__main__':
    print(sol())