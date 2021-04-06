import heapq


def merge_k_arrays(arr):
    res = []

    heap = []
    for ar in arr:
        heapq.heappush(heap, (ar[0], ar, 0))

    while heap:
        x, ar, i = heapq.heappop(heap)
        res.append(x)
        i += 1
        if i < len(ar):
            heapq.heappush(heap, (ar[i], ar, i))
    return res


def get_input():
    n = int(input().strip())
    data = list(map(int, input().strip().split(' ')))
    arr = []
    for i in range(n):
        arr.append(data[i * n: (i + 1) * n])
    return arr


def solve():
    merged_arr = merge_k_arrays(get_input())
    print(' '.join(map(str, merged_arr)))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
