def solution(init_alp, init_cop, problems):
    alp_req = init_alp
    cop_req = init_cop

    for alp, cop, alp_rwd, cop_rwd, cost in problems:
        alp_req = max(alp_req, alp)
        cop_req = max(cop_req, cop)

    dp = [[2250001 for _ in range(cop_req + 1)] for _ in range(alp_req + 1)]

    for i in range(0, init_alp + 1):
        for j in range(0, init_cop + 1):
            dp[i][j] = 0

    for i in range(init_alp, alp_req + 1):
        for j in range(init_cop, cop_req + 1):
            nxt_r = min(alp_req, i + 1)
            nxt_c = min(cop_req, j + 1)
            dp[nxt_r][j] = min(dp[nxt_r][j], dp[i][j] + 1)
            dp[i][nxt_c] = min(dp[i][nxt_c], dp[i][j] + 1)

            for alp, cop, alp_rwd, cop_rwd, cost in problems:
                nxt_r = min(alp_req, i + alp_rwd)
                nxt_c = min(cop_req, j + cop_rwd)
                if alp > i or cop > j:
                    continue
                dp[nxt_r][nxt_c] = min(dp[nxt_r][nxt_c], dp[i][j] + cost)

    return dp[alp_req][cop_req]
