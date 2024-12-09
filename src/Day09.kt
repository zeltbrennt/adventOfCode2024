fun main() {

    fun parse(input: List<String>): MutableList<Long> {
        val disc = mutableListOf<Long>()
        input[0].mapIndexed { i, x ->
            if (i % 2 == 0) {
                repeat(x.digitToInt()) {
                    disc.add(i.toLong() / 2)
                }
            } else {
                repeat(x.digitToInt()) {
                    disc.add(-1L)
                }
            }

        }
        return disc
    }

    fun part1(input: List<String>): Number {
        val disc = parse(input)
        var nextEmpty = 0
        var nextMove = disc.lastIndex
        do {
            while (disc[nextEmpty] != -1L) {
                nextEmpty++
            }
            while (disc[nextMove] == -1L) {
                nextMove--
            }
            disc[nextEmpty] = disc[nextMove]
            disc[nextMove] = -1
        } while (nextEmpty < nextMove)
        return disc.filter { it >= 0 }.mapIndexed { i, x -> i * x }.sum()
    }

    fun List<Long>.findNextSpot(fileSize: Int, stop: Int): Int {
        // returns the index of the next spot, or -1
        var emptyIndex = 0
        do {
            while (this[emptyIndex] != -1L) {
                emptyIndex++
            }
            var space = 1
            while (this[emptyIndex + space] == -1L) {
                space++
            }
            if (space >= fileSize) {
                return emptyIndex
            }
            emptyIndex += space
        } while (emptyIndex < stop)
        return -1
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
            var emptyIdx = disc.findNextSpot(fileSize, fileIdx)
            if (emptyIdx == -1) {
                continue
            }
            // move the file as
            while (fileSize > 0) {
                disc[emptyIdx] = disc[fileIdx]
                disc[fileIdx] = -1L
                fileSize--
                fileIdx++
                emptyIdx++
            }
            //println(disc.joinToString("") { if (it == -1L) "." else it.toString() })
        }
        return disc.mapIndexed { i, x -> if (x >= 0L) i * x else 0L }.sum()
    }

    // test before attempt to solve
    val testInput = readInput("Day09_test")

    check(part1(testInput) == 1928L)
    check(part2(testInput) == 2858L)

    // 6398177437733 too high
    // solve with real input
    solve("Day09", ::part1, ::part2)
}
