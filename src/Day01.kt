import kotlin.math.abs


fun main() {

    fun parseInput(raw: List<String>): Pair<List<Long>, List<Long>> {
        val left = mutableListOf<Long>()
        val right = mutableListOf<Long>()

        raw.forEach {
            val temp = it.split(" ")
            left.add(temp.first().toLong())
            right.add(temp.last().toLong())
        }
        return Pair(left.toList(), right.toList())
    }

    fun part1(input: List<String>): Long {
        val parsedInput = parseInput(input)
        return parsedInput.first.sorted()
            .zip(parsedInput.second.sorted())
            { a, b -> abs(a - b) }.sum()
    }

    fun part2(input: List<String>): Long {
        val parsedInput = parseInput(input)
        return parsedInput.first.sumOf { n -> parsedInput.second.count { it == n } * n }
    }

    val testInput = readInput("Day01_test")

    check(part1(testInput) == 11L)
    check(part2(testInput) == 31L)
    // Read the input from the `src/Day00.txt` file.
    solve("Day01", ::part1, ::part2)

}
