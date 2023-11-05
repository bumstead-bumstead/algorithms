import sys

n = int(sys.stdin.readline())
lectures = []

for _ in range(n):
  s, f = map(int, sys.stdin.readline().split())
  lectures.append([f, s])

lectures.sort()

tmp_max_f = -1
answer = 0

for f, s in lectures:
  if s >= tmp_max_f:
    answer += 1
    tmp_max_f = f

print(answer)
