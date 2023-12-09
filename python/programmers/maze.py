import sys


limit_number = 2550
sys.setrecursionlimit(limit_number)

answer = "z"
dr = [1, 0, 0, -1]
dc = [0, -1, 1, 0]
route_characters = ["d", "l", "r", "u"]
n = -1
m = -1
k = -1
r = -1
c = -1

def solution(n1, m1, x, y, r1, c1, k1):
    global n
    global m
    global k
    global r
    global c
    r = r1
    c = c1
    k = k1
    n = n1
    m = m1
    d = abs(r - x) + abs(c - y)
    if d > k:
        return 'impossible'
    find(0, x, y, "")
    if answer == "z":
        return "impossible"
    return answer


def find(depth, tmp_r, tmp_c, route):
    global answer

    if answer != "z":
        return

    d = abs(r - tmp_r) + abs(c - tmp_c)
    left = k - depth
    if d > k - depth:
        return
    if (left - d) % 2 != 0:
        return

    if depth == k:
        if r == tmp_r and c == tmp_c:
            # print(tmp_r, tmp_c)
            # print(route)
            answer = route
        return

    for i in range(4):
        nxt_r = tmp_r + dr[i]
        nxt_c = tmp_c + dc[i]

        if nxt_r < 1 or nxt_r > n or nxt_c < 1 or nxt_c > m:
            continue

        find(depth + 1, nxt_r, nxt_c, route + route_characters[i])


if __name__ == '__main__':
    print(solution(3,4,2,3,3,1,5))
