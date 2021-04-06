def to_edge_tuple(s):
    start, end = s.split(' ')
    return start, end


def top_sort(_in_degree, _queue, _adj):
    if not _queue:
        return 1
    res = 0
    for i in range(len(_queue)):
        p = _queue[i]
        __queue = _queue.copy()
        del __queue[i]
        __in_degree = _in_degree.copy()
        for q in _adj[p]:
            __in_degree[q] -= 1
            if __in_degree[q] == 0:
                __queue.append(q)
        res += top_sort(__in_degree, __queue, _adj)
    return res


def solve():
    adj = {}
    in_degree = {}

    def check_new_point(np):
        if np not in adj:
            adj[np] = []
            in_degree[np] = 0

    for u, v in list(map(to_edge_tuple, input().strip().split(','))):
        check_new_point(u)
        check_new_point(v)
        adj[u].append(v)
        in_degree[v] += 1

    queue = []
    for u in in_degree:
        if in_degree[u] == 0:
            queue.append(u)

    print(top_sort(in_degree, queue, adj))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
