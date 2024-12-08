fun main() {


    fun part1(input: List<String>): Number {
        return 0
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 0L)
    check(part2(testInput) == 0L)

    // solve with real input
    solve("Day08", ::part1, ::part2)
}
