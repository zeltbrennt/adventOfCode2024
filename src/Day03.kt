fun main() {


    fun addMultiplications(input: String): Long {
        return Regex("mul\\(\\d+,\\d+\\)")
            .findAll(input)
            .map { it.value }
            .map { instruction ->
                Regex("\\d+")
                    .findAll(instruction)
                    .map { it.value.toLong() }
            }.sumOf { it.first() * it.last() }
    }

    fun part1(input: List<String>): Long {
        return addMultiplications(input.joinToString())
    }

    fun part2(input: List<String>): Long {
        val filtered = input.joinToString().replace(Regex("don't\\(\\).+?do\\(\\)"), "")
        return addMultiplications(filtered)
    }

    // test before attempt to solve
    val testInput = readInput("Day03_test")
    check(part1(listOf(testInput.first())) == 161L)
    check(part2(listOf(testInput.last())) == 48L)

    // solve with real input
    solve("Day03", ::part1, ::part2)
}
