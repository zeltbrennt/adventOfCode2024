fun main() {

    fun parse(input: List<String>): Map<String, List<String>> {
        val tempMap = mutableMapOf<String, MutableList<String>>()
        input.forEach { line ->
            val (parent, child) = line.split("-")
            tempMap.putIfAbsent(child, mutableListOf())
            tempMap.putIfAbsent(parent, mutableListOf())
            tempMap[parent]?.add(child)
            tempMap[child]?.add(parent)
        }
        return tempMap.map { it.key to it.value.toList() }.toMap()
    }

    fun getInterconnectedThree(network: Map<String, List<String>>, parent: String, threes: MutableSet<List<String>>) {
        network[parent]!!.forEach { child ->
            network[child]!!.forEach { grandChild ->
                network[grandChild]!!.forEach { grandGrandChild ->
                    if (grandGrandChild == parent) {
                        threes.add(listOf(parent, child, grandChild).sorted())
                    }
                }
            }
        }
    }


    fun part1(input: List<String>): Number {
        val network = parse(input)
        val threes = mutableSetOf<List<String>>()
        network
            .filter { it.key.startsWith("t") }
            .forEach { parent -> getInterconnectedThree(network, parent.key, threes) }
        return threes.size
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    val testInput = readInput("Day23_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 0)

    // solve with real input
    solve("Day23", ::part1, ::part2)
}
