# Collection of simple everyday algorithms
A set of everyday algorithms. Majority of algorithms are implemented in Python, however, some of them are also in Groovy or Java. Majority of implementations in Python (especially scientific algorithms) require several dependencies: __numpy, matplotlib, scipy__.

### Sorting
- [Quicksort](https://en.wikipedia.org/wiki/Quicksort), typical divide and conquer implementation. On average runs in *O(n log(n))* time complexity, where *n* is problem size.
- [Radix sort](https://en.wikipedia.org/wiki/Radix_sort), least significant digit implementation using bucket method. On average runs in *O(nk)* time complexity, where *n* is problem size and *k* is maximum number of digits.

### Hashing
- [Universal hashing](https://en.wikipedia.org/wiki/Universal_hashing) - implementation of one of the hash functions from universal	familiy of hash functions.
- [Bloom filter](https://en.wikipedia.org/wiki/Bloom_filter) - implementation of space-efficient probabilistic data structure that is used to test whether an element is a member of a set.

Sample set of keys is also added.

### Maximize-minimize expression
Algorithm to maximize and minimize the arithmetic expressions by adding missing * and + signs. I.e., given a list of numbers, e.g. `2 1 3 4`, calculate the maximizing placement of operations. E.g. minimum is (2&#215;1)+(3+4)=9, and maximum is (2+1)&#215;3&#215;4=36.

### Graph traversals
- [Depth-first search](https://en.wikipedia.org/wiki/Depth-first_search), recursive and non recursive implementation with stack. Also, prints each vertex discovery and finishing times.
- [Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search), recursive implementation.

Sample graph is also added.

### Text algorithms
- Edit distance - implementation of [Damerau–Levenshtein distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance) - distance between two strings, i.e., finite sequence of symbols, given by counting the minimum number of operations needed to transform one string into the other, where an operation is defined as an insertion, deletion, or substitution of a single character, or a transposition of two adjacent characters.

### Clustering
- [DBSCAN](https://en.wikipedia.org/wiki/DBSCAN) - pure and simple implementation from pseudocode.
- [OPTICS](https://en.wikipedia.org/wiki/OPTICS_algorithm) - implementation produces hierarchical structure of the clusters by creating dendrogram and examining steep down and steep up areas.

### Scientific algorithms
- [LU-factorisation](https://en.wikipedia.org/wiki/LU_decomposition) - function that performs LU-factorisation of a given matrix A = LU.
- [Gaussian elimination](https://en.wikipedia.org/wiki/Gaussian_elimination) - implementation of partial pivoting in the Gauss elimination method.
- [Curve fitting](https://en.wikipedia.org/wiki/Curve_fitting) - simple 2 degree polynomial fit.
- [Gradient descent](https://en.wikipedia.org/wiki/Gradient_descent) - implementation of Gradient Descent algorithm with example function and initial guess.
- [Jacobi method](https://en.wikipedia.org/wiki/Jacobi_method) - function that solves the system of linear equations using Jacobi method. The function takes a sparse matrix and a right-hand side vector.
- [1D Laplacian matrix](https://en.wikipedia.org/wiki/Laplacian_matrix) - function that generates 1D Laplace matrix using Dirichlet boundary conditions.
- [2D Laplacian matrix](https://en.wikipedia.org/wiki/Laplacian_matrix) - function that generates 2D Laplace matrix using Dirichlet boundary conditions.
- [Conjugate gradient method](https://en.wikipedia.org/wiki/Conjugate_gradient_method) - Conjugate Gradient method with preconditioning for solving sparse linear systems. Is location under other project [here](https://github.com/timasjov/2d-wave-equation/blob/master/cg.py).


