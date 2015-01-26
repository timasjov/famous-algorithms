class RadixSort {

    static void main(String[] args) {

        final int ONE_HUNDRED_THOUSAND = 100000
        final int ONE_HUNDRED = 100

        ONE_HUNDRED.times {
            def integers64bit = []
            // def integers32bit = []

            ONE_HUNDRED_THOUSAND.times {
                integers64bit << new Random().nextLong()
                // integers32bit << new Random().nextInt()
            }

            def start = System.currentTimeMillis()
            sort(integers64bit)
            def finish = System.currentTimeMillis()
            println "Time elapsed: ${finish - start} ms"
        }
    }

    // LSD implementation
    static sort(integers) {
        final long radix = 10
        long lsdBit = 1
        def buckets = []
        radix.times { buckets << [] }

        boolean canProceedToNextBit = false
        while (!canProceedToNextBit) {
            canProceedToNextBit = true
            // put number into correct bucket
            integers.each {
                long withinLsdBit = it / lsdBit
                int bucketNumber = withinLsdBit % radix
                buckets[bucketNumber] << it
                if (canProceedToNextBit && withinLsdBit > 0) canProceedToNextBit = false
            }

            // set elements from bucket as input
            int bucketPointer = 0
            buckets.each { bucket ->
                int bucketSize = bucket.size()
                integers[bucketPointer..bucketSize + bucketPointer] = bucket
                bucketPointer += bucketSize
                bucket.clear()
            }

            // go to next lsd bit
            lsdBit *= radix
        }
    }

}