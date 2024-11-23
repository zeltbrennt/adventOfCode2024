import kotlin.time.measureTime

fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day00_test")
    check(part1(testInput) == 0)

    // Read the input from the `src/Day00.txt` file.
    val input = readInput("Day00")
    measureTime { print("Part 1: ${part1(input)}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${part2(input)}".padEnd(40, ' ')) }.also { println("$it") }
}
