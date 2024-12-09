fun main() {

    fun parse(input: List<String>): MutableList<Int?> {
        val disc = mutableListOf<Int?>()
        input[0].mapIndexed { i, x ->
            if (i % 2 == 0) {
                repeat(x.digitToInt()) {
                    disc.add(i / 2)
                }
            } else {
                repeat(x.digitToInt()) {
                    disc.add(null)
                }
            }

        }
        return disc
    }

    fun part1(input: List<String>): Number {
        val disc = parse(input)
        var nextSpot = 0
        var nextBlock = disc.lastIndex
        do {
            while (disc[nextSpot] != null) {
                nextSpot++
            }
            while (disc[nextBlock] == null) {
                nextBlock--
            }
            disc[nextSpot] = disc[nextBlock]
            disc[nextBlock] = null
            nextSpot++
            nextBlock--
        } while (nextSpot < nextBlock)
        return disc.mapIndexed { i, x -> x?.toLong()?.times(i) ?: 0  }.sum()
    }

    fun List<Int?>.findNextSpot(fileSize: Int, stop: Int): Int? {
        // returns the index of the next spot, or null
        var emptyIndex = 0
        do {
            while (this[emptyIndex] != null) {
                emptyIndex++
            }
            var space = 1
            while (this[emptyIndex + space] == null) {
                space++
            }
            if (space >= fileSize) {
                return emptyIndex
            }
            emptyIndex += space
        } while (emptyIndex < stop)
        return null
    }

    fun part2(input: List<String>): Number {
        val disc = parse(input)

        for (file in disc.max() downTo 1) {
            var fileIdx = disc.lastIndexOf(file)
            var fileSize = 1
            // determine file size
            while (disc[fileIdx - 1] == file) {
                fileIdx--
                fileSize++
            }
            //search spot
            var emptyIdx = disc.findNextSpot(fileSize, fileIdx) ?: continue
            // move the file as
            while (fileSize > 0) {
                disc[emptyIdx] = disc[fileIdx]
                disc[fileIdx] = null
                fileSize--
                fileIdx++
                emptyIdx++
            }
            //println(disc.joinToString("") { if (it == -1L) "." else it.toString() })
        }
        return disc.mapIndexed { i, x -> (x?.times(i))?.toLong() ?: 0L }.sum().also(::println)
    }

    // test before attempt to solve
    val testInput = readInput("Day09_test")

    check(part1(testInput) == 1928L)
    check(part2(testInput) == 2858L)

    // 6398177437733 too high
    // solve with real input
    solve("Day09", ::part1, ::part2)
}
