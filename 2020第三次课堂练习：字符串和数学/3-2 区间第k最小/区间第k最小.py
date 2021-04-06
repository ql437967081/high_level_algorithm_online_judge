def get_input():
    a = list(map(int, input().strip().split(' ')))
    l, r = map(int, input().strip().split(' '))
    a = a[l - 1:r]
    k = int(input().strip())
    return a, k


def find_kth(ipt):
    a, k = ipt

    def qs(l, r):
        x = a[l]
        i, j = l, r
        while i < j:
            while i < j and x <= a[j]:
                j -= 1
            a[i] = a[j]
            while i < j and x >= a[i]:
                i += 1
            a[j] = a[i]
        a[i] = x
        if k == i + 1:
            return x
        if k < i + 1:
            return qs(l, i - 1)
        return qs(i + 1, r)

    return qs(0, len(a) - 1)


def solve():
    print(find_kth(get_input()))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
