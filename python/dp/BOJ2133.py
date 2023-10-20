import sys

# dp[i][j] : i 열에서 시작하는 가로 타일 개수 별 경우의 수


def sol():
    n = int(sys.stdin.readline())
    dp = [[0 for _ in range(0, 4)] for _ in range(0, n + 1)]

    dp[1][1] = 2
    dp[1][3] = 1

    for i in range(2, len(dp)):
        dp[i][0] = dp[i-1][3] + dp[i-1][1]
        dp[i][1] = dp[i-1][0]*2 + dp[i-1][2]
        dp[i][2] = dp[i-1][1]
        dp[i][3] = dp[i-1][0]

    print(dp[n-1][3] + dp[n-1][1])


if __name__ == '__main__':
    sol()