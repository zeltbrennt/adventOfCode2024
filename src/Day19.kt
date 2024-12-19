fun main() {

    fun parse(input: List<String>): Pair<List<String>, List<String>> {
        val towels = input[0].split(",").map { it.trim() }
        val patterns = input.subList(2, input.lastIndex)
        return towels to patterns
    }

    fun String.canBeMadeWith(substrings: List<String>): Boolean {
        if (this.isBlank()) return true
        val suffix = substrings.filter { this.endsWith(it) }
        if (suffix.isEmpty()) return false
        return suffix.any { this.substringBefore(it).canBeMadeWith(substrings) }
    }

    fun part1(input: List<String>): Number {
        val (towels, patterns) = parse(input)
        return patterns.count { it.canBeMadeWith(towels) }
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day19_test")
    check(part1(testInput) == 6)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day19", ::part1, ::part2)
}
