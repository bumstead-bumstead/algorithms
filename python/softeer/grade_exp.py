import sys
import math

n, k = map(int, sys.stdin.readline().split())

s = [0] + list(map(int, sys.stdin.readline().split()))

sum_arr = [0 for _ in range(n + 1)]

for i in range(1, len(sum_arr)):
  sum_arr[i] = sum_arr[i-1] + s[i]

for _ in range(k):
  s, e = map(int, sys.stdin.readline().split())
  print("{:.2f}".format((sum_arr[e] - sum_arr[s-1]) / (e - s + 1)))