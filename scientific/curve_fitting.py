#!/usr/local/bin/python
# coding=utf-8

import numpy as np
import matplotlib.pyplot as plt

# Simple 2 degree polynomial fit can be done using numpy build in functions: numpy.polyfit and poly1d
# First function performs squares polynomial fit and second calculates new points

# Example data
points = np.array([(0., 0.), (1., 1.), (-1., .9), (.5, .7)])

# Get x and y vectors
# numpy.polyfit will work only when array is sorted
x = np.array([-1., 0., .5, 1.])
y = np.array([.9, 0., .7, 1.])

# Calculate polynomial
z = np.polyfit(x, y, 2)
f = np.poly1d(z)

# Calculate new x's and y's
x_new = np.linspace(x[0], x[-1], 50)
y_new = f(x_new)

plt.plot(x, y, 'o', x_new, y_new)
plt.xlim([x[0] - 1, x[-1] + 1])
plt.show()