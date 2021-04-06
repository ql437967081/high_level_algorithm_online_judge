class Solution:
    def __init__(self, s1, s2):
        self.s1 = s1
        self.s2 = s2
        self.n1 = len(s1)
        self.n2 = len(s2)
        self.res = set()
        self.max_len = 0

    def run(self, i, j, s):
        tmp_len = len(s)
        s1, s2, n1, n2, max_len = self.s1, self.s2, self.n1, self.n2, self.max_len
        if min(n1 - i, n2 - j) + tmp_len < max_len:
            return
        if i == n1 or j == n2:
            if tmp_len > max_len:
                self.max_len = tmp_len
                self.res = set()
            self.res.add(s)
            return
        if s1[i] == s2[j]:
            self.run(i + 1, j + 1, s + s1[i])
        self.run(i + 1, j, s)
        self.run(i, j + 1, s)

    def print(self):
        for s in sorted(self.res):
            print(s)


if __name__ == '__main__':
    t = int(input().strip())
    while t > 0:
        slt = Solution(input().strip(), input().strip())
        slt.run(0, 0, '')
        slt.print()
        t -= 1
