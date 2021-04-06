def solve():
    arr = list(map(int, input().strip().split(' ')))
    sum_of_2 = int(input().strip())
    arr = sorted(arr)
    n = len(arr)
    cnt = 0
    for i in range(n - 1):
        left, right = i + 1, n - 1
        to_search = sum_of_2 - arr[i]
        if to_search < arr[left] or to_search > arr[right]:
            continue
        while left <= right:
            mid = (left + right) >> 1
            if arr[mid] == to_search:
                left_mid, right_mid = mid - 1, mid + 1
                while left_mid > i and arr[left_mid] == to_search:
                    left_mid -= 1
                while right_mid < n and arr[right_mid] == to_search:
                    right_mid += 1
                cnt += right_mid - left_mid - 1
                break
            if to_search < arr[mid]:
                right = mid - 1
            else:
                left = mid + 1
    print(cnt)


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
