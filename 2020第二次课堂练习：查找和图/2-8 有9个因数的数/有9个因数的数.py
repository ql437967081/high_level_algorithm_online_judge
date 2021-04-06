from math import sqrt


def solve():
    global max_x, vis, primes, x

    n = int(input().strip())
    sqrt_n = sqrt(n)

    max_y = sqrt_n / 2
    while x < max_y:
        x += 1
        if not vis[x]:
            primes.append(x)
            for y in range(x, max_x, x):
                vis[y] = True

    cnt = len(primes)
    res = 0

    # (a * b)**2
    for i in range(cnt):
        left, right = i + 1, cnt - 1
        pos = i
        while left <= right:
            mid = (left + right) >> 1
            if primes[i] * primes[mid] <= sqrt_n:
                pos = mid
                left = mid + 1
            else:
                right = mid - 1
        if pos == i:
            break
        res += pos - i

    # a**8
    left, right = 0, cnt - 1
    pos = -1
    sqrt8_n = sqrt(sqrt(sqrt_n))
    while left <= right:
        mid = (left + right) >> 1
        if primes[mid] <= sqrt8_n:
            pos = mid
            left = mid + 1
        else:
            right = mid - 1

    print(res + pos + 1)


if __name__ == '__main__':
    max_x = 500001
    vis = [False] * max_x
    primes = []
    x = 1
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
