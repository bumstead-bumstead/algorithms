import sys
from heapq import heappush, heappop


def d(board):
    distance = [sys.maxsize for _ in range(0, len(board))]
    q = []
    heappush(q, [0, 1])  # (비용, 다음 정점)
    distance[1] = 0

    while q:
        cost, node = heappop(q)

        for a in board[node]:
            nxt_cost, nxt_node = a

            if cost + nxt_cost < distance[nxt_node]:
                distance[nxt_node] = cost + nxt_cost
                heappush(q, [cost + nxt_cost, nxt_node])

    print(distance)


if __name__ == '__main__':
    board = [
        [],
        [[3, 2], [2, 3], [5, 4]],
        [[2, 3], [8, 5]],
        [[2, 4]],
        [[6, 5]],
        [[1, 6]],
        []
    ]

    d(board)