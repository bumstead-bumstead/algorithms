import sys

dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]


def sol():
    """
    dp[j][k] = i열까지 최대 점수
    k = 0 : 0행을 선택한 경우
    k = 1 : 1행을 선택한 경우
    k = 2 : 해당 열 모두 선택하지 않은 경우
    """

    t = int(sys.stdin.readline())
    for _ in range(0, t):
        n = int(sys.stdin.readline())
        board = []
        dp = [[0 for _ in range(0, 3)] for _ in range(0, n)]

        for i in range(0, 2):
            board.append(list(map(int, sys.stdin.readline().split(" "))))
        dp[0][0] = board[0][0]
        dp[0][1] = board[1][0]
        dp[0][2] = 0

        for j in range(1, n):  # col
            dp[j][0] = max(dp[j - 1][1], dp[j - 1][2]) + board[0][j]
            dp[j][1] = max(dp[j - 1][0], dp[j - 1][2]) + board[1][j]
            dp[j][2] = max(dp[j - 1][0], dp[j - 1][1], dp[j - 1][2])

        sys.stdout.write(str(max(dp[n-1])))
        sys.stdout.write("\n")
    sys.stdout.close()


if __name__ == '__main__':
    sol()