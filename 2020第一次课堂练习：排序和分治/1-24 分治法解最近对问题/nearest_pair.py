def to_tuple(s):
    point = list(map(float, s.split(' ')))
    return point[0], point[1]


def dist(point_a, point_b):
    return dist_on_x(point_a, point_b) + dist_on_y(point_a, point_b)


def dist_on_x(point_a, point_b):
    x1, y1 = point_a
    x2, y2 = point_b
    return (x1 - x2)**2


def dist_on_y(point_a, point_b):
    x1, y1 = point_a
    x2, y2 = point_b
    return (y1 - y2)**2


def get_nearest_pair(left, right, pts):
    if left == right:
        return [], None
    if left + 1 == right:
        return [(pts[left], pts[right])], dist(pts[left], pts[right])
    mid = (left + right) >> 1
    l_pairs, l_dist = get_nearest_pair(left, mid, pts)
    r_pairs, r_dist = get_nearest_pair(mid + 1, right, pts)

    min_dist, min_pairs = (l_dist, l_pairs) \
        if r_dist is None or l_dist is not None and l_dist <= r_dist \
        else (r_dist, r_pairs)

    if l_dist is not None and r_dist is not None and l_dist == r_dist:
        min_pairs += r_pairs

    i = mid
    while i >= left and dist_on_x(pts[i], pts[mid + 1]) <= min_dist:
        j = mid + 1
        while j <= right and dist_on_x(pts[i], pts[j]) <= min_dist:
            dist_i_j = dist(pts[i], pts[j])
            if dist_i_j == min_dist:
                min_pairs += [(pts[i], pts[j])]
            elif dist_i_j < min_dist:
                min_dist = dist_i_j
                min_pairs = [(pts[i], pts[j])]
            j += 1
        i -= 1

    return min_pairs, min_dist


def float_to_int(x):
    to_int = int(x)
    if float(to_int) == x:
        return to_int
    return x


def output_point(point):
    return '{0} {1}'.format(float_to_int(point[0]), float_to_int(point[1]))


def output_pair(pair):
    return '{0},{1}'.format(output_point(pair[0]), output_point(pair[1]))


def solve():
    points = list(map(to_tuple, input().strip().split(',')))
    sorted_points = sorted(points)
    nearest_pairs = sorted(get_nearest_pair(0, len(sorted_points) - 1, sorted_points)[0])
    print(','.join(list(map(output_pair, nearest_pairs))))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
