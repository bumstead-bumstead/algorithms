import sys

def sol() :
    N, K = map(int, sys.stdin.readline().split())
    p = []

    for i in range(0, N):
        p.append(list(map(int, sys.stdin.readline().split())))  # w, v 순서

    dp = [[0 for i in range(K + 1)] for i in range(N + 1)]

    for i in range(0, N):
        tmpProduct = p[i]
        for j in range(0, K + 1):
            dp[i + 1][j] = max(dp[i + 1][j], dp[i][j])
            if (tmpProduct[0] <= j): dp[i + 1][j - tmpProduct[0]] = max(dp[i + 1][j - tmpProduct[0]],
                                                                        dp[i][j] + tmpProduct[1])
    return max(dp[N])

print(sol())
'''
benefit[i][j] : i번 물건까지 넣었다 뺐다 반복했을 때, 가방 무게 j일 때 최대 이익
i : 물건 인덱스
j : 남은 무게

1. 첫 번째 물건부터 (p[0]) 순서대로 검사 시작. , 
2. 모든 j에 대해서 p[0]이 j보다 작으면 continue
3. 그렇지 않은 경우,
3-1. 해당 물건을 넣는 경우 처리 : b[1][j-w] = max(b[0][j], b[1][j-w])
3-2. 해당 물건을 넣지 않는 경우 처리 : b[1][j] = max(b[0][j], b[1][j])
'''