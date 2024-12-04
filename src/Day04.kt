fun main() {

    fun inputToGrid(input: List<String>): Grid<Char> {
        return buildMap {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, value ->
                    this[Coord(x, y)] = value
                }
            }
        }
    }

    fun Grid<Char>.isXMAS(start: Coord, direction: Direction): Boolean {
        if (this[start] == 'X') {
            var next = start.neighbor(direction)
            if (this[next] == 'M') {
                next = next.neighbor(direction)
                if (this[next] == 'A') {
                    next = next.neighbor(direction)
                    if (this[next] == 'S') {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun Grid<Char>.countXMAS(start: Coord): Int {
        return when {
            this[start] != 'X' -> 0
            else -> allDirections.filter { this.isXMAS(start, it) }.size
        }
    }

    fun part1(input: List<String>): Long {
        val grid = inputToGrid(input)
        return grid.keys.sumOf { grid.countXMAS(it) }.toLong()
    }

    fun Grid<Char>.countCrossMAS(start: Coord): Int {
        if (this[start] != 'A') {
            return 0
        }
        return when {
            this[start.northEast()] == 'M' && this[start.southWest()] == 'S' && this[start.northWest()] == 'M' && this[start.southEast()] == 'S' -> 1
            this[start.northEast()] == 'S' && this[start.southWest()] == 'M' && this[start.northWest()] == 'M' && this[start.southEast()] == 'S' -> 1
            this[start.northEast()] == 'S' && this[start.southWest()] == 'M' && this[start.northWest()] == 'S' && this[start.southEast()] == 'M' -> 1
            this[start.northEast()] == 'M' && this[start.southWest()] == 'S' && this[start.northWest()] == 'S' && this[start.southEast()] == 'M' -> 1
            else -> 0
        }
    }

    fun part2(input: List<String>): Long {
        val grid = inputToGrid(input)
        return grid.keys.sumOf { grid.countCrossMAS(it) }.toLong()
    }

    // test before attempt to solve
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18L)
    check(part2(testInput) == 9L)


    // solve with real input
    solve("Day04", ::part1, ::part2)
}
