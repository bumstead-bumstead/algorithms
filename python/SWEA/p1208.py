from heapq import *

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, 11):
    n = int(input())
    arr = list(map(int, input().split()))
    arr_d = [(-1) * a for a in arr]
    heapify(arr)
    heapify(arr_d)
    answer = 0

    for _ in range(n):
        tmpMax = heappop(arr_d) * (-1)
        tmpMin = heappop(arr)
        heappush(arr, tmpMin + 1)
        heappush(arr_d, (tmpMax - 1) * (-1))
        answer = tmpMax - tmpMin
        if answer < 2:
            break
    tmpMax = heappop(arr_d) * (-1)
    tmpMin = heappop(arr)
    print("#%d %d" % (test_case, tmpMax - tmpMin))
