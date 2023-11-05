import sys

n = int(sys.stdin.readline())
a = []
b = []
a_move = [] #ai to bi+1 거리
b_move = [] #bi to ai+1 거리
for _ in range(n - 1):
  at, bt, atob, btoa = list(map(int, sys.stdin.readline().split()))
  a.append(at)
  b.append(bt)
  a_move.append(atob)
  b_move.append(btoa)

an, bn = map(int, sys.stdin.readline().split())

a.append(an)
b.append(bn)

dp = [[10000000000, 10000000000] for _ in range(n)] # dp[i][j] : i번째 작업장까지의 최소 시간
dp[0][0] = a[0]
dp[0][1] = b[0]

for i in range(n-1):
  dp[i+1][0] = min(dp[i][0] + a[i+1], dp[i][1] + b_move[i] + a[i+1])
  dp[i+1][1] = min(dp[i][1] + b[i+1], dp[i][0] + a_move[i] + b[i+1])

print(min(dp[n-1][0], dp[n-1][1]))