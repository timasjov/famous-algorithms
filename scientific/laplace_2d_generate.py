#!/usr/local/bin/python
# coding=utf-8

import numpy as np
import matplotlib.pylab as plt
from mpl_toolkits.mplot3d import Axes3D
import time

start = time.time()
N = 10
m = (N - 1) * (N - 1)

# Generates 2D Laplace matrix using Dirichlet boundary conditions
def generate_2D(N):
    L = np.zeros(shape=((N - 1) * (N - 1), (N - 1) * (N - 1)))
    for i in range(0, m):       # rows
        for j in range(0, m):   # columns
            L[i, j] = 0.0
            if i == j:
                L[i, j] = 4.0
            if i + 1 == j or j + 1 == i or i + 3 == j or j + 3 == i:
                L[i, j] = -1.0
    return L

L = generate_2D(N)
b = np.random.randn(m)
x = np.linalg.solve(L, b)
x.shape = (N - 1, N - 1)
elapsed = (time.time() - start)
print("Elapsed: {}".format(elapsed))

space = np.linspace(0,1, N + 1)
fig = plt.Figure()
axes1 = plt.subplot(2, 2, 1, projection="3d")
xx, yy = np.meshgrid(space, space)
values = np.zeros(shape=(N + 1,N + 1))

for i in range(1, N):
    for j in range(1, N):
        values[i,j] = x[i - 1, j - 1]

plt.contour(xx, yy, values)
plt.show()