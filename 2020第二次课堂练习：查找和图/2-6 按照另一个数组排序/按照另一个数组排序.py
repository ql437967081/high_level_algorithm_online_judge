def solve():
    _, __ = tuple(map(int, input().strip().split(' ')))
    a1 = list(map(int, input().strip().split(' ')))
    a2 = list(map(int, input().strip().split(' ')))
    cnt_map = {}
    a3 = []
    for x in a2:
        cnt_map[x] = 0
    for x in a1:
        if x in cnt_map:
            cnt_map[x] += 1
        else:
            a3.append(x)
    a4 = []
    for x in a2:
        cnt = cnt_map[x]
        if cnt > 0:
            a4.extend([x] * cnt)
    a4.extend(sorted(a3))
    print(' '.join(map(str, a4)))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
