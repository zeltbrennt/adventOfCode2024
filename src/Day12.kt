fun main() {

    fun bfs(
        garden: Map<Coord, Char>,
        plot: Map.Entry<Coord, Char>,
        checked: MutableSet<Coord>
    ): Pair<Int, List<Coord>> {
        if (plot.key in checked) return (0 to emptyList())
        var area = 0
        val perimeter = mutableListOf<Coord>()
        val queue = ArrayDeque<Coord>()
        queue.addLast(plot.key)
        checked.add(plot.key)
        while (queue.isNotEmpty()) {
            //queue.println()
            val current = queue.removeFirst()
            area++
            for (neigbor in Grid.mainNeighbors(current)) {
                when {
                    garden[neigbor] != plot.value -> perimeter.add(neigbor)
                    checked.contains(neigbor) -> continue
                    garden[neigbor] == plot.value -> {
                        queue.addLast(neigbor)
                        checked.add(neigbor)
                    }
                }
            }
        }
        return area to perimeter
    }

    fun bfsGardenLayout(input: List<String>): List<Pair<Int, List<Coord>>> {
        val garden = buildMap<Coord, Char> {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    this[Coord(x, y)] = cell
                }
            }
        }
        val visited = mutableSetOf<Coord>()
        return garden.map {
            bfs(garden, it, visited)
        }
    }

    fun countSides(perimeter: List<Coord>): Int {
        val start = perimeter.first()
        val visited = mutableSetOf(start)
        var next = Grid.allNeighbors(start).first { it in perimeter }
        var direction = Grid.mainDirections.first { Grid.next(start, it) == next }
        visited.add(next)
        while (next in perimeter) {
            next = Grid.mainNeighbors(start).first { it in perimeter && !visited.contains(it) }
            visited.add(next)
        }
        return 0
    }

    fun part1(input: List<String>): Number {
        return bfsGardenLayout(input).sumOf { it.first * it.second.size }
    }

    fun part2(input: List<String>): Number {
        return bfsGardenLayout(input).sumOf { it.first * countSides(it.second) }
    }

    // test before attempt to solve
    check(part1(readInput("Day12_test_small")) == 140)
    check(part1(readInput("Day12_test_hole")) == 772)
    check(part1(readInput("Day12_test_full")) == 1930)

    check(part2(readInput("Day12_test_small")) == 80)
    check(part2(readInput("Day12_test_hole")) == 436)
    check(part2(readInput("Day12_test_E")) == 236)
    check(part2(readInput("Day12_test_AB")) == 368)
    check(part2(readInput("Day12_test_full")) == 1206)

    // solve with real input
    solve("Day12", ::part1, ::part2)
}
