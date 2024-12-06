fun main() {

    fun inputToGrid(input: List<String>): Pair<Grid<Char>, Coord> {
        val data = mutableMapOf<Coord, Char>()
        var start = Coord(0, 0)
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, value ->
                data[Coord(x, y)] = value
                if (value == '^') {
                    start = Coord(x, y)
                }
            }
        }
        return Grid(data) to start
    }

    fun Direction.turn90DegreesRight(): Direction {
        return when (this) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
            else -> throw Error("this should not happen")
        }
    }

    fun tracePath(grid: Grid<Char>, start: Coord): Set<Coord> {
        var guard = start
        var direction = Direction.NORTH
        val path = mutableSetOf<Coord>()
        while (grid[guard] != null) {
            if (grid[Grid.neighbor(guard, direction)] == '#') {
                direction = direction.turn90DegreesRight()
            } else {
                path.add(guard)
                guard = Grid.neighbor(guard, direction)
            }
        }
        return path
    }

    fun part1(input: List<String>): Int {
        val (grid, start) = inputToGrid(input)
        return tracePath(grid, start).size
    }

    fun isLoop(grid: Grid<Char>, start: Coord, startDirection: Direction, obstacle: Coord): Boolean {
        var guard = start
        var direction = startDirection
        val path = mutableMapOf<Coord, Direction>()
        while (grid[guard] != null) {
            if (path[guard] == direction) {
                return true
            }
            if (grid[Grid.neighbor(guard, direction)] == '#' || Grid.neighbor(guard, direction) == obstacle) {
                direction = direction.turn90DegreesRight()
            } else {
                path[guard] = direction
                guard = Grid.neighbor(guard, direction)
            }
        }
        return false
    }

    fun part2(input: List<String>): Int {
        val (grid, start) = inputToGrid(input)
        val path = tracePath(grid, start)

        return path.count { obstacle ->
            isLoop(grid, start, Direction.NORTH, obstacle)
        }
    }

    // test before attempt to solve
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    // solve with real input
    solve("Day06", ::part1, ::part2)
}
