class Quicksort {

    private static qs(List list) {
        if (list.size() < 2) return list

        def pivot = list.get(0)
        def left = list.findAll { it < pivot }
        def right = list.findAll { it > pivot }
        qs(left) + pivot + qs(right)
    }

    public static void main(String[] args) {

        100.times {
            final long NINE_MILLION = 9000000
            def integers = [] // 64-bit signed integers
            NINE_MILLION.times { integers << new Random().nextLong() }

            def start = System.currentTimeMillis()
            qs(integers)
            def finish = System.currentTimeMillis()
            println "${finish - start} ms"
        }
    }
}
