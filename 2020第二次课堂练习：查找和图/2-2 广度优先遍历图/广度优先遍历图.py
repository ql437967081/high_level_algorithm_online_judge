def get_graph():
    n, start = input().strip().split(' ')
    n = int(n)
    index_of_node = {}
    node_name_list = input().strip().split(' ')
    for i in range(n):
        index_of_node[node_name_list[i]] = i
    adj_mat = []
    start_index = -1
    for i in range(n):
        tmp_list = input().strip().split(' ')
        if tmp_list[0] == start:
            start_index = i
        am = list(map(int, tmp_list[1:]))
        am_name = []
        for j in range(n):
            if am[j] == 1:
                am_name.append(node_name_list[j])

        def name_to_index(name):
            return index_of_node[name]

        adj_list = list(map(name_to_index, sorted(am_name)))
        adj_mat.append(adj_list)
    return n, start_index, adj_mat, node_name_list


def bfs(n, start, adj_mat, node_name_list):
    vis = []
    for i in range(n):
        vis.append(False)
    q = [start]
    vis[start] = True
    res = []
    while q:
        node = q.pop(0)
        res.append(node_name_list[node])
        for to_node in adj_mat[node]:
            if not vis[to_node]:
                q.append(to_node)
                vis[to_node] = True
    print(' '.join(res))


def solve():
    n, start, adj_mat, node_name_list = get_graph()
    bfs(n, start, adj_mat, node_name_list)


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
