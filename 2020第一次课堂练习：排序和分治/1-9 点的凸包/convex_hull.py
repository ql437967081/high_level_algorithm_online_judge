def get_line_equation(start, end):
    x1, y1 = start
    x2, y2 = end
    return y2 - y1, x1 - x2, x2 * y1 - x1 * y2


def find_convex_hull(start, end, points):
    if len(points) == 0:
        return [], False

    a, b, c = get_line_equation(start, end)

    def cal_dist(pt):
        x, y = pt
        return a * x + b * y + c

    farthest_point = None
    min_dist = 1
    on_line_points = []
    left_points = []
    for point in points:
        dist = cal_dist(point)
        if dist <= 0:
            if dist == 0:
                on_line_points.append(point)
            else:
                left_points.append(point)
            if dist < min_dist:
                min_dist = dist
                farthest_point = point

    if farthest_point is None:
        return [], False
    if min_dist == 0:
        left_points = on_line_points
    for point in left_points:
        points.remove(point)
    left_points.remove(farthest_point)
    start_to_fp_convex_hull = find_convex_hull(start, farthest_point, left_points)[0]
    fp_to_end_convex_hull = find_convex_hull(farthest_point, end, left_points)[0]
    return [farthest_point] + start_to_fp_convex_hull + fp_to_end_convex_hull, min_dist < 0


def output_point(point):
    return '{0} {1}'.format(point[0], point[1])


def solve():
    n = int(input().strip())
    xys = list(map(int, input().strip().split(' ')))

    points = []
    min_x = xys[0] + 1 if n > 0 else 0
    max_x = min_x - 2
    min_x_point = None
    max_x_point = None
    for i in range(n):
        x_index = i << 1
        y_index = x_index + 1
        x = xys[x_index]
        y = xys[y_index]
        point = (x, y)
        points.append(point)
        if x < min_x:
            min_x = x
            min_x_point = point
        if x > max_x:
            max_x = x
            max_x_point = point
    if min_x == max_x or n < 3:
        print(-1)
        return
    points.remove(min_x_point)
    points.remove(max_x_point)
    left_convex_hull, left_has_points = find_convex_hull(min_x_point, max_x_point, points)
    right_convex_hull, right_has_points = find_convex_hull(max_x_point, min_x_point, points)
    if not left_has_points and not right_has_points:
        print(-1)
        return
    convex_hull = [min_x_point, max_x_point] + left_convex_hull + right_convex_hull
    print(', '.join(list(map(output_point, sorted(convex_hull)))))


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        solve()
        t -= 1
