from random import randrange

MAX_K =16
DEFAULT_K = 8


def hash(word):
    wordStr = str(word)
    assert len(wordStr) <= MAX_K

    value = 0
    for n, ch in enumerate(wordStr):
        value += ord(ch) * 128 ** n
        #value += 2 * ord(ch) ** n
        
    return value


class BloomFilter(object):
    allchars = "".join([chr(i) for i in range(128)])

    def __init__(self, tablesizes, k=DEFAULT_K):
        self.tables = [(size, [0] * size) for size in tablesizes]
        self.k = k

    def add(self, word):
        val = hash(word)
        for size, ht in self.tables:
            ht[val % size] = 1

    def __contains__(self, word):
        val = hash(word)
        return all(ht[val % size] for (size, ht) in self.tables)

bloomFilter = BloomFilter([1001, 1003, 1005])
#bloomFilter = BloomFilter([100000])

lines = []
for line in open('1000_keys.txt'):
    num = line.strip()
    lines.append(num)
    bloomFilter.add(num)

falsePositives = 0
for num in lines:
    if not (num in bloomFilter):
        falsePositives += 1
print('Number of false positives: ' + str(falsePositives))
