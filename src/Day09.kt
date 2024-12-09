fun main() {


    fun part1(input: List<String>): Number {
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
        var nextEmpty = 0
        var nextMove = disc.lastIndex
        do {
            nextEmpty = disc.indexOf(-1)
            while (disc[nextMove] == -1L) {
                nextMove--
            }
            disc[nextEmpty] = disc[nextMove]
            disc[nextMove] = -1
        } while (nextEmpty < nextMove)


        return disc.filter { it >= 0 }.mapIndexed { i, x -> i * x }.sum()

    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day09_test")

    check(part1(testInput) == 1928L)
    check(part2(testInput) == 0)

    // solve with real input
    solve("Day09", ::part1, ::part2)
    println("done")
}
