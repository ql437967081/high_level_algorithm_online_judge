def get_root(y, fa):
    def gr(x):
        if x == fa[x]:
            return x
        fa[x] = gr(fa[x])
        return fa[x]

    return gr(y)


def is_common_root(fa):
    root = set()
    for x in fa:
        root.add(get_root(x, fa))
        if len(root) > 1:
            return False
    return True


def solve():
    n = int(input().strip())
    l_set = set()
    r_set = set()
    father = {}
    for s in input().strip().split(' '):
        left = s[0]
        right = s[-1]
        l_set.add(left)
        if right in l_set:
            l_set.remove(right)
        else:
            r_set.add(right)
        if left not in father:
            father[left] = left
        if right not in father:
            father[right] = right
        father[get_root(left, father)] = get_root(right, father)
    if not l_set.difference(r_set) and is_common_root(father):
        print(1)
    else:
        print(0)


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
