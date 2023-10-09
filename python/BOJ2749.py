import sys

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]
board = []
visited = []
man = []
open_cnt = 10000
sys.setrecursionlimit(15000)


def sol():
    global board
    global visited
    global man
    global open_cnt

    t = int(sys.stdin.readline())

    for _ in range(0, t):
        board = []
        man = []
        r, c = map(int, sys.stdin.readline().split())
        for i in range(0, r):

            board.append(list(sys.stdin.readline()))

            for j in range(0, c):
                if board[i][j] == '$':
                    man.append([i, j])

        visited = [[[False for _ in range(0, len(board[0]))] for _ in range(0, len(board))] for _ in range(0, 2)]
        open_cnt = 10000

        dfs(man[0], 0, 0)
        sys.stdout.write(str(open_cnt))
        sys.stdout.write("\n")

    sys.stdout.close()


def dfs(pos, idx, cnt):
    global open_cnt

    for i in range(0, 4):
        nxt = [pos[0] + dr[i], pos[1] + dc[i]]

        if (nxt[0] < 0 or nxt[1] < 0
                or nxt[0] >= len(board) or nxt[1] >= len(board[0])
                or visited[idx][nxt[0]][nxt[1]]
                or board[nxt[0]][nxt[1]] == '*'):
            continue

        if (nxt[0] == 0 or nxt[1] == 0
                or nxt[0] == len(board) - 1 or nxt[1] == len(board[0]) - 1):
            if idx == 1:
                cnt = cnt + 1 if board[nxt[0]][nxt[1]] == '#' else cnt
                # if open_cnt > cnt:
                #     print("------------------------")
                #     show()
                #     print("open_cnt = ", cnt)
                #     print("endpoint = ", nxt)
                #     print("------------------------")

                open_cnt = min(cnt, open_cnt)
                return

            if board[nxt[0]][nxt[1]] == '#':
                board[nxt[0]][nxt[1]] = '.'
                dfs(man[1], 1, cnt + 1)
                board[nxt[0]][nxt[1]] = '#'
            else:
                dfs(man[1], 1, cnt)

        visited[idx][nxt[0]][nxt[1]] = True

        if board[nxt[0]][nxt[1]] == '#':
            board[nxt[0]][nxt[1]] = '.'
            dfs(nxt, idx, cnt + 1)
            board[nxt[0]][nxt[1]] = '#'
        else:
            dfs(nxt, idx, cnt)
        visited[idx][nxt[0]][nxt[1]] = False


def show():
    for row in board:
        print(*row, sep="")


if __name__ == '__main__':
    sol()
    # a = [[5,4,3,2,1], [5,4,3,2,1]]
    # for b in a:
    #     print(*b, sep="")
