fun main() {

    fun bfs(
        garden: Map<Coord, Char>,
        plot: Map.Entry<Coord, Char>,
        checked: MutableSet<Coord>
    ): Pair<List<Coord>, List<Coord>> {
        if (plot.key in checked) return emptyList<Coord>() to emptyList()
        val area = mutableListOf<Coord>()
        val perimeter = mutableListOf<Coord>()
        val queue = ArrayDeque<Coord>()
        queue.addLast(plot.key)
        checked.add(plot.key)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            area.add(current)
            for (neighbor in Grid.mainNeighbors(current)) {
                when {
                    garden[neighbor] != plot.value -> perimeter.add(neighbor)
                    checked.contains(neighbor) -> continue
                    garden[neighbor] == plot.value -> {
                        queue.addLast(neighbor)
                        checked.add(neighbor)
                    }
                }
            }
        }
        return area to perimeter
    }

    fun bfsGardenLayout(input: List<String>): List<Pair<List<Coord>, List<Coord>>> {
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

    fun countEdges(area: List<Coord>, perimeter: List<Coord>): Int {
        if (area.isEmpty()) return 0
        var edges = 0
        for (plot in area) {
            if (Grid.allNeighbors(plot).all { area.contains(it) }) continue
            // top left convex
            if (Grid.west(plot) in perimeter && Grid.north(plot) in perimeter) edges++
            //bottom right convex
            if (Grid.east(plot) in perimeter && Grid.south(plot) in perimeter) edges++
            // top right convex
            if (Grid.east(plot) in perimeter && Grid.north(plot) in perimeter) edges++
            //bottom left convex
            if (Grid.west(plot) in perimeter && Grid.south(plot) in perimeter) edges++
            // top left concave
            if (Grid.east(plot) in area && Grid.south(plot) in area && Grid.southEast(plot) in perimeter) edges++
            //bottom right concave
            if (Grid.west(plot) in area && Grid.north(plot) in area && Grid.northWest(plot) in perimeter) edges++
            // top right concave
            if (Grid.west(plot) in area && Grid.south(plot) in area && Grid.southWest(plot) in perimeter) edges++
            //bottom left concave
            if (Grid.east(plot) in area && Grid.north(plot) in area && Grid.northEast(plot) in perimeter) edges++

        }
        return edges
    }

    fun part1(input: List<String>): Number {
        return bfsGardenLayout(input).sumOf { it.first.size * it.second.size }
    }

    fun part2(input: List<String>): Number {
        return bfsGardenLayout(input).sumOf { it.first.size * countEdges(it.first, it.second) }
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
