import kotlin.math.abs


fun main() {

    fun parseInput(raw: List<String>): Pair<List<Int>, List<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        raw.forEach {
            val temp = it.split(" ")
            left.add(temp.first().toInt())
            right.add(temp.last().toInt())
        }
        return Pair(left.toList(), right.toList())
    }

    fun part1(input: List<String>): Int {
        val parsedInput = parseInput(input)
        return parsedInput.first.sorted()
            .zip(parsedInput.second.sorted())
            { a, b -> abs(a - b) }.sum()
    }

    fun part2(input: List<String>): Int {
        val parsedInput = parseInput(input)
        return parsedInput.first.sumOf { n -> parsedInput.second.count { it == n } * n }
    }

    val testInput = readInput("Day01_test")

    check(part1(testInput) == 11)
    check(part2(testInput) == 31)
    // Read the input from the `src/Day00.txt` file.
    solve("Day01", ::part1, ::part2)

}
