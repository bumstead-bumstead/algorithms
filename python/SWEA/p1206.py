
for test_case in range(1, 11):
    n = int(input())
    buildings = list(map(int, input().split()))
    answer = 0

    for i in range(2, len(buildings) - 2):
        m = 0
        for k in range(-2, 3):
            if k == 0:
                continue
            m = max(m, buildings[k + i])
        answer += max(buildings[i] - m, 0)
    print("#%d %d" % (test_case, answer))

