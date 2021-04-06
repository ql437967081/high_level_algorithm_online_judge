## 整除查询

### 描述

Given an array of positive integers and many queries for divisibility. In every query Q[i], we are given an integer K , we need to count all elements in the array which are perfectly divisible by K.

Constraints:1<=T<=1001<=N,M<=1051<=A[i],Q[i]<=105

### 输入

The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. Each test case consists of three lines. First line of each test case contains two integers N & M, second line contains N space separated array elements and third line contains M space separated queries.

### 输出

For each test case,In new line print the required count for each query Q[i].

### 输入样例 1 

```
2
6 3
2 4 9 15 21 20
2 3 5
3 2
3 4 6
2 3
```

### 输出样例 1

```
3 3 2
2 2
```