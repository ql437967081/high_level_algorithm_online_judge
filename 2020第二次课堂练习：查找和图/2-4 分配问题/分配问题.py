class Solution:
    def __init__(self):
        self.min_cost = 1e9
        self.min_cost_arranges = []
        self.n, self.cost_mat = Solution.get_input()

    @staticmethod
    def get_input():
        n = int(input().strip())

        def str_to_tuple(s):
            return tuple(map(int, s.split(' ')))

        cost_mat = [[0] * (n + 1) for _ in range(n + 1)]
        for person, task, cost in list(map(str_to_tuple, input().strip().split(','))):
            cost_mat[person][task] = cost
        return n, cost_mat

    def run(self, person, vis, tasks_done, current_cost=0):
        n, cost_mat = self.n, self.cost_mat
        if current_cost > self.min_cost:
            return

        if person > n:
            if current_cost < self.min_cost:
                self.min_cost = current_cost
                self.min_cost_arranges = [tasks_done.copy()]
                return
            if current_cost == self.min_cost:
                self.min_cost_arranges.append(tasks_done.copy())
                return

        task = n
        while task > 0:
            if not vis[task]:
                vis[task] = True
                tasks_done.append(task)
                self.run(person + 1, vis, tasks_done, current_cost + cost_mat[person][task])
                vis[task] = False
                tasks_done.pop()
            task -= 1

    def print_arranges(self):
        def arrange_to_str(arrange):
            return ' '.join(map(str, arrange))

        print(','.join(map(arrange_to_str, self.min_cost_arranges)))

    def run_test_case(self):
        self.run(1, [False] * (self.n + 1), [])
        self.print_arranges()


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        Solution().run_test_case()
        t -= 1
