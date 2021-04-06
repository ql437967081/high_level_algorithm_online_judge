def decimal_to_str(x):
    return '%.2f' % x


def solve():
    n = int(input().strip())
    m = list(map(int, input().strip().split(' ')))
    points = []
    for i in range(n - 1):
        left = m[i]
        right = m[i + 1]
        while left < right:
            mid = (left + right) / 2
            if 1e-5 > right - left:
                points.append(mid)
                break
            force = 0
            for j in range(n):
                force += 1 / (mid - m[j])
            if force < 0:
                right = mid
            else:
                left = mid
    print(' '.join(map(decimal_to_str, points)))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
