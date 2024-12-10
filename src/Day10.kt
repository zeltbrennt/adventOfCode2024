fun main() {


    fun parse(input: List<String>) = buildMap {
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, cell ->
                this[Coord(x, y)] = cell.digitToInt()
            }
        }
    }

    fun Coord.score(topMap: Map<Coord, Int>, peaksRached: MutableSet<Coord>, current: Int = 0): MutableSet<Coord> {
        if (current == 9) {
            peaksRached.add(this)
        }
        val west = Grid.west(this)
        val east = Grid.east(this)
        val north = Grid.north(this)
        val south = Grid.south(this)
        if ((topMap[west] ?: 0) == current + 1) {
            west.score(topMap, peaksRached, current + 1)
        }
        if ((topMap[east] ?: 0) == current + 1) {
            east.score(topMap, peaksRached, current + 1)
        }
        if ((topMap[north] ?: 0) == current + 1) {
            north.score(topMap, peaksRached, current + 1)
        }
        if ((topMap[south] ?: 0) == current + 1) {
            south.score(topMap, peaksRached, current + 1)
        }
        return peaksRached
    }

    fun Coord.rating(topMap: Map<Coord, Int>, current: Int = 0): Int {
        if (current == 9) {
            return 1
        }
        var rating = 0
        val west = Grid.west(this)
        val east = Grid.east(this)
        val north = Grid.north(this)
        val south = Grid.south(this)
        if ((topMap[west] ?: 0) == current + 1) {
            rating += west.rating(topMap, current + 1)
        }
        if ((topMap[east] ?: 0) == current + 1) {
            rating += east.rating(topMap, current + 1)
        }
        if ((topMap[north] ?: 0) == current + 1) {
            rating += north.rating(topMap, current + 1)
        }
        if ((topMap[south] ?: 0) == current + 1) {
            rating += south.rating(topMap, current + 1)
        }
        return rating
    }

    fun part1(input: List<String>): Number {
        val topoMap = parse(input)
        return topoMap.filter { it.value == 0 }.keys.sumOf { it.score(topoMap, mutableSetOf()).size }
    }

    fun part2(input: List<String>): Number {
        val topoMap = parse(input)
        return topoMap.filter { it.value == 0 }.keys.sumOf { it.rating(topoMap) }
    }

    // test before attempt to solve
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 36)
    check(part2(testInput) == 81)

    // solve with real input
    solve("Day10", ::part1, ::part2)
}
