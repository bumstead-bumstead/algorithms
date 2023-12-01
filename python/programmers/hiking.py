from heapq import *


def solution(n, paths, gates, summits):
    arr = [[] for _ in range(n + 1)]
    min_intensity = 10000001
    min_summit = 50001
    gates = set(gates)

    for i, j, w in paths:
        arr[i].append((w, j))
        arr[j].append((w, j))

    for gate in gates:
        d = [-1 for _ in range(n + 1)]
        d[gate] = 0
        q = []
        heappush(q, (0, gate))  # (비용, 정점)

        while q:
            cost, node = heappop(q)

            for nxt_cost, nxt_node in arr[node]:
                if nxt_node in gates:
                    continue

                tmp_max = max(cost, nxt_cost)
                if d[nxt_node] < tmp_max:
                    d[nxt_node] = tmp_max
                    heappush(q, (tmp_max, nxt_node))

        for summit in summits:
            if d[summit] == min_intensity:
                min_summit = min(min_summit, summit)
            elif d[summit] < min_intensity:
                min_intensity = d[summit]
                min_summit = summit
        print(gate, d)
    return min_summit, min_intensity


if __name__ == '__main__':
    print(solution(6, [[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]], [1, 3],
                   [5]))
