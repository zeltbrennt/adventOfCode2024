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


    fun evaluatesTo(nums: List<Long>, test: Long, result: Long = 0L): Boolean {
        if (nums.isEmpty()) {
            return test == result
        }
        val next = nums.first()
        return when {
            evaluatesTo(nums.drop(1), test, result + next) -> true
            evaluatesTo(nums.drop(1), test, result * next) -> true
            else -> false
        }
    }


    fun part1(input: List<String>): Number {
        val equations = parse(input)
        return equations.filter { evaluatesTo(it.value, it.key) }.map { it.key }.sum()
    }

    fun part2(input: List<String>): Number {
        val equations = parse(input)
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    // solve with real input
    solve("Day07", ::part1, ::part2)
}
