fun main() {

    fun calculatePrice(garden: Map<Coord, Char>, plot: Map.Entry<Coord, Char>, checked: MutableSet<Coord>): Int {
        if (plot.key in checked) return 0
        var area = 0
        var perimeter = 0
        val queue = ArrayDeque<Coord>()
        queue.addLast(plot.key)
        checked.add(plot.key)
        while (queue.isNotEmpty()) {
            //queue.println()
            val current = queue.removeFirst()
            area++
            for (neigbor in Grid.mainNeighbors(current)) {
                when {
                    garden[neigbor] != plot.value -> perimeter++
                    checked.contains(neigbor) -> continue
                    garden[neigbor] == plot.value -> {
                        queue.addLast(neigbor)
                        checked.add(neigbor)
                    }
                }
            }
        }
        return area * perimeter
    }

    fun part1(input: List<String>): Number {
        val garden = buildMap<Coord, Char> {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    this[Coord(x, y)] = cell
                }
            }
        }
        val visited = mutableSetOf<Coord>()
        return garden.map { calculatePrice(garden, it, visited) }.sum()
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    check(part1(readInput("Day12_test")) == 140)
    check(part1(readInput("Day12_test_2")) == 772)
    check(part1(readInput("Day12_test_3")) == 1930)
    check(part2(readInput("Day12_test")) == 80)
    check(part2(readInput("Day12_test_2")) == 236)
    check(part2(readInput("Day12_test_3")) == 368)

    // solve with real input
    solve("Day12", ::part1, ::part2)
}
