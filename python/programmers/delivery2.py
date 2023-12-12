def solution(cap, n, deliveries, pickups):
    answer = 0
    d_dist = getDistance(cap, n, deliveries)
    p_dist = getDistance(cap, n, pickups)

    print(d_dist, p_dist)
    return answer


def getDistance(cap, n, deliveries):
    dist = 0
    idx = n - 1

    while idx > -1:
        d_cap = cap
        dist += idx + 1

        while idx > -1:
            if deliveries[idx] > d_cap:
                reps = deliveries[idx] // d_cap
                deliveries[idx] -= d_cap * reps
                dist += (idx + 1) * (reps - 1)
                if deliveries[idx] != 0:
                    break
            else:
                d_cap -= deliveries[idx]
                deliveries[idx] = 0
                while idx > -1 and deliveries[idx] == 0:
                    idx -= 1

    return dist


if __name__ == '__main__':
    print(solution(4, 5, [1000000000, 0, 3, 1, 2], [1000000000, 3, 0, 4, 0]))
