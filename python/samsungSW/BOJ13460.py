n, m = map(int, input().split())

board = []
r_pos = []
b_pos = []
h_pos = []
answer = -1
dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]

def show():
    print('----------------------------')
    for a in board:
        print(*a)
    print('----------------------------')


def dfs(red_r, red_c, blue_r, blue_c, dir, depth):
    global answer
    tmp_b = [red_r, red_c]
    tmp_r = [blue_r, blue_c]
    if depth > 10:
        return

    if depth != 0:
        if (dir == 0 and red_r > blue_r) or (dir == 1 and red_r < blue_r) or (
                dir == 2 and red_c > blue_c) or (dir == 3 and red_c < blue_c):
            tmp_r = move(red_r, red_c, dir)
            tmp_b = move(blue_r, blue_c, dir)
        else:
            tmp_b = move(blue_r, blue_c, dir)
            tmp_r = move(red_r, red_c, dir)


    if tmp_b == h_pos:
        return
    if tmp_r == h_pos:
        if answer == -1:
            answer = depth
        else:
            answer = min(answer, depth)
        return

    for i in range(4):
        board[tmp_r[0]][tmp_r[1]], board[red_r][red_c] = board[red_r][red_c], board[tmp_r[0]][tmp_r[1]]
        dfs(tmp_r[0], tmp_r[1], tmp_b[0], tmp_b[1], i, depth + 1)
        board[tmp_r[0]][tmp_r[1]], board[red_r][red_c] = board[red_r][red_c], board[tmp_r[0]][tmp_r[1]]


def move(row, col, dir):
    nxt_row = row
    nxt_col = col
    while True:
        if board[nxt_row + dr[dir]][nxt_col + dc[dir]] == 'O':
            return [nxt_row + dr[dir], nxt_col + dc[dir]]
        if board[nxt_row + dr[dir]][nxt_col + dc[dir]] != '.':
            break
        nxt_row += dr[dir]
        nxt_col += dc[dir]

    return [nxt_row, nxt_col]


for i in range(n):
    board.append(list(input()))

    for j in range(m):
        node = board[i][j]

        if node == 'B':
            b_pos = [i, j]
        if node == 'R':
            r_pos = [i, j]
        if node == 'O':
            h_pos = [i, j]

print(h_pos)

show()
dfs(r_pos[0], r_pos[1], b_pos[0], b_pos[1], 1, 0)
print(answer)