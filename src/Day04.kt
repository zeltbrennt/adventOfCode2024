fun main() {

    fun inputToGrid(input: List<String>): Grid<Char> {
        return Grid(buildMap {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, value ->
                    this[Coord(x, y)] = value
                }
            }
        })
    }

    fun Grid<Char>.isXMAS(start: Coord, direction: Direction): Boolean {
        var next = Grid.neighbor(start, direction)
        if (this[next] == 'M') {
            next = Grid.neighbor(next, direction)
            if (this[next] == 'A') {
                next = Grid.neighbor(next, direction)
                if (this[next] == 'S') {
                    return true
                }
            }
        }
        return false
    }

    fun Grid<Char>.countXMAS(start: Coord): Int {
        return when {
            this[start] != 'X' -> 0
            else -> Grid.allDirections.filter { this.isXMAS(start, it) }.size
        }
    }

    fun part1(input: List<String>): Long {
        val grid = inputToGrid(input)
        return grid.coords.sumOf { grid.countXMAS(it) }.toLong()
    }

    fun Grid<Char>.countCrossMAS(start: Coord): Int {
        if (this[start] != 'A') {
            return 0
        }
        return when {
            this[Grid.northEast(start)] == 'M'
                    && this[Grid.southWest(start)] == 'S'
                    && this[Grid.northWest(start)] == 'M'
                    && this[Grid.southEast(start)] == 'S' -> 1

            this[Grid.northEast(start)] == 'S'
                    && this[Grid.southWest(start)] == 'M'
                    && this[Grid.northWest(start)] == 'M'
                    && this[Grid.southEast(start)] == 'S' -> 1

            this[Grid.northEast(start)] == 'S'
                    && this[Grid.southWest(start)] == 'M'
                    && this[Grid.northWest(start)] == 'S'
                    && this[Grid.southEast(start)] == 'M' -> 1

            this[Grid.northEast(start)] == 'M'
                    && this[Grid.southWest(start)] == 'S'
                    && this[Grid.northWest(start)] == 'S'
                    && this[Grid.southEast(start)] == 'M' -> 1

            else -> 0
        }
    }

    fun part2(input: List<String>): Long {
        val grid = inputToGrid(input)
        return grid.coords.sumOf { grid.countCrossMAS(it) }.toLong()
    }

    // test before attempt to solve
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18L)
    check(part2(testInput) == 9L)

    // solve with real input
    solve("Day04", ::part1, ::part2)
}
