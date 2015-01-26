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

# recursive BFS algorithm
def bfs_rec(graph, start, visited=None, queue=None):
    if visited is None:
        visited = []
    if queue is None:
        queue = []
    if start not in visited:
        visited.append(start)
    if start in graph.keys():
        unvisited = [x for x in list(graph[start]) if x not in visited]
        [queue.append(v) for v in unvisited if v not in queue]
        if len(queue) != 0:
            vertex_to_visit_next = queue.pop(0)
            bfs_rec(graph, vertex_to_visit_next, visited, queue)
    return visited
print('Recursive BFS algorithm: ' + str(bfs_rec(directed_graph, 'A')))