import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

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

    fun tracePath(floor: Grid<Char>, start: Coord): Set<Coord> {
        var guard = start
        var direction = Direction.NORTH
        val path = mutableSetOf<Coord>()
        while (floor[guard] != null) {
            if (floor[Grid.next(guard, direction)] == '#') {
                direction = direction.turn90DegreesRight()
            } else {
                path.add(guard)
                guard = Grid.next(guard, direction)
            }
        }
        return path
    }

    fun Coord.producesLoopWith(floor: Grid<Char>, start: GridVector): Boolean {
        var guard = start.point
        var direction = start.direction
        val path = mutableSetOf<GridVector>()
        while (floor[guard] != null) {
            if (floor[Grid.next(guard, direction)] == '#' || Grid.next(guard, direction) == this) {
                if (!path.add(GridVector(guard, direction))) {
                    return true
                }
                direction = direction.turn90DegreesRight()
            } else {
                guard = Grid.next(guard, direction)
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val (floor, start) = inputToGrid(input)
        return tracePath(floor, start).size
    }

    fun part2(input: List<String>): Int {
        val (floor, start) = inputToGrid(input)
        val obstacles = tracePath(floor, start)
        return runBlocking {
            obstacles.map { obstacle ->
                async(Dispatchers.Default) {
                    obstacle.producesLoopWith(floor, GridVector(start, Direction.NORTH))
                }
            }.count { it.await() }
        }
    }

    // test before attempt to solve
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    // solve with real input
    solve("Day06", ::part1, ::part2)
}
