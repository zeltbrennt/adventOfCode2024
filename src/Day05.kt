fun main() {


    fun parseInput(input: List<String>): Pair<List<String>, List<String>> {
        val rules = mutableListOf<String>()
        val updates = mutableListOf<String>()
        input.forEach {
            when {
                "|" in it -> rules.add(it)
                "," in it -> updates.add(it)
            }
        }
        return rules.toList() to updates.toList()
    }

    fun String.fixWith(rules: List<String>): List<String> {
        return this.split(",").sortedWith { a, b ->
            when {
                "$a|$b" in rules -> -1
                else -> 1
            }
        }
    }

    fun part1(input: List<String>): Int {
        val (rules, updates) = parseInput(input)
        return updates.map { it.fixWith(rules) }
            .intersect(updates.map { it.split(",") }.toSet())
            .sumOf { it[it.lastIndex / 2].toInt() }
    }

    fun part2(input: List<String>): Int {
        val (rules, updates) = parseInput(input)
        return updates.map { it.fixWith(rules) }
            .subtract(updates.map { it.split(",") }.toSet())
            .sumOf { it[it.lastIndex / 2].toInt() }
    }

    // test before attempt to solve
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    // solve with real input
    solve("Day05", ::part1, ::part2)
}
