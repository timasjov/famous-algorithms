#!/usr/local/bin/python
# coding=utf-8

import numpy as np

# Example matrix
A = np.matrix([[3, 1, 2], [0, -2, -2], [1, 5, 3]], dtype=float)
X = np.matrix([[0, 0, 0], [0, 0, 0], [0, 0, 0]], dtype=float)

# LU factorization of matrix A
def LU(A):
    n = len(A)
    A1 = A.copy()
    M = A.copy()
    LU = A.copy()
    U = X.copy()
    L = X.copy()

    for k in range(0, n - 1):
        # stop in case pivot = 0
        if A[k, k] == 0:
            return 0
        for i in range(k + 1, n):
            # coefficient calculation in column i
            M[i, k] = A1[i, k] / A1[k, k]
            LU[i, k] = M[i, k]
            L[i, k] = LU[i, k]
        for j in range(k + 1, n):
            # applying transformations to the rest of the matrix
            for i in range(k + 1, n):
                A1[i, j] = A1[i, j] - M[i, k] * A1[k, j]
                LU[i, j] = A1[i, j]

    for i in range(0, n):
        for j in range(0, n):
            if i <= j:
                U[i, j] = LU[i, j]

    for i in range(len(L)):
        L[i, i] = 1
    return LU, U, L

factorization = LU(A)
U = factorization[1]
L = factorization[2]

print('LU', factorization[0])
print('L', factorization[2])
print('U', factorization[1])