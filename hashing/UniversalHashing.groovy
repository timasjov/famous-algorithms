class UniversalHashing {

    public static void main(String[] args) {

        int p = 15485867
        int a = 1129
        int b = 8649

        int m1 = 1000
        int m2 = 10000
        int m3 = 100000
        int m4 = 1000000

        HashMap<Integer, List<Long>> hashTable = new HashMap<>()
        int collisions = 0
        new File('1000_keys.txt').eachLine { line ->
            long key = line as Long
            int hash = calculateHash(key, p, a, b, m2)
            if (!hashTable.containsKey(hash)) {
                hashTable.put(hash, [key])
            } else {
                collisions++
                List<Long> keys = hashTable.get(hash)
                keys << key
            }
        }
        println "With m=$m2, p=$p, a=$a, b=$b number of collisions is $collisions."
    }

    private static int calculateHash(long x, int p, int a, int b, int m) {
        (a * x + b) % p % m
    }

}
