import sys


def sol():
    N, K = map(int, sys.stdin.readline().split())
    answer = 1
    d = 1
    for i in range(N, K, -1):
        answer *= i
        answer %= 1000000007
        d *= (N - i + 1)
        d %= 1000000007

    sys.stdout.write(str(int(answer / d)))
    sys.stdout.close()

if __name__ == '__main__':
    sol()
