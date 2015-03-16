#!/usr/local/bin/python
# coding=utf-8

import numpy as np

# Example matrix
A = np.matrix([[3, 1, 2], [0, -2, -2], [1, 5, 3]], dtype=float)
b = np.array([1, 1, 2], dtype=float)
X = np.matrix([[0, 0, 0], [0, 0, 0], [0, 0, 0]], dtype=float)

# Partial pivoting in the Gauss elimination method of matrix A
def pivotingLU(A, b):
    A1 = A.copy()
    n = len(A)
    L = np.zeros(shape=(n, n))
    U = np.zeros(shape=(n, n))
    ps = np.arange(b.size)

    for k in range(0, n - 1):
        column = [np.abs(A1[i, k]) for i in range(0, n)]
        column2 = column[k:n]
        p = np.argmax(column2) + k

        if p != k:
            for j in range(0, n):
                z = A1[k, j]
                A1[k, j] = A1[p, j]
                A1[p, j] = z

            z = ps[k]
            ps[k] = ps[p]
            ps[p] = z

        if A1[k, k] != 0:
            for i in range(k + 1, n):
                A1[i, k] = A1[i, k] / A1[k, k]

            for j in range(k + 1, n):
                for i in range(k + 1, n):
                    A1[i, j] = A1[i, j] - A1[i, k] * A1[k, j]

            for i in range(n):
                for j in range(n):
                    if i > j:
                        L[i, j] = A1[i, j]
                    else:
                        U[i, j] = A1[i, j]
                    if i == j:
                        L[i, j] = 1
    return ps, L, U


plu = pivotingLU(A, b)
ps = plu[0]
U = plu[1]
L = plu[2]

print('ps=', plu[0])
print('L=', plu[2])
print('U=', plu[1])