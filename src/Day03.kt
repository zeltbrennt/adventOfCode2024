fun main() {

    val instructionPattern = Regex("""mul\((\d+),(\d+)\)""")
    val deactivationPattern = Regex("""don't\(\).+?(do\(\)|$)""")

    fun String.addMultiplications(): Long {
        return instructionPattern
            .findAll(this)
            .sumOf {
                val (a, b) = it.destructured
                a.toLong() * b.toLong()
            }
    }

    fun part1(input: List<String>): Long {
        return input.joinToString().addMultiplications()
    }

    fun part2(input: List<String>): Long {
        return input
            .joinToString()
            .replace(deactivationPattern, "")
            .addMultiplications()
    }

    // test before attempt to solve
    val testInput = readInput("Day03_test")
    check(part1(listOf(testInput.first())) == 161L)
    check(part2(listOf(testInput.last())) == 48L)

    // solve with real input
    solve("Day03", ::part1, ::part2)
}
