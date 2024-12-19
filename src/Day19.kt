fun main() {

    fun parse(input: List<String>): Pair<List<String>, List<String>> {
        val towels = input[0].split(",").map { it.trim() }
        val patterns = input.drop(2)
        return towels to patterns
    }

    fun String.canBeMadeWith(substrings: List<String>): Boolean {
        if (this.isBlank()) return true
        val suffix = substrings.filter { this.endsWith(it) }
        if (suffix.isEmpty()) return false
        return suffix.any { this.substringBeforeLast(it).canBeMadeWith(substrings) }
    }

    fun String.howManyCombinationsWith(substrings: List<String>, cache: MutableMap<String, Long>): Long {
        if (this.isBlank()) return 1L
        if (this in cache) return cache[this]!!
        val prefix = substrings.filter { this.endsWith(it) }
        cache[this] = prefix.sumOf { this.substringBeforeLast(it).howManyCombinationsWith(substrings, cache) }
        return cache[this]!!

    }

    fun part1(input: List<String>): Number {
        val (towels, patterns) = parse(input)
        return patterns.count { it.canBeMadeWith(towels) }
    }

    fun part2(input: List<String>): Number {
        val (towels, patterns) = parse(input)
        return patterns.sumOf { it.howManyCombinationsWith(towels, mutableMapOf()) }
    }

    // test before attempt to solve
    val testInput = readInput("Day19_test")
    check(part1(testInput) == 6)
    check(part2(testInput) == 16L)

    // solve with real input
    solve("Day19", ::part1, ::part2)
}
