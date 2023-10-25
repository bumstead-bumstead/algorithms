p1 = [[1], [1], [1], [1]]
p2 = [
    [1, 1],
    [1, 1]
]
p3 = [
    [1, 0],
    [1, 0],
    [1, 1]
]
p4 = [
    [0, 1, 0],
    [1, 1, 1]
]
p5 = [
    [1, 0],
    [1, 1],
    [0, 1]
]

tetra = [p1, p2, p3, p4, p5]

def show(p):
    print("----------")
    for a in p:
        print(*a)
    print("----------")


def rotate(t):
    row = len(t)
    col = len(t[0])
    r = [[0 for _ in range(row)] for _ in range(col)]

    for i in range(len(t)):
        for j in range(len(t[0])):
            r[j][row - i - 1] = t[i][j]

    return r


def flip(t):
    r = [[0 for _ in range(len(t[0]))] for _ in range(len(t))]
    for i in range(len(t)):
        for j in range(len(t[0])):
            r[len(t) - i - 1][j] = t[i][j]

    return r

# 한 형태의 테트로미노에 대해서 최대 점수를 계산
def getScore(p):
    result = 0
    for i in range(len(board)):
        for j in range(len(board[i])):
            result = max(result, gettmpsum(p, i, j))

    return result


# 특정한 위치에 테트로미노를 위치시켰을 때의 점수를 계산
def gettmpsum(p, row, col):
    result = 0

    for i in range(len(p)):
        if i + row >= len(board):
            return -1
        for j in range(len(p[i])):
            if j + col >= len(board[0]):
                return -1
            if p[i][j]:
                result += board[row + i][col + j]

    return result


n, m = map(int, input().split())
board = []
answer = 0

for i in range(n):
    board.append(list(map(int, input().split())))

for p in tetra:
    for _ in range(4):
        answer = max(answer, getScore(p))
        p = rotate(p)
    p = flip(p)
    for _ in range(4):
        answer = max(answer, getScore(p))
        p = rotate(p)

print(answer)
