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


    fun evaluatesWithAddMultiply(numbers: List<Long>, expected: Long, actual: Long, idx: Int = 1): Boolean {
        if (actual > expected) {
            return false
        }
        if (idx > numbers.lastIndex) {
            return actual == expected
        }
        return when {
            evaluatesWithAddMultiply(numbers, expected, actual + numbers[idx], idx + 1) -> true
            evaluatesWithAddMultiply(numbers, expected, actual * numbers[idx], idx + 1) -> true
            else -> false
        }
    }

    fun evaluatesWithAddMultiplyConcat(numbers: List<Long>, result: Long, idx: Int): Boolean {

        val resultString = result.toString()
        val numberString = numbers[idx].toString()

        return when {
            idx == 0 -> result == numbers[0]
            result == 0L -> false

            resultString.endsWith(numberString) &&
                    evaluatesWithAddMultiplyConcat(
                        numbers,
                        resultString.removeSuffix(numberString).toLongOrNull() ?: 0,
                        idx - 1
                    ) -> true

            result % numbers[idx] == 0L &&
                    evaluatesWithAddMultiplyConcat(
                        numbers,
                        result / numbers[idx],
                        idx - 1
                    ) -> true

            result - numbers[idx] > 0L &&
                    evaluatesWithAddMultiplyConcat(
                        numbers,
                        result - numbers[idx],
                        idx - 1
                    ) -> true

            else -> false
        }
    }


    fun part1(input: List<String>): Number {
        val calibrations = parse(input)
        return calibrations.filter {
            evaluatesWithAddMultiply(it.value, it.key, it.value.first())
        }.keys.sum()
    }

    fun part2(input: List<String>): Number {
        val calibrations = parse(input)
        return calibrations.filter {
            evaluatesWithAddMultiplyConcat(it.value, it.key, it.value.lastIndex)
        }.keys.sum()
    }

    // test before attempt to solve
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    // solve with real input
    solve("Day07", ::part1, ::part2)
}
