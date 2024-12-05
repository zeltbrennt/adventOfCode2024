fun main() {


    fun parseInput(input: List<String>): Pair<List<String>, List<String>> {
        val rulesRev = mutableListOf<String>()
        val updates = mutableListOf<String>()
        input.forEach {
            when {
                "|" in it -> rulesRev.add(it.split("|").reversed().joinToString(","))
                "," in it -> updates.add(it)
            }
        }
        return Pair(rulesRev.toList(), updates.toList())
    }

    fun part1(input: List<String>): Long {
        val (invalidStates, updates) = parseInput(input)
        return updates
            .filter { update -> invalidStates.none { state -> update.contains(state) } }
            .sumOf {
                val pages = it.split(",")
                pages[pages.lastIndex / 2].toLong()
            }
    }

    fun part2(input: List<String>): Long {
        val (invalidStates, updates) = parseInput(input)
        updates.filter { update -> invalidStates.any { state -> update.contains(state) } }.forEach(::println)
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143L)
    check(part2(testInput) == 123L)

    // solve with real input
    solve("Day05", ::part1, ::part2)
}
