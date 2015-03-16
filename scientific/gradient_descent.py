#!/usr/local/bin/python
# coding=utf-8

import numpy as np
from scipy import optimize
import matplotlib.pylab as plt

# Initial guess x^0
x = [(-8, -2)] 
h = 1E-07
tolerance = 1E-03

# Example function
def F(x1, x2):
    return (x1 ** 2) + 5 * (x2 ** 2)


def gradient(x1, x2):
    ix1 = (F(x1 + h, x2) - F(x1, x2)) / h
    ix2 = (F(x1, x2 + h) - F(x1, x2)) / h
    dF = (ix1, ix2)
    return dF


def searchDirection(gradient):
    sd = (-gradient[0], -gradient[1])
    return sd


def stepSize(x):
    res = optimize.line_search(lambda z: F(z[0], z[1]), lambda t: gradient(t[0], t[1]), np.array(x), np.array(sd), gr)
    alpha = res[0]
    return alpha


def makeStep(x, alpha, sd):
    step = x + np.multiply(alpha, sd)
    return step[0], step[1]


def checkTolerance(x1, x2):
    return True if np.abs(x1) < tolerance or np.abs(x2) < tolerance else False


i = 0
while True:
    currentX = x[i]
    gr = gradient(currentX[0], currentX[1])
    sd = searchDirection(gr)
    alpha = stepSize(currentX)
    newX = makeStep(currentX, alpha, sd)
    if checkTolerance(newX[0], newX[1]):
        break
    else:
        x.append(newX)
        i += 1

points = np.array(x)

fig = plt.Figure()
fig, ax = plt.subplots()
ax.set_aspect(1)
x1, x2, y1, y2 = plt.axis()
plt.axis((-8, 8, -2, 2))
ax.plot(points[:, 0], points[:, 1], "bo-", label=r"gradient descent")

xc = np.linspace(-10, 10, 100)
yc = np.linspace(-5, 5, 100)
XC, YC = np.meshgrid(xc, yc)
levels = np.arange(-40, 40, 4)
plt.contour(XC, YC, F(XC,YC), levels)
ax.legend(loc=1)

plt.show()