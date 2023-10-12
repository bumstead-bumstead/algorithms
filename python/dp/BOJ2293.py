import sys


def sol():
    n, k = map(int, sys.stdin.readline().split(" "))
    coins = []
    dp = [0 for _ in range(0, k+1)]

    for _ in range(0, n):
        coins.append(int(sys.stdin.readline()))

    coins.sort()
    dp[0] = 1

    for i in range(1, n+1):
        tmp = coins[i-1]

        for j in range(tmp, k+1):
            dp[j] += dp[j - tmp]
    print(dp[k])


if __name__ == '__main__':
    sol()