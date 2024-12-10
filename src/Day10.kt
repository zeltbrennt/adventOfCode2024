fun main() {


    fun parse(input: List<String>) = buildMap {
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                this[Coord(x, y)] = cell.digitToInt()
            }
        }
    }

    fun Coord.score(topoMap: Grid<Int>, peaksRached: MutableList<Coord>, elevation: Int = 0): MutableList<Coord> {
        if (elevation == 9) {
            peaksRached.add(this)
        }
        Grid.mainNeighbors(this).forEach {
            if ((topoMap[it] ?: 0) == elevation + 1)
                it.score(topoMap, peaksRached, elevation + 1)
        }
        return peaksRached
    }

    fun part1(input: List<String>): Number {
        val topoMap = Grid(parse(input))
        return topoMap.data.filter { it.value == 0 }.keys.sumOf { it.score(topoMap, mutableListOf()).toSet().size }
    }

    fun part2(input: List<String>): Number {
        val topoMap = Grid(parse(input))
        return topoMap.data.filter { it.value == 0 }.keys.sumOf { it.score(topoMap, mutableListOf()).size }
    }

    // test before attempt to solve
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 36)
    check(part2(testInput) == 81)

    // solve with real input
    solve("Day10", ::part1, ::part2)
}
