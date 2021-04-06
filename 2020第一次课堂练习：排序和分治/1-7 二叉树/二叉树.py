def traverse_by_level_order():
    n = int(input().strip())
    nodes = list(map(int, input().strip().split(' ')))
    index = 0
    level_index = 0
    while index < n:
        level_num = 1 << level_index
        level_index += 1
        s = set()
        while index < n and level_num > 0:
            s.add(nodes[index])
            index += 1
            level_num -= 1
        print(' '.join(map(str, sorted(s))))


def solve():
    traverse_by_level_order()


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
