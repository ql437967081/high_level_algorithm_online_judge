def solve():
    arr = list(map(int, input().strip().split(' ')))
    arr = remove_repeat(arr)

    lis_ends_with = []
    for x in arr:
        longest_increasing_subsequence(lis_ends_with, x)

    lds_starts_with = []
    for x in arr[::-1]:
        longest_increasing_subsequence(lds_starts_with, x)
    lds_starts_with = [
        [tup[::-1] for tup in lds_starts_with_ai]
        for lds_starts_with_ai in lds_starts_with[::-1]
    ]

    increasing_decreasing_subsequence = [()]
    for i in range(len(arr)):
        lis_ends_with_ai = lis_ends_with[i]
        lds_starts_with_ai = lds_starts_with[i]
        new_len = len(lis_ends_with_ai[0]) + len(lds_starts_with_ai[0]) - 1
        old_len = len(increasing_decreasing_subsequence[0])
        if new_len >= old_len:
            added_ids = [
                lis + lds[1:]
                for lis in lis_ends_with_ai
                for lds in lds_starts_with_ai
            ]
            if new_len > old_len:
                increasing_decreasing_subsequence.clear()
            increasing_decreasing_subsequence += added_ids

    for ids in sorted(list(map(tuple_to_str, increasing_decreasing_subsequence))):
        print(ids)


def remove_repeat(arr):
    removed = []
    for i in range(len(arr)):
        if i == 0 or arr[i] != arr[i - 1]:
            removed.append(arr[i])
    return removed


def longest_increasing_subsequence(lis_ends_with, x):
    lis_ends_with_x = [(x,)]
    for lis_ends_with_ai in lis_ends_with:
        first_lis = lis_ends_with_ai[0]
        last = first_lis[-1]
        if last < x:
            new_len = len(first_lis) + 1
            old_len = len(lis_ends_with_x[0])
            if new_len >= old_len:
                added_lis = [pre_lis + (x,) for pre_lis in lis_ends_with_ai]
                if new_len > old_len:
                    lis_ends_with_x.clear()
                lis_ends_with_x += added_lis
    lis_ends_with.append(lis_ends_with_x)


def tuple_to_str(tup):
    return ' '.join(map(str, tup))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
