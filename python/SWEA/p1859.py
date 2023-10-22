from heapq import *

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    arr = list(map(int, input().split()))
    stack = []
    tmp_max = arr[len(arr) - 1]
    answer = 0

    for i in range(len(arr) - 2, -1, -1):
        if tmp_max > arr[i]:
            answer += tmp_max - arr[i]
        else:
            tmp_max = arr[i]

    print("#%d %d" % (test_case, answer))
