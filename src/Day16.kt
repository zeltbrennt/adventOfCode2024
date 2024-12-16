import java.util.PriorityQueue

fun main() {

    fun parse(input: List<String>): Grid<Char> {
        return Grid(buildMap {
            input.forEachIndexed { y, row ->
                row.forEachIndexed { x, cell ->
                    this[Coord(x, y)] = cell
                }
            }
        })
    }

    data class State(val position: Coord, val direction: Direction, val cost: Int)

    fun part1(input: List<String>): Number {
        val maze = parse(input)
        val start = maze.data.filter { it.value == 'S' }.keys.first()
        val visited = mutableSetOf(start)
        val queue = PriorityQueue<State>(compareBy { it.cost })
        queue.add(State(start, Direction.EAST, 0))
        while (queue.isNotEmpty()) {
            val current = queue.remove() ?: break
            if (maze[current.position] == 'E') return current.cost
            val next = Grid.next(current.position, current.direction)
            if (maze[next] != '#' && !visited.contains(next)) {
                queue.add(State(next, current.direction, current.cost + 1))
                visited.add(next)
            }
            val left = Grid.next(current.position, Grid.left(current.direction))
            if (maze[left] != '#' && !visited.contains(left)) {
                queue.add(State(left, Grid.left(current.direction), current.cost + 1001))
                visited.add(left)
            }
            val right = Grid.next(current.position, Grid.right(current.direction))
            if (maze[right] != '#' && !visited.contains(right)) {
                queue.add(State(right, Grid.right(current.direction), current.cost + 1001))
                visited.add(right)
            }
        }
        return -1
    }

    fun part2(input: List<String>): Number {
        return 0
    }

    // test before attempt to solve
    check(part1(readInput("Day16_test_1")) == 7036)
    check(part1(readInput("Day16_test_2")) == 11048)
    //check(part2(testInput) == 0L)

    // solve with real input
    solve("Day16", ::part1, ::part2)
}
