def solve():
    data = list(map(int, input().strip().split(' ')))
    gaps = list(map(int, input().strip().split(' ')))
    n = len(data)
    for gap in gaps:
        for start in range(gap):
            i = start + gap
            while i < n:
                j = i - gap
                while j >= start:
                    if data[j] > data[j + gap]:
                        tmp = data[j]
                        data[j] = data[j + gap]
                        data[j + gap] = tmp
                    else:
                        break
                    j -= gap
                i += gap
    print(' '.join(map(str, data)))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
