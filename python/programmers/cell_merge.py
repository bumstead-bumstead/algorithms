board = [[-1 for _ in range(51)] for _ in range(51)]
link = [[{(i, j)} for j in range(51)] for i in range(51)]


def update_cell(r, c, value):
    for i, j in link[r][c]:
        board[i][j] = value


def update_value(value, update):
    for i in range(51):
        for j in range(51):
            if board[i][j] == value:
                board[i][j] = update


def merge(r1, c1, r2, c2):
    if r1 == r2 and c1 == c2:
        return

    for tuple in link[r2][c2]:
        link[r1][c1].add(tuple)
    for r, c in link[r1][c1]:
        link[r][c] = link[r1][c1]

    if board[r1][c1] != -1:
        for r, c in link[r1][c1]:
            board[r][c] = board[r1][c1]
    elif board[r2][c2] != -1:
        for r, c in link[r2][c2]:
            board[r][c] = board[r2][c2]


def unmerge(r, c):
    tmp = board[r][c]
    for r1, c1 in link[r][c]:
        link[r1][c1] = {(r1, c1)}
        board[r1][c1] = -1
    board[r][c] = tmp


def add_result(answer, r, c):
    if board[r][c] == -1:
        answer.append("EMPTY")
        return

    answer.append(board[r][c])


def solution(commands):
    global board
    global link
    answer = []

    for command in commands:
        command_arr = command.split()
        if command_arr[0] == "UPDATE" and len(command_arr) == 4:
            update_cell(int(command_arr[1]), int(command_arr[2]), command_arr[3])
        elif command_arr[0] == "UPDATE":
            update_value(command_arr[1], command_arr[2])
        elif command_arr[0] == "MERGE":
            merge(int(command_arr[1]), int(command_arr[2]), int(command_arr[3]), int(command_arr[4]))
        elif command_arr[0] == "UNMERGE":
            unmerge(int(command_arr[1]), int(command_arr[2]))
        else:
            add_result(answer, int(command_arr[1]), int(command_arr[2]))

    return answer

if __name__ == '__main__':
    print(solution(["UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"]))

