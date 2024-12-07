fun main() {

    fun parse(input: List<String>): Map<Long, List<Long>> {
        return buildMap {
            input.forEach { line ->
                val equation = line.split(":")
                val nums = equation.last().trim().split(" ").map { it.toLong() }
                this[equation.first().toLong()] = nums
            }

        }
    }


    fun evaluatesWithAddMultiply(numbers: List<Long>, expected: Long, actual: Long = 0L): Boolean {
        if (numbers.isEmpty()) {
            return actual == expected
        }
        val next = numbers.first()
        return when {
            evaluatesWithAddMultiply(numbers.drop(1), expected, actual + next) -> true
            evaluatesWithAddMultiply(numbers.drop(1), expected, actual * next) -> true
            else -> false
        }
    }

    fun evaluatesWithAddMultiplyConcat(numbers: List<Long>, expected: Long, actual: Long = 0L): Boolean {
        if (numbers.isEmpty()) {
            return actual == expected
        }
        if (actual > expected) {
            return false
        }
        val next = numbers.first()
        return when {
            evaluatesWithAddMultiplyConcat(numbers.drop(1), expected, actual + next) -> true
            evaluatesWithAddMultiplyConcat(numbers.drop(1), expected, actual * next) -> true
            evaluatesWithAddMultiplyConcat(numbers.drop(1), expected, "$actual$next".toLong()) -> true
            else -> false
        }
    }


    fun part1(input: List<String>): Number {
        val calibrations = parse(input)
        return calibrations.filter { evaluatesWithAddMultiply(it.value, it.key) }.map { it.key }.sum()
    }

    fun part2(input: List<String>): Number {
        val calibrations = parse(input)
        return calibrations.filter { evaluatesWithAddMultiplyConcat(it.value, it.key) }.map { it.key }.sum()
    }

    // test before attempt to solve
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    // solve with real input
    solve("Day07", ::part1, ::part2)
}
