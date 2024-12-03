import kotlin.time.measureTime

fun main() {


    fun part1(input: String): Long {
        return Regex("mul\\(\\d+,\\d+\\)")
            .findAll(input)
            .map { it.value }
            .map { instruction ->
                Regex("\\d+")
                    .findAll(instruction)
                    .map { it.value.toLong() }
            }.sumOf { it.first() * it.last() }
    }

    fun part2(input: String): Long {
        val filtered = input.replace(Regex("don't\\(\\).+?do\\(\\)"), "")
        return part1(filtered)
    }

    // test before attempt to solve
    val testInput = readInput("Day03_test")
    check(part1(testInput.first()) == 161L)
    check(part2(testInput.last()) == 48L)

    // solve with real input
    val input = readInput("Day03").joinToString()
    measureTime { print("Part 1: ${part1(input)}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${part2(input)}".padEnd(40, ' ')) }.also { println("$it") }
}
