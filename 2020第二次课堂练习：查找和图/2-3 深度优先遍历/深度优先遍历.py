def get_graph():
    n, start = input().strip().split(' ')
    n = int(n)
    input()
    adj_mat = []
    start_index = -1
    for i in range(n):
        tmp_list = input().strip().split(' ')
        if tmp_list[0] == start:
            start_index = i
        am = list(map(int, tmp_list[1:]))
        adj_list = []
        for j in range(n):
            if am[j] == 1:
                adj_list.append(j)
        adj_mat.append(adj_list)
    return n, start_index, adj_mat


def dfs_max_depth(u, adj_mat, vis, depth):
    vis[u] = True
    max_depth = depth
    for v in adj_mat[u]:
        if not vis[v]:
            max_depth = max(max_depth, dfs_max_depth(v, adj_mat, vis, depth + 1))
    vis[u] = False
    return max_depth


def dfs(n, start, adj_mat):
    vis = [False] * n
    print(dfs_max_depth(start, adj_mat, vis, 1))


def solve():
    n, start, adj_mat = get_graph()
    dfs(n, start, adj_mat)


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
