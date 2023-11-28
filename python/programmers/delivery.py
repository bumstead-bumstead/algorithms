def solution(cap, n, deliveries, pickups):
    d_idx = n - 1
    p_idx = n - 1

    answer = 0

    while d_idx > -1 or p_idx > -1:
        answer += (max(d_idx, p_idx) + 1) * 2
        d_cap = cap
        p_cap = cap

        while d_idx > -1:
            if deliveries[d_idx] > d_cap:
                reps = deliveries[d_idx] // d_cap
                deliveries[d_idx] -= d_cap
                break
            else:
                d_cap -= deliveries[d_idx]
                deliveries[d_idx] = 0
                d_idx -= 1

        while p_idx > -1:
            if pickups[p_idx] > p_cap:
                pickups[p_idx] -= p_cap
                break
            else:
                pickups[p_idx] = 0
                p_cap -= pickups[p_idx]
                p_idx -= 1

    return answer


if __name__ == '__main__':
    print(solution(4, 5, [1000000000, 0, 3, 1, 2], [1000000000, 3, 0, 4, 0]))
