T = int(input())
for test_case in range(1, T + 1):
    n, swap = map(int, input().split())
    arr = [int(a) for a in n]

    for _ in range(len(swap)):
        s1 = -1
        s2 = -1
        inc = -1000001

        for i in range(len(arr)):
            for j in range(i + 1, len(arr)):
                d = arr[i] - arr[j]
                tmp = d * (10 ** (len(arr) - j)) - d * (10 ** (len(arr) - i))
                if tmp > inc:
                    s1 = i
                    s2 = j
                    inc = tmp

        arr[s1], arr[s2] = arr[s2], arr[s1]

    print(arr)
