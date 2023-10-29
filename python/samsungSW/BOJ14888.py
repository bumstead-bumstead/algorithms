n = int(input())
arr = list(map(int, input().split()))
ops = list(map(int, input().split()))
mx = -1000000001
mn = 1000000001


def dfs(sum, depth):
    global mx
    global mn

    if depth == n:
        mx = max(mx, sum)
        mn = min(mn, sum)
        return

    if ops[0] > 0:
        ops[0] -= 1
        dfs(sum + arr[depth], depth + 1)
        ops[0] += 1
    if ops[1] > 0:
        ops[1] -= 1
        dfs(sum - arr[depth], depth + 1)
        ops[1] += 1
    if ops[2] > 0:
        ops[2] -= 1
        dfs(sum * arr[depth], depth + 1)
        ops[2] += 1
    if ops[3] > 0:
        ops[3] -= 1
        if sum < 0:
            dfs(((sum * (-1)) // arr[depth]) * (-1), depth + 1)
        else :
            dfs(sum // arr[depth], depth + 1)
        ops[3] += 1


dfs(arr[0], 1)

print(mx)
print(mn)
