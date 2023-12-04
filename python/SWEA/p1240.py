def findLastColumnIndex(row):
    for i in range(len(row) - 1, 0, -1):
        if row[i] == 1:
            return i

T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
    board = []
    for _ in range(n):
        tmp = list(map(int, list(input())))
        if max(tmp) == 0:
            continue
        board.append(tmp)
    row = board[0]
    lastIdx = findLastColumnIndex(row)
    code = row[lastIdx - 55 : lastIdx + 1]

    decoded = ""
    for i in range(0, len(code), 7):
        tmp = "".join(str(a) for a in code[i:i+7])
        if tmp == "0001101":
            decoded += "0"
        elif tmp == "0011001":
            decoded += "1"
        elif tmp == "0010011":
            decoded += "2"
        elif tmp == "0111101":
            decoded += "3"
        elif tmp == "0100011":
            decoded += "4"
        elif tmp == "0110001":
            decoded += "5"
        elif tmp == "0101111":
            decoded += "6"
        elif tmp == "0111011":
            decoded += "7"
        elif tmp == "0110111":
            decoded += "8"
        elif tmp == "0001011":
            decoded += "9"

    arr = list(map(int, list(decoded)))

    if ((arr[6] + arr[4] + arr[2] + arr[0])*3 + (arr[7] + arr[5] + arr[3] + arr[1])) % 10 == 0:
        print("#%d %d" % (test_case, sum(arr)))
    print("#%d %d" % (test_case, 0))
