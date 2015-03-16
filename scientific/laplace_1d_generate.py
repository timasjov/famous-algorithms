#!/usr/local/bin/python
# coding=utf-8

import numpy as np
import matplotlib.pylab as plt

N = 10

# Generates 1D Laplace matrix using Dirichlet boundary conditions
def generate_1D(N):
    L = np.zeros(shape=((N - 1), (N - 1)))
    for i in range(0, N - 1):       # rows
        for j in range(0, N - 1):   # columns
            L[i, j] = 0.0
            if i == j:
                L[i, j] = 2.0
            if i + 1 == j or j + 1 == i:
                L[i, j] = -1
    return L


L = generate_1D(N)
b = np.random.randn(N - 1)
x = np.linalg.solve(L, b)

points = [x[i - 1] for i in range(1, (len(x) + 1))]
points.append(0)
interval = np.linspace(0.0, 1.0, num=N)

fig = plt.Figure()
fig, ax = plt.subplots()
ax.plot(interval, points, "bo-")
plt.show()