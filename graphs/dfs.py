edges = [line.rstrip() for line in open('graph.txt')]

directed_graph = dict()
for edge in edges:
    start_node = edge[0]
    end_node = edge[2]
    if start_node in directed_graph:
        directed_graph[start_node].append(end_node)
    else:
        directed_graph[start_node] = [end_node]

# directed graph:
#     'A': ['D', 'C', 'G'],
#     'C': ['D', 'E'],
#     'B': ['K'],
#     'E': ['N', 'F', 'H'],
#     'D': ['B'],
#     'F': ['I'],
#     'H': ['G'],
#     'K': ['D'],
#     'J': ['I'],
#     'M': ['F', 'L'],
#     'L': ['M'],
#     'N': ['A', 'G']

# recursive DFS algorithm
discovery_time = dict()
finishing_time = dict()
counter = 1
def dfs_rec(graph, start, visited=None):
    global counter
    if visited is None:
        visited = []
    if counter == 1:
        discovery_time[start] = counter
        counter += 1
    visited.append(start)
    if start in graph.keys():
        unvisited = [x for x in list(graph[start]) if x not in visited]
        for vertex in unvisited:
            if vertex not in discovery_time.keys():
                discovery_time[vertex] = counter
                counter += 1
        for vertex in unvisited:
            if vertex not in visited:
                dfs_rec(graph, vertex, visited)
    if start not in finishing_time.keys():
        finishing_time[start] = counter
        counter += 1
    return visited
print('Recursive DFS algorithm: ' + str(dfs_rec(directed_graph, 'A')))
print('Discovery times: ' + str(discovery_time))
print('Finishing times: ' + str(finishing_time))

# not recursive DFS algorithm with stack
def dfs_no_rec_stack(graph, start):
    visited = []
    stack = [start]
    while stack:
        vertex = stack.pop(0)
        if vertex not in visited:
            visited.append(vertex)
            if vertex in graph.keys():
                unvisited = [x for x in list(graph[vertex]) if x not in visited]
                stack = unvisited + stack
    return visited
print('Not recursive DFS algorithm with stack ' + str(dfs_no_rec_stack(directed_graph, 'A')))