# Collection of simple everyday algorithms
A set of everyday algorithms. Majority of algorithms implemented in Python, however, some of them are also in Groovy or Java.

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

### Edit distance
Implementation of [Damerau–Levenshtein distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance) - distance between two strings, i.e., finite sequence of symbols, given by counting the minimum number of operations needed to transform one string into the other, where an operation is defined as an insertion, deletion, or substitution of a single character, or a transposition of two adjacent characters.

### Clustering
- [DBSCAN](https://en.wikipedia.org/wiki/DBSCAN) - pure and simple implementation from pseudocode.
- [OPTICS](https://en.wikipedia.org/wiki/OPTICS_algorithm) - implementation produces hierarchical structure of the clusters by creating dendrogram and examining steep down and steep up areas.