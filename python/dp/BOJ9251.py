import sys

"""
두 문자열 s1, s2에 대해서 
LCS[i][j] = s1[0:i], s2[0:j]의 LCS
"""

def sol():
    s1 = sys.stdin.readline()[:-1]
    s2 = sys.stdin.readline()[:-1]

    if len(s1) == 0 or len(s2) == 0:
        print(0)
        return

    dp = [[0 for _ in range(0, len(s2) + 1)] for _ in range(0, len(s1) + 1)]

    for i in range(1, len(s1) + 1):
        for j in range(1, len(s2) + 1):
            print(i, j)
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:  # 2, 1 : 1, 1 : 1, 0
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    print(dp[len(s1)][len(s2)])


sol()