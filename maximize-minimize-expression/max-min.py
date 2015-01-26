def maxmin(a, type):
    if operation == 'max':
        type = True
    else:
        type = False
    height = len(a)
    matrix = [[0] * height for row in xrange(height)]
    for nr_signs in range(height):
        matrix[nr_signs][nr_signs] = int(a[nr_signs])

    for nr_signs in range(1, height):  # loop total number of signs needed to put
        for row in range(0, height - nr_signs):  # loop total number of signs per row
            add = []
            mult = []
            pos = nr_signs + row  # position in row where to put the element
            for operation in range(nr_signs):  # number of operations per row per sign
                add.append(matrix[row][row + operation] + matrix[row + operation + 1][row + nr_signs])
                mult.append(matrix[row][row + operation] * matrix[row + operation + 1][row + nr_signs])
            if type:
                max_mult = max(mult)
                max_add = max(add)
            else:
                max_mult = min(mult)
                max_add = min(add)

            if max_mult >= max_add and type:
                matrix[row][pos] = max_mult
            elif type:
                matrix[row][pos] = max_add
            elif max_mult <= max_add:
                matrix[row][pos] = max_mult
            else:
                matrix[row][pos] = max_add

    return matrix


numbers = [2, 1, 2, 1, 1, 3, 6, 10, 1, 2, 2, 1, 6, 1, 2, 2, 1, 7, 2, 1, 1, 3, 2, 1, 5, 1, 7, 2, 1, 2, 1, 2, 1, 2, 3, 1, 4, 4, 5, 2, 1, 2, 2, 2, 1, 1, 1, 2, 3]
result_max = maxmin(numbers, "max")
print('Max: ' + str(result_max[0][-1]))
result_min = maxmin(numbers, "min")
print('Min: ' + str(result_min[0][-1]))