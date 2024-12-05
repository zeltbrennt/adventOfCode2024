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

    fun part1(input: List<String>): Long {
        val (rules, updates) = parseInput(input)
        val invalidStates = rules.map { it.split("|").reversed().joinToString(",") }
        return updates
            .filter { update -> invalidStates.none { state -> update.contains(state) } }
            .sumOf {
                val pages = it.split(",")
                pages[pages.lastIndex / 2].toLong()
            }
    }


    fun String.fixWith(rules: List<String>): List<String> {
        return this.split(",").sortedWith { a, b ->
            when {
                "$a|$b" in rules -> -1
                else -> 1
            }
        }
    }

    fun part2(input: List<String>): Long {
        val (rules, updates) = parseInput(input)
        val invalidStates = rules.map { it.split("|").reversed().joinToString(",") }
        return updates.filter { update -> invalidStates.any { state -> update.contains(state) } }
            .map { it.fixWith(rules) }
            .sumOf { it[it.lastIndex / 2].toLong() }
    }

    // test before attempt to solve
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143L)
    check(part2(testInput) == 123L)

    // solve with real input
    solve("Day05", ::part1, ::part2)
}
