import kotlin.math.abs
import kotlin.time.measureTime

fun part1(input: List<String>): Int {
    val parsedInput = parseInput(input)
    return parsedInput.first.sorted()
        .zip(parsedInput.second.sorted())
        { a, b -> abs(a-b) }.sum()
}

fun part2(input: List<String>): Int {
    val parsedInput = parseInput(input)
    return parsedInput.first.sumOf { n -> parsedInput.second.count { it == n } * n }
}

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

fun main() {

    val testInput = readInput("Day01_test")

    check(part1(testInput) == 11)
    check(part2(testInput) == 31)
    // Read the input from the `src/Day00.txt` file.
    val input = readInput("Day01")

    measureTime { print("Part 1: ${part1(input)}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${part2(input)}".padEnd(40, ' ')) }.also { println("$it") }
}
