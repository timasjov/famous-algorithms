#!/usr/local/bin/python
# coding=utf-8

import numpy as np

# Implementation of sparse matrices. Alternatively can use scipy.sparse function
class SparseMatrix:
    "Sparse matrix in triple storage format."

    def __init__(self, m, n, nnz):
        """Sparse matrix
        @arg m: number of rows
        @arg n: number of columns
        @arg nnz: number of non-zero elements"""

        self.m = m
        self.n = n
        self.nnz = nnz
        self.irows = np.zeros(nnz, dtype=int)
        self.icols = np.zeros(nnz, dtype=int)
        self.vals = np.zeros(nnz, dtype=float)

    def __str__(self):
        "String representation of the matrix."
        vs = []
        for i in xrange(self.nnz):
            vs.append("%d %d %.15f" % (self.irows[i], self.icols[i], self.vals[i]))
        vs_str = "\n".join(vs)
        return "%d x %d nnz=%d\n%s" % (self.m, self.n, self.nnz, vs_str)

    def __mul__(self, vec):
        """Matrix vector multiplication.
        @arg vec: NumPy vector
        """
        y = np.zeros(vec.shape, dtype=float)
        for k in range(0, self.nnz):
            i = self.irows[k]
            j = self.icols[k]
            v = self.vals[k]
            y[i] += vec[j] * v

        return y

    def __getitem__(self, (i, j)):
        for k in range(self.nnz):
            if self.irows[k] == i and self.icols[k] == j:
                return self.vals[k]
        return 0.0


def as_sparse_matrix(matrix, m, n, nnz):
    "Create sparse matrix from NumPy matrix."
    A = SparseMatrix(m, n, nnz)
    A.irows = [];
    A.icols = [];
    A.vals = [];
    for i in range(len(matrix)):
        for j in range(len(matrix)):
            if matrix[i, j] != 0:
                A.irows.append(i)
                A.icols.append(j)
                A.vals.append(matrix[i, j])

    return A

	
def countnnz(A):
    count = 0
    for i in range(len(A)):
        for j in range(len(A)):
            if A[i, j] != 0:
                count += 1
    return count

# Solves the system of linear equations using Jacobi method
def jakobi(S, b):
    x = np.zeros(shape=(len(b), 1))
    x_next = np.zeros(shape=(len(b), 1))
    tol = 1E-6
    E = tol + 1
    max_iter = 1000
    iter_count = 0
    while E > tol and iter_count < max_iter:
        E = np.sqrt(np.sum((b - S * x) ** 2))
        print "Iter: {}".format(iter_count)
        print "E: {}".format(E)

        for i in range(S.m):
            sum = 0.0
            for j in range(S.n):
                if i != j:
                    sum = x[j] * S[i, j]
                    x_next[i] = 1.0 / S[i, i] * (b[i] - sum)
        x[:] = x_next
        iter_count += 1

    return x
	

# Example matrix
A = np.matrix([[2, -1, 0], [-1, 2, -1], [0, -1, 2]], dtype=float)
b = np.array([[1], [2], [3]], dtype=float)

print "A: {}".format(A)
print "b: {}".format(b)

S = as_sparse_matrix(A, len(A), len(A), countnnz(A))
print "S: {}".format(S)

x = jakobi(S, b)
print "x: {}".format(x)